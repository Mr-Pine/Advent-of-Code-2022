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

    val xRange = (cubes.minOf { it.x } - 1)..(cubes.maxOf { it.x } + 1)
    val yRange = (cubes.minOf { it.y } - 1)..(cubes.maxOf { it.y } + 1)
    val zRange = (cubes.minOf { it.z } - 1)..(cubes.maxOf { it.z } + 1)

    val droplet = xRange.map { x ->
        yRange.map { y ->
            zRange.map { z ->
                Point3D(x, y, z)
            }
        }.flatten()
    }.flatten().filter { cube ->
        cube in cubes ||
                (cubes.any { it.x > cube.x && it.y == cube.y && it.z == cube.z } &&
                        cubes.any { it.x < cube.x && it.y == cube.y && it.z == cube.z } &&
                        cubes.any { it.x == cube.x && it.y > cube.y && it.z == cube.z } &&
                        cubes.any { it.x == cube.x && it.y < cube.y && it.z == cube.z } &&
                        cubes.any { it.x == cube.x && it.y == cube.y && it.z > cube.z } &&
                        cubes.any { it.x == cube.x && it.y == cube.y && it.z < cube.z }
                        )
    }

    var totalSides = droplet.size * 6

    println(droplet.groupBy { it.z }.toList().sortedBy { it.first }.joinToString("\n\n") { (z, points) ->
        yRange.joinToString("\n") { y ->
            xRange.joinToString("") { if(Point3D(it,y,z) in points) "#" else " " }
        }
    })

    droplet.forEach { cube ->
        totalSides -= (droplet - cube).count {
            it adjacent cube
        }
    }

    println(totalSides)
}

data class Point3D(val x: Int, val y: Int, val z: Int) {
    infix fun adjacent(other: Point3D): Boolean {
        val xDistance = (x - other.x).absoluteValue
        val yDistance = (y - other.y).absoluteValue
        val zDistance = (z - other.z).absoluteValue

        return xDistance + yDistance + zDistance == 1
    }

    val adjacentCubes: List<Point3D>
        get() = listOf(
            Point3D(x + 1, y, z),
            Point3D(x - 1, y, z),
            Point3D(x, y + 1, z),
            Point3D(x, y - 1, z),
            Point3D(x, y, z + 1),
            Point3D(x, y, z - 1),
        )
}
