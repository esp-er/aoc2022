enum class GameResult{
    LOSS, DRAW, WIN
}
enum class Hand{
    ROCK, PAPER, SCISSOR
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
    val hands = Pair(playerHand, opponentHand)

    //TODO: Find a more concise way to determine Game Result?
    val result = when(hands){
        Pair(Hand.ROCK, Hand.PAPER) -> GameResult.LOSS
        Pair(Hand.ROCK, Hand.ROCK) -> GameResult.DRAW
        Pair(Hand.ROCK, Hand.SCISSOR) -> GameResult.WIN
        Pair(Hand.PAPER, Hand.SCISSOR) -> GameResult.LOSS
        Pair(Hand.PAPER, Hand.PAPER) -> GameResult.DRAW
        Pair(Hand.PAPER, Hand.ROCK) -> GameResult.WIN
        Pair(Hand.SCISSOR, Hand.ROCK) -> GameResult.LOSS
        Pair(Hand.SCISSOR, Hand.SCISSOR) -> GameResult.DRAW
        Pair(Hand.SCISSOR, Hand.PAPER) -> GameResult.WIN
        else -> GameResult.DRAW
    }
    return result
}

fun calcPlayerScore(playerHand: Hand, result: GameResult): Int{
    val handScore = playerHand.ordinal + 1
    val gameScore = result.ordinal * 3
    return handScore + gameScore
}

fun solvePart1(inputList: List<String>): Int{
   val gamesScores = inputList.map{ gameInput ->
       val rawHands = gameInput.split(" ")
       if(rawHands.size == 2){
           val playerChar = rawHands.last().single()
           val opponentChar = rawHands.first().single()
           val gameResult = playGame(playerChar.toHand(), opponentChar.toHand())
           calcPlayerScore(playerChar.toHand(), gameResult)
       }
       else{
           0
       }
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
}


