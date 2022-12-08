package patriker.day08
import patriker.utils.*

fun main() {
    val testInput = readInput("Day08_test")
    val testWidth = testInput.first().length
    val testHeight = testInput.size

    val testArr =
        Array(testHeight) {
            IntArray(testWidth)
        }
    testInput.forEachIndexed{ i, line ->
        line.forEachIndexed{ j, digit ->
            testArr[i][j] = "$digit".toInt()
        }

    }

    val input = readInput("Day08_input")
    val width = input.first().length
    val height = input.size


    val inputArr =
        Array(height) {
            IntArray(width)
        }
    input.forEachIndexed{ i, line ->
        line.forEachIndexed{ j, digit ->
            inputArr[i][j] = "$digit".toInt()
        }

    }



    println(solvePart1(testArr))
    println(solvePart1(inputArr))

    //println(solvePart1(input))
    //println(solvePart2(input).sum())
}

fun isVisible(row: Int, col: Int, grid: Array<IntArray>): Boolean{

    if(row == 0 || row == grid.size-1)
        return true
    if(col == 0 || col == grid.first().size-1)
        return true

    val tree = grid[row][col]

    val northNotVisible = (0 until row).any{ grid[it][col] >= tree }
    val southNotVisible = (row + 1 until grid.size).any{ grid[it][col] >= tree }
    val westNotVisible = (0 until col).any{ grid[row][it] >= tree }
    val eastNotVisible = (col + 1 until grid[0].size).any{ grid[row][it] >= tree }

    return !(northNotVisible && southNotVisible && westNotVisible && eastNotVisible)

}

fun solvePart1(input: Array<IntArray>): Int{

    return input.indices.sumOf{ i ->
        input[i].indices.count{j ->
            isVisible(i,j, input)
        }
    }

}


fun solvePart2(input: String): List<Int>{
    TODO()
}

