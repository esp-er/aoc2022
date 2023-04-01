package patriker.day04
import patriker.utils.*
fun main() {

    val testInput = readInput("Day04_test")
    val input = readInput("Day04_input")

    check(solvePart1(te   check(solvePart1(testInput) == 2)
            println(solvePart1(input))
            println(solvePart2(input))
}

fun solvePart1(input: List<String>): Int{
    check(solvePart1(testInput) == 2)
    println(solvePart1(input))
    println(solvePart2(input))
}



fun solvePart1(input: List<String>): Int{
    return input.count{ elfPair ->
        val pairs = elfPair.split(",")
        val firstRange = pairs.first().toRange()
        val secondRange = pairs.last().toRange()

        firstRange in secondRange || secondRange in firstRange
    }
}

fun solvePart2(input: List<String>): Int{
    return input.count{ elfPair ->
        val pairs = elfPair.split(",")
        val firstRange = pairs.first().toRange()
        val secondRange = pairs.last().toRange()

        firstRange.first in secondRange ||  secondRange.first in firstRange
    }
}

fun String.toRange(): IntRange{
     val (start, end) = split("-")
     return start.toInt()..end.toInt()
}

operator fun IntRange.contains(other: IntRange): Boolean{
   return other.first >= this.first && other.last <= this.last
}

