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

fun convert(src: Int, trg: Int, num: String) {

    val dec: String = when {
        src == 2 -> binToDec(num)
        //src == 8 -> octalToDec(num.toInt())
        src in 3..36 -> fromAnyBaseToDec(src, num)
        else -> "0"
    }
    var res: String
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