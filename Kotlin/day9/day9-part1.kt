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


    var head = 0 to 0
    var tail = 0 to 0
    val visited = mutableSetOf(0 to 0)

    val result =
        data.lines().map { it.split(" ").let { instruction -> List(instruction[1].toInt()) { instruction[0] } } }
            .flatten()
            .forEach { move ->
                head += moves[move]!!
                val touching = (head.first - tail.first).absoluteValue <= 1 && (head.second - tail.second).absoluteValue <= 1
                if(!touching) {
                    tail += (head.first - tail.first).let { it.sign * (min(it.absoluteValue, 1)) } to
                            (head.second - tail.second).let { it.sign * min(it.absoluteValue, 1) }

                    visited.add(tail)
                }
            }

    println(visited)
    println(visited.size)

}

operator fun Pair<Int, Int>.plus(other: Pair<Int, Int>) = first + other.first to second + other.second