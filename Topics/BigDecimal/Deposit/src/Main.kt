import java.math.BigDecimal
import java.math.RoundingMode

fun main() {
    // write your code here
    val amount = readln().toBigDecimal()
    val interest = readln().toBigDecimal()
    val years = readln().toInt()

    val step1: BigDecimal = interest.divide(BigDecimal(100))
    val step2: BigDecimal = (BigDecimal.ONE + step1).pow(years)
    val finalAmount: BigDecimal = amount * step2

    println("Amount of money in the account: ${finalAmount.setScale(2, RoundingMode.CEILING)}")
}