package patriker.day14
import patriker.utils.*

fun main() {
    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day14_test")
    val input = readInput("Day14_input")
    check(solvePart1(testInput) == 24)

    println(solvePart1(input))


    check(solvePart2(testInput) == 93)
    println(solvePart2(input))
}

fun parsePath(line: String): List<Pair<Int,Int>>{
    val points = line.replace(" ", "").split("->")

    val pointsPairs = points.map{
        val (x,y) =  it.split(",")
        x.toInt() to y.toInt()
    }
    return pointsPairs
}

fun updateRockWall(points: List<Pair<Int,Int>>, blockedMap: HashSet<Pair<Int,Int>>){
   points.windowed(2).forEach{ wallSegment ->
       val (start,end) = wallSegment
       val horRange = if(start.first < end.first) start.first..end.first else start.first downTo end.first
       val vertRange = if(start.second < end.second) start.second..end.second else start.second downTo end.second

       horRange.forEach{xPoint ->
           vertRange.forEach{yPoint ->
               blockedMap.add(xPoint to yPoint)
           }
       }
   }
}

fun simulateFalling(startPos: Pair<Int,Int>, blockedMap: HashSet<Pair<Int,Int>>, floorLevel: Int): Pair<Int,Int>{
    var currentPos = startPos

    while(true){
        if(!blockedMap.contains(currentPos.first to currentPos.second + 1))
            currentPos = currentPos.first to currentPos.second + 1
        else if(!blockedMap.contains(currentPos.first - 1 to currentPos.second + 1))
            currentPos = currentPos.first - 1 to currentPos.second + 1
        else if(!blockedMap.contains(currentPos.first + 1 to currentPos.second  + 1))
            currentPos = currentPos.first + 1 to currentPos.second +  1
        else
            break
        if(currentPos.second > floorLevel)
            break
    }

    return if(currentPos.second > floorLevel) -1 to -1 else currentPos

}


fun simulateFalling2(startPos: Pair<Int,Int>, blockedMap: HashSet<Pair<Int,Int>>, floorLevel: Int): Pair<Int,Int>{
    var currentPos = startPos

    while(true){
        if(currentPos.second + 1 < floorLevel) {
            if (!blockedMap.contains(currentPos.first to currentPos.second + 1))
                currentPos = currentPos.first to currentPos.second + 1
            else if (!blockedMap.contains(currentPos.first - 1 to currentPos.second + 1))
                currentPos = currentPos.first - 1 to currentPos.second + 1
            else if (!blockedMap.contains(currentPos.first + 1 to currentPos.second + 1))
                currentPos = currentPos.first + 1 to currentPos.second + 1
            else //sand comes to rest
                break
        }
        else { //reached floor
            break
        }
    }

    return if(currentPos.second > floorLevel) -1 to -1 else currentPos

}



fun solvePart1(input: List<String>): Int{
    val blockedMap = HashSet<Pair<Int,Int>>()

    input.forEach{ line ->
        val points = parsePath(line)
        updateRockWall(points, blockedMap)
    }
    val floorLevel = blockedMap.maxOf{ it.second }
    val wallUnits = blockedMap.size

    while(true){
        val newSand = simulateFalling(500 to 0, blockedMap, floorLevel)
        if(newSand == -1 to - 1)
            break
        else
            blockedMap.add(newSand)
    }

    return blockedMap.size - wallUnits
}


fun solvePart2(input: List<String>): Int{
    val blockedMap = HashSet<Pair<Int,Int>>()
    input.forEach{ line ->
        val points = parsePath(line)
        updateRockWall(points, blockedMap)
    }

    val floorLevel = blockedMap.maxOf{ it.second } + 2
    val wallUnits = blockedMap.size

    do{
        val newSand = simulateFalling2(500 to 0, blockedMap, floorLevel)
        blockedMap.add(newSand)
    } while(newSand != 500 to 0)

    return blockedMap.size - wallUnits
}





