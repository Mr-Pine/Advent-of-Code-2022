import java.io.File
fun main() {
    println(File("./day4/input.txt").readText().lines().map { it.split(",").map { it.split("-") }.map { it[0].toInt()..it[1].toInt() } }.count { (it[0].contains(it[1].first) && it[0].contains(it[1].last)) || (it[1].contains(it[0].first) && it[1].contains(it[0].last)) })
}
