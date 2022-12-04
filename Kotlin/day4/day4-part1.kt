import java.io.File

fun main(args: Array<String>) {
    /*val inputString = """
        2-4,6-8
        2-3,4-5
        5-7,7-9
        2-8,3-7
        6-6,4-6
        2-6,4-8
    """.trimIndent()*/

    val inputString = File("./day4/input.txt").readText().trim()

    val result = inputString.lines().map { it.split(",").map { it.split("-")}.map { it[0].toInt()..it[1].toInt() } }.count { it[0].contains(it[1]) || it[1].contains(it[0]) }

    println(result)
}

fun IntRange.contains(other: IntRange) = contains(other.first) && contains(other.last)
