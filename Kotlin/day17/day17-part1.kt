import common.AoCData
import common.Point

fun main() {

    val data = AoCData(
        filePath = "./day17/input.txt",
        ">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>",
        exampleIndex = null
    )

    val jets = data.currentString()

    var rockCount = 0
    var step = 0
    var currentRock = Rock(mutableSetOf(), 0, 0)
    val filled = mutableSetOf<Point>()
    var highest: Int

    while (rockCount < 2022) {
        highest = filled.maxOfOrNull { it.second } ?: -1
        currentRock = rocks[rockCount % rocks.size].move(2, highest + 4)
        while (!currentRock.settled) {
            //println(visualize(filled, currentRock) + "\n-------\n")
            currentRock.move(if(jets[step % jets.length] == '>') 1 else -1, 0).let {
                if(!it.collide(rocks = filled)) currentRock = it
            }
            step++
            currentRock.move(0, -1).let {
                if(it.collide(rocks = filled)) {
                    currentRock.settled = true
                } else {
                    currentRock = it
                }
            }
        }
        filled += currentRock.points
        rockCount++
    }

    println(visualize(filled, currentRock))

    println(filled.maxOf { it.second } + 1)


}

val rocks = listOf(
    /*####*/
    Rock(mutableSetOf(0 to 0, 1 to 0, 2 to 0, 3 to 0), 1, 4),
    /*
    .#.
    ###
    .#.
     */
    Rock(mutableSetOf(1 to 0, 0 to 1, 1 to 1, 2 to 1, 1 to 2), 3, 3),
    /*
    ..#
    ..#
    ###
     */
    Rock(mutableSetOf(0 to 0, 1 to 0, 2 to 0, 2 to 1, 2 to 2), 3, 3),
    /*
    #
    #
    #
    #
     */
    Rock(mutableSetOf(0 to 0, 0 to 1, 0 to 2, 0 to 3), 4, 1),
    /*
    ##
    ##
     */
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
    (0..(rock.points + filled).maxOf { it.second }).mapIndexed { rowIndex, row ->
        rowIndex.toString().padStart(3) + " |" +  (0..6).joinToString("") { if(filled.contains(it to row)) "#" else if (rock.points.contains(it to row)) "@" else "." } + "|"
    }.reversed().joinToString("\n")
