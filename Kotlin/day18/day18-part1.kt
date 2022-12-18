import common.AoCData
import kotlin.math.absoluteValue

fun main() {

    val data = AoCData(
        filePath = "./day18/input.txt",
        """
            2,2,2
            1,2,2
            3,2,2
            2,1,2
            2,3,2
            2,2,1
            2,2,3
            2,2,4
            2,2,6
            1,2,5
            3,2,5
            2,1,5
            2,3,5
        """,
        exampleIndex = null
    )

    val cubes = data.lines().map { it.split(",").let { Point3D(it[0].toInt(), it[1].toInt(), it[2].toInt()) } }

    var totalSides = cubes.size * 6

    cubes.forEach { cube ->
        totalSides -= (cubes - cube).count {
            it adjacent cube
        }
    }

    println(totalSides)

}

/*
data class Point3D(val x: Int, val y: Int, val z: Int) {
    infix fun adjacent(other: Point3D): Boolean {
        val xDistance = (x - other.x).absoluteValue
        val yDistance = (y - other.y).absoluteValue
        val zDistance = (z - other.z).absoluteValue

        return xDistance + yDistance + zDistance == 1
    }
}
*/
