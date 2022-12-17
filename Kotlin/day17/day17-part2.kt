import common.AoCData
import common.Point

fun main() {

    val data = AoCData(
        filePath = "./day17/input.txt",
        ">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>",
        exampleIndex = null
    )

    val jets = data.currentString()

    var rockCount = 0L
    var step = 0
    var currentRock = Rock(mutableSetOf(), 0, 0)
    var filled = mutableSetOf<Point>()
    var highest: Int

    var heightAdd = 0L

    val blocks = mutableMapOf<Pair<Set<Point>, Int>, Pair<Int, Long>>()

    val maxRocks = 1000000000000L

    var blockStart = setOf<Point>()
    var blockStartCount = 0L
    var blockStartRock = 0

    var end = false

    while (rockCount < maxRocks) {
        highest = filled.maxOfOrNull { it.second } ?: -1
        currentRock = rocks[(rockCount % rocks.size).toInt()].move(2, highest + 4)
        while (!currentRock.settled) {
            currentRock.move(if (jets[step % jets.length] == '>') 1 else -1, 0).let {
                if (!it.collide(rocks = filled)) currentRock = it
            }
            step++
            currentRock.move(0, -1).let {
                if (it.collide(rocks = filled)) {
                    currentRock.settled = true
                } else {
                    currentRock = it
                }
            }
        }
        filled += currentRock.points
        //println(visualize(filled, currentRock) + "\n---------------------\n")
        if(!end) currentRock.points.maxOf { it.second }.let {
            ((it - currentRock.height)..it).lastOrNull { y ->
                (0..6).all { filled.contains(it to y) }
            }?.let { height ->
                val remaining = filled.filter { it.second > height }.map { it.first to it.second - height - 1 }.toMutableSet()
                heightAdd += it
                if (blocks.contains(remaining to (step % rocks.size))) {
                    blocks[remaining to (step % rocks.size)]!!.let {
                        val count = (maxRocks - rockCount) / it.second
                        heightAdd += it.first * count
                        rockCount += it.second * count
                    }
                    end = true
                    heightAdd += 1
                } else {
                    blocks[blockStart to blockStartRock] = height to (rockCount - blockStartCount)
                }
                filled = remaining
                blockStart = remaining.toSet()
                blockStartRock = (step % rocks.size)
                blockStartCount = rockCount
            }
        }
        rockCount++
    }

    println(visualize(filled, currentRock))
    println(blocks.size)

    println(filled.maxOf { it.second } + 1 + heightAdd)


}

/*
val rocks = listOf(
    */
/*####*//*

    Rock(mutableSetOf(0 to 0, 1 to 0, 2 to 0, 3 to 0), 1, 4),
    */
/*
    .#.
    ###
    .#.
     *//*

    Rock(mutableSetOf(1 to 0, 0 to 1, 1 to 1, 2 to 1, 1 to 2), 3, 3),
    */
/*
    ..#
    ..#
    ###
     *//*

    Rock(mutableSetOf(0 to 0, 1 to 0, 2 to 0, 2 to 1, 2 to 2), 3, 3),
    */
/*
    #
    #
    #
    #
     *//*

    Rock(mutableSetOf(0 to 0, 0 to 1, 0 to 2, 0 to 3), 4, 1),
    */
/*
    ##
    ##
     *//*

    Rock(mutableSetOf(0 to 0, 0 to 1, 1 to 0, 1 to 1), 2, 2)
)

data class Rock(var points: MutableSet<Point>, val height: Int, val width: Int, var settled: Boolean = false) {
    fun clone() = copy(points = points.toMutableSet())
    fun collide(width: Int = 7, floor: Int = -1, rocks: Set<Point>) =
        points.any { it.first >= width || it.first < 0 || it.second <= floor || rocks.contains(it) }

    fun moveVertical(amount: Int) {
        points = points.map { it + (0 to amount) }.toMutableSet()
    }

    fun moveHorizontal(amount: Int) {
        points = points.map { it + (amount to 0) }.toMutableSet()
    }

    fun move(horizontal: Int, vertical: Int) =
        copy(points = points.map { it + (horizontal to vertical) }.toMutableSet())
}

fun visualize(filled: Set<Point>, rock: Rock) =
    (0..rock.points.maxOf { it.second }).map { row ->
        (0..6).joinToString("") { if(filled.contains(it to row)) "#" else if (rock.points.contains(it to row)) "@" else "." }
    }.reversed().joinToString("\n")
*/
