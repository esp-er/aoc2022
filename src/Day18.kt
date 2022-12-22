package patriker.day18
import patriker.utils.*

data class Pos3(val x: Int, val y: Int, val z: Int)

val Pos3.adjacentPositions: List<Pos3>
    get() = listOf(Pos3(x - 1, y, z ), Pos3(x + 1, y, z ) , Pos3(x, y - 1, z ) , Pos3(x, y + 1, z ), Pos3(x, y, z - 1), Pos3(x, y , z + 1))

fun main() {
    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day18_test")
    val input = readInput("Day18_input")

    //check(solvePart1(testInput) == 64)
    println(solvePart1(testInput))
    println(solvePart1(input))

    println(solvePart2(testInput))
    println(solvePart2(input))

}

fun inputToSet(input: List<String>): HashSet<Pos3>{
    val set = HashSet<Pos3>()
    input.forEach{line ->
        val (xstr, ystr, zstr) = line.split(",")
        set.add(Pos3( xstr.toInt(), ystr.toInt(), zstr.toInt()))
    }
    return set
}
fun solvePart1(input: List<String>): Int{
    val lavaPositions = inputToSet(input)
    return surfaceArea(lavaPositions)
}

fun surfaceArea(positions: HashSet<Pos3>): Int{
    val surfaceArea = positions.sumOf {pos ->
        val adjCount = pos.adjacentPositions.count{
            positions.contains(it)
        }

        6 - adjCount
    }
    return surfaceArea
}

fun Pos3.outOfBounds(bounds: Bounds3): Boolean{
    return x !in bounds.xRange || y !in bounds.yRange || z !in bounds.zRange
}

data class Bounds3(val xRange: IntRange, val yRange: IntRange, val zRange: IntRange)

fun Pos3.hasWayOut(lavaPositions: HashSet<Pos3>, bounds: Bounds3, visited: HashSet<Pos3> = HashSet<Pos3>(), toVisit: HashSet<Pos3> = HashSet()): Boolean {
    if(this.outOfBounds(bounds))
        return true

    val adjPositions = this.adjacentPositions
    val toVisit = adjPositions.filter{it !in lavaPositions && it !in visited}

    if(toVisit.isEmpty())
        return false

    visited.addAll(toVisit + this)
    return toVisit.any { p -> p.hasWayOut(lavaPositions, bounds, visited) }
}

fun Pos3.pathOut(lavaPositions: HashSet<Pos3>, bounds: Bounds3,
                 visited: HashSet<Pos3> = HashSet(),
                 toVisit: HashSet<Pos3> = HashSet(),
                 path: HashSet<Pos3> = HashSet()): Pair<Boolean, HashSet<Pos3>> {

    if(this.outOfBounds(bounds))
        return true to path

    val adjPositions = this.adjacentPositions
    val toVisit = adjPositions.filter{it !in lavaPositions && it !in visited}

    if(toVisit.isEmpty()) {
        return false to HashSet()
    }

    visited.addAll(toVisit + this)
    path.add(this)

    val ret = toVisit.map{ p -> p.pathOut(lavaPositions, bounds, visited, path) }.firstOrNull{it.first}
    return if(ret != null) ret else false to HashSet()
}

fun solvePart2(input: List<String>): Int{
    val lavaPositions = inputToSet(input)

    val xRange =  lavaPositions.minOf{it.x}..lavaPositions.maxOf{it.x}
    val yRange = lavaPositions.minOf{it.y}..lavaPositions.maxOf{it.y}
    val zRange = lavaPositions.minOf{it.z}..lavaPositions.maxOf{it.z}

    val emptyPositions = HashSet<Pos3>()
    emptyPositions.addAll (
        xRange.flatMap { x ->
            yRange.flatMap { y ->
                zRange.map { z ->
                    Pos3(x, y, z)
                }
            }
        }.filter { it !in lavaPositions }
    )

    val bubblePositions = emptyPositions.filterNot{
        it.hasWayOut(lavaPositions, Bounds3(xRange, yRange, zRange))
    }

    val bubbleSurface = surfaceArea( bubblePositions.toHashSet())

    return surfaceArea(lavaPositions) - bubbleSurface

}