package converter

fun toBinary(number: Int): Int {
    var bin = 0
    var rem = 1
    var step = 1
    var i = 1
    var num = number

    while (num != 0) {
        rem = num % 2
        num /= 2
        bin += rem * i
        i *= 10
    }
    return bin
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

fun toAnyBase(toAnyBase: Int, number: Int): String {
    return number.toString(toAnyBase)
}

fun binToDec(number: Int): Int {
    var num = number.toLong()
    var decimalNumber = 0
    var i = 0
    var remainder: Long

    while (num.toInt() != 0) {
        remainder = num % 10
        num /= 10
        decimalNumber += (remainder * Math.pow(2.0, i.toDouble())).toInt()
        ++i
    }
    return decimalNumber
}

fun fromAnyBaseToDec(fromBase: Int, number: String): Int {
    return number.toInt(fromBase)
}

fun octalToDec(number: Int): Int {
    return number.toString().toInt(8)
}

fun convert(src: Int, trg: Int, num: String) {
    val dec: Int = when {
        src == 2 -> binToDec(num.toInt())
        src == 8 -> octalToDec(num.toInt())
        src in 9..36 -> fromAnyBaseToDec(src, num)
        else -> 0
    }
    var res: String
    if (src == trg) {
        res = dec.toString()
    } else {
        res = when {
            src == 2 -> toBinary(dec).toString()
            src == 8 -> toOctal(dec).toString()
            src in 9..36 -> toAnyBase(trg, dec)
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