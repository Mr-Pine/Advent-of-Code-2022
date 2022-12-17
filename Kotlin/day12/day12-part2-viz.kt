import com.varabyte.kotter.foundation.input.onKeyPressed
import com.varabyte.kotter.foundation.session
import com.varabyte.kotter.foundation.text.*
import common.AoCData
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.lang.Integer.max

fun main() {

    val data = AoCData(
        filePath = "./day12/input.txt", """
            Sabqponm
            abcryxxl
            accszExk
            acctuvwj
            abdefghi
        """, exampleIndex = null
    )

    val grid = data.lines().map { it.asSequence().toMutableList() }

    val start = grid.first { it.contains('S') }.let {
        grid.indexOf(it) to it.indexOf('S')
    }

    val end = grid.first { it.contains('E') }.let {
        grid.indexOf(it) to it.indexOf('E')
    }

    grid[start] = 'a'
    grid[end] = 'z'

    var tileAges = List(grid.size * grid[0].size) {-1}.toMutableList()

    val lowest = grid.indices.map { row ->
        grid[0].indices.map { row to it }
    }.flatten().filter { grid[it] == 'a' }

    var paths = listOf(end to 0)
    var newPaths = paths

    runBlocking {
        session {
            section {
                grid.forEachIndexed { rowIndex, row ->
                    row.forEachIndexed { columnIndex, column ->
                        if ((rowIndex to columnIndex) == start || (rowIndex to columnIndex) in lowest) {
                            hsv(118, 1f, .30f) {
                                text('a')
                            }
                        } else if ((rowIndex to columnIndex) == end) {
                            red {
                                text('E')
                            }
                        } else if ((rowIndex to columnIndex) in paths.map { it.first }) {
                            hsv(210, 1f, 0.68f * (1f/max(tileAges[rowIndex * grid.size + columnIndex], 1)) + 0.32f) {
                                text(column)
                            }
                        } else {
                            hsv(0, 0f, .5f) {
                                text(column)
                            }
                        }
                    }
                    text("\n")
                }
            }.run {
                onKeyPressed { signal() }
                waitForSignal()

                while (!paths.any { it.first in lowest }) {
                    val oldPaths = paths
                    paths = (newPaths.map { (pathEnd, length) ->
                        pathEnd.surrounding()
                            .filter { it.first in 0..grid.lastIndex && it.second in 0..grid[0].lastIndex }
                            .filter { grid[pathEnd] - grid[it] <= 1 }.map {
                                it to length + 1
                            }
                    }.flatten() + paths).groupBy { it.first }.values.map { it.minBy { it.second } }
                    newPaths = paths - oldPaths
                    newPaths.forEach { (coords, _) -> tileAges[coords.first * grid.size + coords.second] = 0 }
                    tileAges = tileAges.map { if(it < 0) it else (it + 1) }.toMutableList()
                    delay(30)
                    rerender()
                }
            }
        }
    }
}

/*
operator fun <T> List<List<T>>.get(pair: Pair<Int, Int>) = get(pair.first)[pair.second]
operator fun <T> List<MutableList<T>>.set(pair: Pair<Int, Int>, value: T) = get(pair.first).set(pair.second, value)
fun Pair<Int, Int>.surrounding() =
    listOf(first - 1 to second, first + 1 to second, first to second + 1, first to second - 1)
*/
