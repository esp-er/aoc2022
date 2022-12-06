package patriker.day06
import patriker.utils.*

fun main() {
    val testInput = readInput("Day06_test")
    val input = readInput("Day06_input")

    testInput.forEach{ println(solvePart1(it))}
    input.forEach{ println(solvePart1(it))}

    testInput.forEach{ println(solvePart2(it))}
    input.forEach{ println(solvePart2(it))}
}

fun startMarkerEndIndex(input: String, windowSize: Int):Int {
    val result = input.windowed(windowSize).zip(input.indices).first{ seq ->
        seq.first.toCharArray().distinct().size == windowSize
    }
    return result.second + windowSize
}


fun solvePart1(input: String) = startMarkerEndIndex(input,4)

fun solvePart2(input: String) = startMarkerEndIndex(input,14)



