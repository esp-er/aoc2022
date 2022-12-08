package patriker.day07
import patriker.utils.*

import kotlin.collections.ArrayDeque

data class Directory(val name: String, var size: Int = 0 , var subDirs: List<Directory> = emptyList())

fun main() {
    val testInput = readInput("Day07_test")
    //val input = readInput("Day07_input")

    println(solvePart1(testInput))


    val input = readInput("Day07_input")

    println(solvePart1(input))
    //input.forEach{ println(solvePart1(it))}

    println(solvePart2(testInput))
    println(solvePart2(input))

    //testInput.forEach{ println(solvePart2(it))}
    //input.forEach{ println(solvePart2(it))}
}


fun String.isInteger() : Boolean {
    return this.toIntOrNull() != null
}

fun calculateTotals(d: Directory, dirMap: Map<String,Directory>): Int{
    val childrenSize = d.subDirs.sumOf{ calculateTotals( dirMap[it.name]!!, dirMap) }
    return d.size + childrenSize

}

fun solvePart2(input: List<String>): Int {
    val currentDir = ArrayDeque<Directory>().apply {
        this.addFirst(Directory("root"))
    }
    var directories = mutableMapOf<String, Directory>()

    input.forEach { line ->
        if (line.startsWith("$")) {
            val cmds = line.split(" ")
            if (cmds[1] == "cd") {
                when (cmds[2]) {
                    "/" -> {
                        currentDir.clear(); currentDir.addFirst(Directory("root"))
                    }

                    ".." -> currentDir.removeFirst()
                    else -> {
                        currentDir.addFirst(Directory("${currentDir[0].name}/${cmds[2]}"))
                    }
                }
            }

        } else {
            val contents = line.split(" ")
            val dirOrSize = contents.first()
            when {
                dirOrSize.isInteger() -> {
                    currentDir.first().size += dirOrSize.toInt()
                    directories[currentDir[0].name] = currentDir.first()
                }
                dirOrSize == "dir" -> {
                    currentDir[0].subDirs += Directory("${currentDir[0].name}/${contents[1]}")
                    directories[currentDir[0].name] = currentDir.first()
                }
            }
        }
    }

    //println(directories)

    val freeSpace = 70000000 - calculateTotals(directories["root"]!!, directories)

    return directories.values.minOf { dir ->
        val size = calculateTotals(dir, directories)
        if(freeSpace + size >= 30000000)
            size
        else
            Int.MAX_VALUE
    }

}

fun solvePart1(input: List<String>): Int {
    val currentDir = ArrayDeque<Directory>().apply {
        this.addFirst(Directory("root"))
    }
    var directories = mutableMapOf<String, Directory>()

    input.forEach { line ->
        if (line.startsWith("$")) {
            val cmds = line.split(" ")
            if (cmds[1] == "cd") {
                when (cmds[2]) {
                    "/" -> {
                        currentDir.clear(); currentDir.addFirst(Directory("root"))
                    }

                    ".." -> currentDir.removeFirst()
                    else -> {
                        currentDir.addFirst(Directory("${currentDir[0].name}/${cmds[2]}"))
                    }
                }
            }

        } else {
            val contents = line.split(" ")
            val dirOrSize = contents.first()
            when {
                dirOrSize.isInteger() -> {
                    currentDir.first().size += dirOrSize.toInt()
                    directories[currentDir[0].name] = currentDir.first()
                }
                dirOrSize == "dir" -> {
                    currentDir[0].subDirs += Directory("${currentDir[0].name}/${contents[1]}")
                    directories[currentDir[0].name] = currentDir.first()
                }
            }
        }
    }

    //println(directories)
    val smallDirsSum =
        directories.values.sumOf{ dir ->
            val size = calculateTotals(dir , directories)
            if( size <= 100000)
                size
            else 0
        }

    return smallDirsSum

}





