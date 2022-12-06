import java.io.File

fun main() {
    // Day 1
    println("1/1: ${File("./day1/input.txt").readText().trim().split("\n\n").maxOfOrNull { it.split("\n").sumOf(String::toInt) } }")
    println("1/2: ${File("./day1/input.txt").readText().trim().split("\n\n").map { it.split("\n").sumOf(String::toInt) }.sorted().takeLast(3).sum() }")
}
