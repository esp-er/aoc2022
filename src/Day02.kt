package patriker.day02
import patriker.utils.*
operator fun String.component1(): Char { return this[0] }
operator fun String.component2(): Char { return this[1] }
operator fun String.component3(): Char { return this[2] }

enum class GameResult(val score: Int){
    LOSS(0), DRAW(3), WIN(6)
}
enum class Hand{
    ROCK, PAPER, SCISSOR
}

fun Hand.losesAgainst(): Hand{
    return when(this){
        Hand.ROCK -> Hand.PAPER
        Hand.PAPER -> Hand.SCISSOR
        Hand.SCISSOR -> Hand.ROCK
    }
}

fun Hand.winsAgainst(): Hand{
    return when(this){
        Hand.ROCK -> Hand.SCISSOR
        Hand.PAPER -> Hand.ROCK
        Hand.SCISSOR -> Hand.PAPER
    }
}
fun Char.toHand(): Hand{
    return when(this){
        'A', 'X' -> Hand.ROCK
        'B', 'Y' -> Hand.PAPER
        'C', 'Z' -> Hand.SCISSOR
        else -> Hand.ROCK
    }
}

fun playGame(playerHand: Hand, opponentHand: Hand): GameResult{
    val result = when{
        playerHand == opponentHand -> GameResult.DRAW
        playerHand.losesAgainst() == opponentHand -> GameResult.LOSS
        playerHand.winsAgainst() == opponentHand -> GameResult.WIN
        else -> GameResult.DRAW
    }
    return result
}

fun calcPlayerScore(playerHand: Hand, result: GameResult): Int{
    val handScore = playerHand.ordinal + 1
    val gameScore = result.score
    return handScore + gameScore
}


fun handToPlay(playerResult: Char, opponentHand: Hand): Hand{
    return when(playerResult){
        'X' -> opponentHand.winsAgainst()
        'Y' ->  opponentHand
        'Z' -> opponentHand.losesAgainst()
        else -> opponentHand
    }

}

fun solvePart1(inputList: List<String>): Int{
   val gamesScores = inputList.asSequence().filter(String::isNotBlank).map{ gameInput ->
       val (opponentChar, _, playerChar) = gameInput
       val gameResult = playGame(playerChar.toHand(), opponentChar.toHand())
       calcPlayerScore(playerChar.toHand(), gameResult)
   }
   return gamesScores.sum()
}

fun solvePart2(inputList: List<String>): Int{
    val gamesScores = inputList.asSequence().filter(String::isNotBlank).map{ gameInput ->
        val (opponentChar, _, playerResult) = gameInput
        val opponentHand = opponentChar.toHand()
        val playerHand = handToPlay(playerResult, opponentHand)

        val gameResult = playGame(playerHand, opponentHand)
        calcPlayerScore(playerHand, gameResult)
    }
    return gamesScores.sum()
}


fun main() {
    // test if implementation meets criteria from the description, like:
    println("::day2:: part 1 :: ")
    val testInput = readInput("Day02_test")
    check(solvePart1(testInput) == 15)

    val input = readInput("Day02_input")
    println(solvePart1(input))


    println("::day2:: part 2 :: ")
    check(solvePart2(testInput) == 12)
    println(solvePart2(input))
}


