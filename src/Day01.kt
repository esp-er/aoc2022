package patriker.day01
import patriker.utils.*
fun main() {
    // test if implementation meets criteria from the description, like:
    val testInput = readInputText("Day01_test")
    check(solvePart1(testInput) == 24000)

    check(solvePart2(testInput) == listOf(24000, 11000, 10000))


    val input = readInputText("Day01_input")
    println(solvePart1(input))
    println(solvePart2(input).sum())
}


fun caloriesPerElf(input: String): List<Int>{
    val calories = input.split("\n\n").map {cals ->
        cals.lines().filter(String::isNotBlank).map{
            it.toInt()
        }.sum()
    }
    return calories
}

fun solvePart1(input: String): Int{
    val elfCalorieList = caloriesPerElf(input)
    return elfCalorieList.max()

}


fun solvePart2(input: String): List<Int>{
    val elfCalorieList = caloriesPerElf(input)
    return elfCalorieList.sortedDescending().take(3)

}

