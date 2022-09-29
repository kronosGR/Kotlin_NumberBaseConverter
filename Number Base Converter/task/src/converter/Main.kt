package converter

import java.math.BigDecimal
import java.math.BigInteger
import kotlin.math.pow

fun toBinary(number: String): String {
    var bin = "0".toBigInteger()
    var rem = "1".toBigInteger()
    var step = 1
    var i = "1".toBigInteger()
    var num = number.toBigInteger()

    while (num != "0".toBigInteger()) {
        rem = num % "2".toBigInteger()
        num /= "2".toBigInteger()
        bin += rem * i
        i *= "10".toBigInteger()
    }
    return bin.toString()
}

fun toOctal(number: Int): Int {
    var dec = number;
    var octal = 0;
    var i = 1;

    while (dec != 0) {
        octal += dec % 8 * i
        dec = dec / 8
        i *= 10
    }
    return octal
}

fun toAnyBase(toAnyBase: Int, number: String): String {
    return number.toBigDecimal().toBigInteger().toString(toAnyBase)
}

fun binToDec(number: String): String {
    var num: BigInteger = number.toBigDecimal().toBigInteger()
    var decimalNumber: BigDecimal = "0.0".toBigDecimal()
    var i: Int = 0
    var remainder: BigInteger

    while (num.toInt() != 0) {
        remainder = num % 10.toBigInteger()
        num /= 10.toBigInteger()
        decimalNumber += BigDecimal(remainder * (2.toBigInteger()).pow(i))
        ++i
    }
    return decimalNumber.toString()
}


fun fromAnyBaseToDec(fromBase: Int, number: String): String {
    return number.toBigInteger(fromBase).toString()
}

fun octalToDec(number: Int): Int {
    return number.toString().toInt(8)
}

fun fractionalToDecimal(factional: String, base: Int): BigDecimal {
    return factional.toLowerCase().mapIndexed { i, c ->
        val number = Integer.valueOf(c.toString(), base)
        if (number == 0) {
            number.toBigDecimal()
        } else {
            (number * base.toDouble().pow(-(i + 1))).toBigDecimal()
        }
    }.reduceRight(BigDecimal::add)
}

fun fractionalFromDecimal(fractional: BigDecimal, base: Int): String {
    val result = mutableListOf<String>()
    var remaining = fractional
    var precision = 1
    while (precision <= 5 && remaining.compareTo(BigDecimal.ZERO) != 0) {
        remaining = remaining.multiply(base.toBigDecimal())
        val integerPart = remaining.toInt()
        if (integerPart > 0) {
            result.add(integerPart.toString(base))
            remaining = remaining.subtract(integerPart.toBigDecimal())
        } else {
            result.add("0")
        }
        precision++
    }
    return result.joinToString("") { it.toString() }
}

fun convert(src: Int, trg: Int, num: String) {
    var dec: String
    var res: String = ""
    if (num.contains('.')) {
        val (integer, fract) = num.split('.')
        val integ = when {
            src == 2 -> binToDec(num)
            //src == 8 -> octalToDec(num.toInt())
            src in 3..36 -> fromAnyBaseToDec(src, integer)
            else -> "0"
        }


        val decF = fractionalFromDecimal(fract.toBigDecimal(), src)
        val newF = fractionalToDecimal(decF, trg)
        res = "$integ.$newF"
    } else {
        dec = when {
            src == 2 -> binToDec(num)
            //src == 8 -> octalToDec(num.toInt())
            src in 3..36 -> fromAnyBaseToDec(src, num)
            else -> "0"
        }

        if (src == trg) {
            res = dec.toString()
        } else {
            res = when {
                trg == 2 -> toBinary(dec)
                //src == 8 -> toOctal(dec).toString()
                trg in 3..36 -> toAnyBase(trg, dec)
                else -> "0"
            }
        }
    }
    println("Conversion result: $res")

}

fun menu() {
    while (true) {
        println("Enter two numbers in format: {source base} {target base} (To quit type /exit)")
        val choice = readln()
        if (choice.equals("/exit")) {
            break;
        }
        val bases = choice.split(" ")
        val srcBase = bases[0]
        val tgtBase = bases[1]

        while (true) {
            println("Enter number in base $srcBase to convert to base $tgtBase (To go back type /back)")
            val ch = readln()
            if (ch.equals("/back")) {
                break;
            }

            convert(srcBase.toInt(), tgtBase.toInt(), ch)
        }
    }
}

fun main() {
    menu()
}