package converter

fun toBinary(number: Int) {
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
    println("Conversion result: $bin")
}

fun toOctal(number: Int) {
    var dec = number;
    var octal = 0;
    var i = 1;

    while (dec != 0) {
        octal += dec % 8 * i
        dec = dec / 8
        i *= 10
    }
    println("Conversion result: $octal")
}

fun toHexa(number: Int) {
    println("Conversion result: ${number.toString(16)}")
}


fun main() {
    println("Enter number in decimal system:")
    val number = readln().toInt()
    println("Enter target base:")
    val target = readln().toInt()

    when (target) {
        2 -> toBinary(number)
        8 -> toOctal(number)
        16 -> toHexa(number)
    }
}