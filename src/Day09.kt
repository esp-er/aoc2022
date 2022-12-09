package patriker.day09
import kotlin.math.abs
import patriker.utils.*

data class Pos(val x: Int, val y: Int)

enum class Dir{
    RIGHT,LEFT,UP,DOWN;
}
fun main() {
    val testInput = readInput("Day09_test")
    val input = readInput("Day09_input")
    // test if implementation meets criteria from the description, like:
    //val testInput = readInputText("Day01_test")

    println(solvePart1(testInput))
    println(solvePart1(input))
}

fun maxDist(hpos: Pos, tpos: Pos): Int{
    val diff = Pos(abs(hpos.x - tpos.x),  abs(hpos.y - tpos.y))
    return maxOf(diff.x, diff.y)
}


fun moveRope(dir: Dir,  HPos: Pos, TPos: Pos, times: Int = 1): List<Pair<Pos,Pos>>{
    var tpos = TPos
    var hpos = HPos

    val hdir = when(dir){
        Dir.RIGHT -> 1
        Dir.LEFT -> -1
        else -> 0
    }
    val vdir = when(dir){
        Dir.UP -> -1
        Dir.DOWN -> 1
        else -> 0
    }

    var visitedPos = mutableListOf<Pair<Pos,Pos>>()
    repeat(times){
        val oldpos = hpos
        hpos = Pos(hpos.x + hdir, hpos.y + vdir)
        if(maxDist(hpos,tpos) > 1)
            tpos = oldpos

        visitedPos.add(Pair(hpos,tpos))
    }

    return visitedPos
}

fun solvePart1(input: List<String>): Int{
    val visitedPositions =  mutableSetOf<Pos>()
    visitedPositions.add(Pos(0,0))
    var hpos = Pos(0,0)
    var tpos = Pos(0,0)
    input.forEach{
        val (direction, times) = it.split(" ")
        val timesInt = times.toInt()

        val visited = when(direction) {
            "R" -> moveRope(Dir.RIGHT, hpos, tpos, timesInt)
            "L" -> moveRope(Dir.LEFT, hpos, tpos, timesInt)
            "U" -> moveRope(Dir.UP, hpos, tpos, timesInt)
            "D" -> moveRope(Dir.DOWN, hpos, tpos, timesInt)
            else -> error("Erroneous input!")
        }
        visitedPositions.addAll( visited.map{ it.second } )
        hpos = visited.last().first
        tpos = visited.last().second
    }

    return visitedPositions.size
}

fun solvePart2(input: String): List<Int>{
    TODO()
}

