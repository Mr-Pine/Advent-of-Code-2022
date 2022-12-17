import common.AoCData
import common.abs
import common.manhattanDistance
import common.minus
import kotlin.math.sign

fun main() {

    val data = AoCData(
        filePath = "./day15/input.txt",
        """
            Sensor at x=2, y=18: closest beacon is at x=-2, y=15
            Sensor at x=9, y=16: closest beacon is at x=10, y=16
            Sensor at x=13, y=2: closest beacon is at x=15, y=3
            Sensor at x=12, y=14: closest beacon is at x=10, y=16
            Sensor at x=10, y=20: closest beacon is at x=10, y=16
            Sensor at x=14, y=17: closest beacon is at x=10, y=16
            Sensor at x=8, y=7: closest beacon is at x=2, y=10
            Sensor at x=2, y=0: closest beacon is at x=2, y=10
            Sensor at x=0, y=11: closest beacon is at x=2, y=10
            Sensor at x=20, y=14: closest beacon is at x=25, y=17
            Sensor at x=17, y=20: closest beacon is at x=21, y=22
            Sensor at x=16, y=7: closest beacon is at x=15, y=3
            Sensor at x=14, y=3: closest beacon is at x=15, y=3
            Sensor at x=20, y=1: closest beacon is at x=15, y=3
        """,
        exampleId = null
    )

    val signalBeacons = data.lines()
        .map { it.split(":").map { it.split("x=")[1].split(", y=").map { it.toInt() }.let { it[0] to it[1] } } }

    val distanceMap = signalBeacons.map { it[0] to (it[0] - it[1]).abs.let { it.first + it.second } }

    val maxDistance = distanceMap.maxBy { it.second }.second
    val minX = distanceMap.minBy { it.first.first }.first.first
    val maxX = distanceMap.maxBy { it.first.first }.first.first

    val start = minX - maxDistance to 2000000
    val end = maxX + maxDistance to 2000000

    val xRange = minX - maxDistance .. maxX + maxDistance

    val devices = signalBeacons.flatten().toSet().filter { it.second == 2000000 }

    val occupied = xRange.count {
        val point = it to 2000000
        point !in devices && distanceMap.any { (sensor, distance) ->
            point.manhattanDistance(sensor) <= distance
        }
    }

    /*val occupied = signalBeacons.map { it[0] to (it[0] - it[1]).abs.let { it.first + it.second } }.map { (receiver, distance) ->
        (0..distance).map {  xAbs ->
            (xAbs..distance).map { smallDistance ->
                val yAbs = smallDistance - xAbs
                setOf(receiver + (xAbs to yAbs), receiver + (-xAbs to yAbs), receiver + (xAbs to -yAbs), receiver + (-xAbs to -yAbs))
            }.flatten().toSet()
        }.flatten().toSet()
    }.flatten().toSet() - signalBeacons.flatten().toSet()*/

    println(occupied)

}
