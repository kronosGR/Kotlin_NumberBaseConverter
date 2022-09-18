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
    println(bin)
}

fun toOctal(number: Int) {

}

fun toHexa(number: Int) {

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