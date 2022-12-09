package patriker.day08
import patriker.utils.*

fun main() {
    val testInput = readInput("Day08_test")

    val testGrid = createGridArray(testInput)

    val input = readInput("Day08_input")
    val inputGrid = createGridArray(input)

    println(solvePart1(testGrid))
    println(solvePart1(inputGrid))

    println("\nPart 2")
    println(solvePart2(testGrid))
    println(solvePart2(inputGrid))
}

fun createGridArray(input: List<String>): Array<IntArray>{
    val grid = Array(input.size) {
        IntArray(input.first().length)
    }
    input.forEachIndexed{ i, line ->
        line.forEachIndexed{ j, c ->
            grid[i][j] = c.digitToInt()
        }
    }

    return grid
}

fun isVisible(row: Int, col: Int, grid: Array<IntArray>): Boolean{
    if(row == 0 || row == grid.size-1)
        return true
    if(col == 0 || col == grid.first().size-1)
        return true

    val treeHeight = grid[row][col]

    val northHidden = (0 until row).any{ grid[it][col] >= treeHeight }
    if(!northHidden) return true

    val southHidden = (row + 1 until grid.size).any{ grid[it][col] >= treeHeight }
    if(!southHidden) return true

    val westHidden = (0 until col).any{ grid[row][it] >= treeHeight }
    if(!westHidden) return true

    val eastHidden = (col + 1 until grid[0].size).any{ grid[row][it] >= treeHeight }
    if(!eastHidden) return true

    return false
}

fun scenicScore(row: Int, col: Int, grid: Array<IntArray>): Int{
    val northRange = (row -1 downTo  0 )
    val southRange = (row + 1 until grid.size)
    val westRange = (col - 1  downTo 0)
    val eastRange = (col+ 1 until grid[0].size)

    val treeHeight = grid[row][col]

    var northVisible = 0
    for(i in northRange){
        northVisible++
        if(grid[i][col] >= treeHeight)
            break
    }

    var southVisible = 0
    for(i in southRange){
        southVisible++
        if(grid[i][col] >= treeHeight)
            break
    }

    var westVisible = 0
    for(j in westRange){
        westVisible++
        if(grid[row][j] >= treeHeight)
            break
    }

    var eastVisible = 0
    for(j in eastRange){
        eastVisible++
        if(grid[row][j] >= treeHeight)
            break
    }
    return northVisible * southVisible * westVisible * eastVisible
}

fun solvePart1(input: Array<IntArray>): Int{
    return input.indices.sumOf{ i ->
        input[i].indices.count{ j ->
            isVisible(i,j, input)
        }
    }
}

fun solvePart2(input: Array<IntArray>): Int{
    return input.indices.maxOf{ i ->
        input[i].indices.maxOf{ j ->
            scenicScore(i,j, input)
        }
    }
}