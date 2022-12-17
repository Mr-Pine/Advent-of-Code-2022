import common.AoCData
import common.manhattanDistance

fun main() {

    val data = AoCData(
        filePath = "./day15/input.txt", """
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
        """, exampleIndex = null
    )

    val signalBeacons = data.lines()
        .map { it.split(":").map { it.split("x=")[1].split(", y=").map { it.toInt() }.let { it[0] to it[1] } } }

    val distanceMap = signalBeacons.map { it[0] to it[0].manhattanDistance(it[1]) }

    val xRange = 0..4000000
    val yRange = 0..4000000

    distanceMap.forEach { (sensor, distance) ->
        (0..distance + 1).forEach { x ->
            val y = distance + 1 - x
            listOf(x to y, x to -y, -x to y, -x to -y).map { sensor + it }.forEach { point ->
                if (point.first in xRange && point.second in yRange && distanceMap.none {
                        point.manhattanDistance(it.first) <= it.second
                    }
                ) {
                    println(point)
                    println(point.first.toLong() * 4000000 + point.second.toLong())
                }
            }
        }
    }
}
