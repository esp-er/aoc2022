package patriker.day04
import patriker.utils.*
fun main() {

    val testInput = readInput("Day04_test")
    val input = readInput("Day04_input")

    check(solvePart1(testInput) == 2)

    println(solvePart1(input))
    println(solvePart2(testInput))
    println(solvePart2(input))
    // test if implementation meets criteria from the description, like:
}

fun solvePart1(input: List<String>): Int{
    return input.asSequence().map{ elfPair ->
        val pairs = elfPair.split(",")
        val (firstStart, firstEnd) = pairs.first().split("-").map(String::toInt)
        val (secondStart, secondEnd) = pairs.last().split("-").map(String::toInt)

        (firstStart>= secondStart && firstEnd <= secondEnd) ||
            (secondStart >= firstStart && secondEnd <= firstEnd)
    }.count{it}
}

fun solvePart2(input: List<String>): Int{
    return input.asSequence().map{ elfPair ->
        val pairs = elfPair.split(",")
        val (firstStart, firstEnd) = pairs.first().split("-").map(String::toInt)
        val (secondStart, secondEnd) = pairs.last().split("-").map(String::toInt)

        (firstStart in secondStart..secondEnd)
                || (secondStart in firstStart..firstEnd)

    }.count{it}
}

