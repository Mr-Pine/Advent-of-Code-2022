import common.AoCData

fun main() {

    val data = AoCData(
        filePath = "./day12/input.txt", """
            Sabqponm
            abcryxxl
            accszExk
            acctuvwj
            abdefghi
        """, exampleId = null
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

    var paths = listOf(start to 0)

    while (!paths.map(Pair<Pair<Int, Int>, Int>::first).contains(end)) {
        paths = paths.map { (end, length) ->
            end.surrounding().filter { it.first in 0..grid.lastIndex && it.second in 0..grid[0].lastIndex }.filter { grid[it] - grid[end] <= 1 }.map {
                it to length + 1
            }
        }.flatten().groupBy { it.first }.values.map { it.minBy { it.second } }
    }

    println(paths.find { it.first == end }!!.second)

}

operator fun <T> List<List<T>>.get(pair: Pair<Int, Int>) = get(pair.first)[pair.second]
operator fun <T> List<MutableList<T>>.set(pair: Pair<Int, Int>, value: T) = get(pair.first).set(pair.second, value)
fun Pair<Int, Int>.surrounding() =
    listOf(first - 1 to second, first + 1 to second, first to second + 1, first to second - 1)
