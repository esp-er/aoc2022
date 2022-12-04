package patriker.day03
import patriker.utils.*

val Char.priority
    get(): Int{
        if(this.isUpperCase()){
            return this.code - 'A'.code + 27
        }
        return this.code - 'a'.code + 1
    }

fun main() {
    val testInput = readInput("Day03_test")
    val input = readInput("Day03_input")

    println(solvePart1(testInput))
    println(solvePart1(input))

    check(solvePart2(testInput) == 70)
    println(solvePart2(input))
}

fun solvePart1(input: List<String>): Int{
    val overlapList =  input.map{ contents ->
        val firstCompartment = contents.slice(0 until contents.length/2)
        val secondCompartment = contents.slice(contents.length/2 until contents.length)
        val overlap = firstCompartment
            .asSequence()
            .distinct()
            .filter{secondCompartment.contains(it)}
        overlap.sumOf(Char::priority)
    }
    return overlapList.sum()
}
fun solvePart2(input: List<String>): Int{
    val commonItemScores =  input.chunked(3){group ->
        val groupSet = group.map(String::toSet)
        val (ruck1, ruck2, ruck3) = groupSet
        val commonItem = ruck1 intersect ruck2 intersect ruck3
        commonItem.sumOf(Char::priority)
    }

    return commonItemScores.sum()
}

