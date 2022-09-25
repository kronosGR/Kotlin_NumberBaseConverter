package converter

//fun toBinary(number: Int) {
//    var bin = 0
//    var rem = 1
//    var step = 1
//    var i = 1
//    var num = number
//
//    while (num != 0) {
//        rem = num % 2
//        num /= 2
//        bin += rem * i
//        i *= 10
//    }
//    println("Conversion result: $bin")
//}
//
//fun toOctal(number: Int) {
//    var dec = number;
//    var octal = 0;
//    var i = 1;
//
//    while (dec != 0) {
//        octal += dec % 8 * i
//        dec = dec / 8
//        i *= 10
//    }
//    println("Conversion result: $octal")
//}

//fun toHexa(number: Int) {
//    println("Conversion result: ${number.toString(16)}")
//}



//fun fromDecimal() {
//    println("Enter number in decimal system:")
//    val number = readln().toInt()
//    println("Enter target base:")
//    val target = readln().toInt()
//
//    when (target) {
//        2 -> toBinary(number)
//        8 -> toOctal(number)
//        16 -> toHexa(number)
//    }
//    println()
//}
//
//fun binToDec(number: String) {
//    var num = number.toLong()
//    var decimalNumber = 0
//    var i = 0
//    var remainder: Long
//
//    while (num.toInt() != 0) {
//        remainder = num % 10
//        num /= 10
//        decimalNumber += (remainder * Math.pow(2.0, i.toDouble())).toInt()
//        ++i
//    }
//    println("Conversion to decimal result: ${decimalNumber}")
//}
//
//fun hexToDec(number: String) {
//    println("Conversion to decimal result: ${number.toInt(16)}")
//}
//
//fun octalToDec(number: String) {
//    println("Conversion to decimal result: ${number.toInt(8)}")
//}
//
//fun toDecimal() {
//    println("Enter source number: ")
//    val src = readln()
//    println("Enter source base: ")
//    val base = readln().toInt()
//    when (base) {
//        2 -> binToDec(src)
//        8 -> octalToDec(src)
//        16 -> hexToDec(src)
//    }
//}

fun vall(c: Char): Int {
    return if (c >= '0' && c <= '9') c.code - '0'.code else c.code - 'A'.code + 10
}

fun reVal(num: Int): Char {
    return if (num >= 0 && num <= 9) (num + 48).toChar() else (num - 10 + 65).toChar()
}

fun fromDecimal(base1: Int, inputNum: Int): String? {
    var inputNum = inputNum
    var s = ""

    while (inputNum > 0) {
        s += reVal(inputNum % base1)
        inputNum /= base1
    }
    val ix = StringBuilder()

    ix.append(s)

    return String(ix.reverse())
}

fun toDecimal(
    str: String,
    base: Int
): Int {
    val len = str.length
    var power = 1
    var num = 0
    var i: Int

    i = len - 1
    while (i >= 0) {
        if (vall(str[i]) >= base) {
            println("Invalid Number")
            return -1
        }
        num += vall(str[i]) * power
        power = power * base
        i--
    }
    return num
}
fun convert(src: String, trg: String,  num: String){
    val dec =  toDecimal( num, src.toInt())
    println(dec)

}

fun menu() {
    while (true) {
        println("Enter two numbers in format: {source base} {target base} (To quit type /exit)")
        val choice = readln()
        if (choice.equals("/exit")){
            break;
        }
        val bases = choice.split(" ")
        val srcBase = bases[0]
        val tgtBase = bases[1]

        while(true){
            println("Enter number in base $srcBase to convert to base $tgtBase (To go back type /back)")
            val ch = readln()
            if (ch.equals("/back")){
                break;
            }

            convert(srcBase, tgtBase, ch)

        }

    }
}
fun main() {
    menu()

}