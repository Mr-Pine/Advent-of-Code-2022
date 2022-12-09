import common.AoCData
import java.lang.Integer.min
import kotlin.math.absoluteValue
import kotlin.math.sign

fun main() {

    val data = AoCData(
        filePath = "./day9/input.txt",
        """
            R 4
            U 4
            L 3
            D 1
            R 4
            D 1
            L 5
            R 2
        """,
        exampleId = null
    )

    val moves = mapOf("R" to (1 to 0), "L" to (-1 to 0), "U" to (0 to 1), "D" to (0 to -1))


    val knotsInitial = List(10) { 0 to 0 }

    val result = data.lines().map { it.split(" ").let { instruction -> List(instruction[1].toInt()) { instruction[0] } } }
        .flatten()
        .scan(knotsInitial) { knots, move ->
            knots.drop(1)
                .scan(knots[0] + moves[move]!!) { previousKnot, currentKnot ->
                    val touching =
                        (previousKnot.first - currentKnot.first).absoluteValue <= 1 && (previousKnot.second - currentKnot.second).absoluteValue <= 1
                    if (!touching) {
                        currentKnot + ((previousKnot.first - currentKnot.first).let { it.sign * (min(it.absoluteValue, 1)) } to
                                (previousKnot.second - currentKnot.second).let { it.sign * min(it.absoluteValue, 1) })
                    } else {
                        currentKnot
                    }
                }
        }.map { it.last() }.toSet()

    println(result.size)

}

// See Part 1
// operator fun Pair<Int, Int>.plus(other: Pair<Int, Int>) = first + other.first to second + other.second