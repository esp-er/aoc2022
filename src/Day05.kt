package patriker.day05
import patriker.utils.*
import java.util.Deque

fun main() {
    // test if implementation meets criteria from the description, like:
    val testInput = readInputText("Day05_test")
    val input = readInputText("Day05_input")


    println(solvePart1(input))
    println(solvePart2(testInput))

    check(solvePart2(testInput) == "MCD")
    println(solvePart2(input))


}

//used in part1
fun moveCrate(from: Int, dest: Int, numCrates: Int, crateStacks: Array<ArrayDeque<Char>>): Unit{
    repeat(numCrates){
        crateStacks[dest].addFirst( crateStacks[from].removeFirst() )
    }
}

//used in part2
fun moveCrates(from: Int, dest: Int, numCrates: Int, crateStacks: Array<ArrayDeque<Char>>): Unit{
    val toMove = crateStacks[from].slice(0 until numCrates)
    repeat(numCrates){crateStacks[from].removeFirst()}
    crateStacks[dest].addAll(0, toMove)
}

fun String.isInteger() : Boolean {
    return this.toIntOrNull() != null
}

fun parseLine(line: String): Triple<Int,Int,Int> {
    val (numCrates, from, dest) =  line.split(" ").filter{ it.isInteger() }
    return Triple(from.toInt(), dest.toInt(), numCrates.toInt())
}


fun solvePart1(input: String): String{
    val (stacks, instructions) = input.split("\n\n")

    val numStacks = stacks.lines().last().filter{!it.isWhitespace()}.length
    val colPositions = stacks.lines().last().foldIndexed(emptyList<Int>()){ i, accum, c ->
        if(!c.isWhitespace()){
            accum + i
        }else{
            accum
        }
    }

    val stackArr = Array(numStacks){
        ArrayDeque<Char>()
    }

    stacks.lines().dropLast(1).reversed().forEach{ containerLine ->
        colPositions.forEachIndexed{ i, p ->
            if(p < containerLine.length && !containerLine[p].isWhitespace())
                stackArr[i].addFirst(containerLine[p])
        }

    }

    instructions.lines().forEach{ line ->
        if(line.isNotBlank()) {
            val (from, dest, numCrates) = parseLine(line)
            moveCrate(from - 1, dest - 1, numCrates, stackArr)
        }
    }

    return stackArr.map{it[0]}.joinToString("")
}


fun solvePart2(input: String): String{
    val (stacks, instructions) = input.split("\n\n")

    val numStacks = stacks.lines().last().filter{!it.isWhitespace()}.length
    val colPositions = stacks.lines().last().foldIndexed(emptyList<Int>()){ i, accum, c ->
        if(!c.isWhitespace()){
            accum + i
        }else{
            accum
        }
    }

    val stackArr = Array(numStacks){
        ArrayDeque<Char>()
    }

    stacks.lines().dropLast(1).reversed().forEach{ containerLine ->
        colPositions.forEachIndexed{ i, p ->
            if(p < containerLine.length && !containerLine[p].isWhitespace())
                stackArr[i].addFirst(containerLine[p])
        }

    }

    instructions.lines().forEach{ line ->
        if(line.isNotBlank()) {
            val (from, dest, numCrates) = parseLine(line)
            moveCrates(from - 1, dest - 1, numCrates, stackArr)
        }
    }

    return stackArr.map{it[0]}.joinToString("")
}

