package day2
import java.io.File

fun main(args: Array<String>) {
    /*val inputString = """
        A Y
        B X
        C Z
    """.trimIndent()*/

    /*val inputString = """
        B X
        C Y
        A X
        B X
        B Y
        B X
        A Z
        A Z
        A X
        C X
        C X
        B X
        B X
        C X
        C Y
        A Z
        B Y
        C Y
        C X
        B X
    """.trimIndent()*/

    /*
    0+1 1
    0+2 2 3
    3+1 4 7
    0+1 1 8
    3+2 5 13
    0+1 1 14
    0+3 3 17
    0+3 3 20
    0+1
    6+1
    6+1
    0+1
    0+1
    6+1
    0+2
    0+3
    3+2
    0+2
    6+1
    0+2
     */

    val inputString = File("./src/main/resources/2-input.txt").readText().trim()

    val result = inputString.split(System.lineSeparator()).map { line ->
        line.split(" ").map { RockPaperScissors.parse(it)
        } }.map {
        (it[1] play it[0]) + it[1]
            .score
    }.sum()

    println(result)
}

private infix fun RockPaperScissors.play(other: RockPaperScissors): Int {
    if (this == other) return 3
    return if (other.ordinal == (this.ordinal + 2) % 3) 6
    else 0
}


enum class RockPaperScissors(val score: Int) {
    ROCK(1),
    PAPER(2),
    SCISSORS(3),
    UNKNOWN(-1);

    companion object {
        fun parse(from: String): RockPaperScissors {
            return when (from) {
                "A", "X" -> ROCK
                "B", "Y" -> PAPER
                "C", "Z" -> SCISSORS
                else -> UNKNOWN
            }
        }
    }
}
