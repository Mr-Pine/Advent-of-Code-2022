import common.AoCData
import kotlin.math.max
import kotlin.math.min

fun main() {

    val data = AoCData(
        filePath = "./day14/input.txt",
        """
            498,4 -> 498,6 -> 496,6
            503,4 -> 502,4 -> 502,9 -> 494,9
        """,
        exampleId = null
    )

    val filled = data.lines()
        .map { it.split(" -> ").windowed(2).map { it.map { it.split(",").let { it[0].toInt() to it[1].toInt() } } } }
        .flatten().map {
            if (it[0].first == it[1].first) {
                it[0].second.let { end1 ->
                    it[1].second.let { end2 ->
                        (min(end1, end2)..max(
                            end1,
                            end2
                        )).map { y -> (it[0].first to y) }
                    }
                }
            } else {
                it[0].first.let { end1 ->
                    it[1].first.let { end2 ->
                        (min(end1, end2)..max(
                            end1,
                            end2
                        )).map { x -> (x to it[0].second) }
                    }
                }
            }
        }.flatten().toMutableSet()

    val lowest = filled.maxBy { it.second }.second
    val floor = lowest + 2

    var currentSand = 500 to 0
    var wallSize = filled.size

    while ((500 to 0) !in filled) {
        val down = currentSand.first to currentSand.second + 1
        val downLeft = currentSand.first - 1 to currentSand.second + 1
        val downRight = currentSand.first + 1 to currentSand.second + 1


        currentSand = if (down !in filled && down.second < floor) {
            down
        } else if (downLeft !in filled && down.second < floor) {
            downLeft
        } else if (downRight !in filled && down.second < floor) {
            downRight
        } else {
            filled.add(currentSand)
            (500 to 0)
        }
    }

    println(filled.size - wallSize)

}