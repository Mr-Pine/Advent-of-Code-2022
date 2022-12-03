import java.io.File

fun main(args: Array<String>) {
    /*val inputString = """
        A Y
        B X
        C Z
    """.trimIndent()*/

    val inputString = File("./src/main/resources/2-input.txt").readText().trim()

    val result = inputString.split(System.lineSeparator()).map { line ->
        line.split(" ")}.map {
        val opponent = RockPaperScissors2.parse(it[0])
        when(it[1]) {
            "X" -> 0 + opponent.loser().score
            "Y" -> 3 + opponent.score
            "Z" -> 6 + opponent.winner().score
            else -> 0
        }
    }.sum()

    println(result)
}


enum class RockPaperScissors2(val score: Int) {
    ROCK(1),
    PAPER(2),
    SCISSORS(3),
    UNKNOWN(-1);

    fun winner(): RockPaperScissors2 = values()[(this.ordinal + 1) % 3]
    fun loser(): RockPaperScissors2 = values()[(this.ordinal + 2) % 3]

    companion object {
        fun parse(from: String): RockPaperScissors2 {
            return when (from) {
                "A" -> ROCK
                "B" -> PAPER
                "C" -> SCISSORS
                else -> UNKNOWN
            }
        }
    }


}
