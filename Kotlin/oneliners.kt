import java.io.File

fun main() {
    // Day 1
    println("1/1: ${File("./day1/input.txt").readText().trim().split("\n\n").maxOfOrNull { it.split("\n").sumOf(String::toInt) } }")
    println("1/2: ${File("./day1/input.txt").readText().trim().split("\n\n").map { it.split("\n").sumOf(String::toInt) }.sorted().takeLast(3).sum() }")

    // Day 2
    println("2/1: ${File("./day2/input.txt").readText().trim().split("\n").map { it.split(" ").let { it[0][0].code - 'A'.code to it[1][0].code - 'X'.code } }.sumOf { (opponent, self) -> (self - opponent + 1).mod(3) * 3 + (self + 1) }}")
    println("2/2: ${File("./day2/input.txt").readText().trim().split("\n").map { it.split(" ").let { it[0][0].code - 'A'.code to it[1][0].code - 'X'.code } }.sumOf { (opponent, strat) -> strat * 3 + ((opponent + strat + 2) % 3 + 1) }}")

    // Day 3
    println("3/1: ${File("./day3/input.txt").readText().trim().lines().map { it.chunked(it.length / 2) }.sumOf { halves -> halves[0].find { halves[1].contains(it) }!!.let { (('a'..'z') + ('A'..'Z')).indexOf(it) + 1 } } }")
    println("3/2: ${File("./day3/input.txt").readText().trim().lines().chunked(3).sumOf { elves -> elves[0].find { elves[1].contains(it) && elves[2].contains(it) }!!.let { (('a'..'z') + ('A'..'Z')).indexOf(it) + 1 } } }")

    // Day 4
    println("4/1: ${File("./day4/input.txt").readText().trim().lines().map { it.split(",").map { it.split("-") }.map { it[0].toInt()..it[1].toInt() } }.count { (it[0].contains(it[1].first) && it[0].contains(it[1].last)) || (it[1].contains(it[0].first) && it[1].contains(it[0].last)) }}")
    println("4/2: ${File("./day4/input.txt").readText().trim().lines().map { it.split(",").map { it.split("-") }.map { it[0].toInt()..it[1].toInt() } }.count { (it[0].contains(it[1].first) || it[0].contains(it[1].last)) || (it[1].contains(it[0].first) || it[1].contains(it[0].last)) }}")

    // Day 5
    println("5/1: ${File("./day5/input.txt").readText().trim().split("\n\n").let { it[0].lines().let { it.subList(0, it.size -1) }.reversed().map { it.chunked(4).map { it[1] } }.let { it[0].indices.map { stackIndex -> it.map { it[stackIndex] }.filter { it != ' ' }.toMutableList() } } to it[1].lines().map { it.split(" ").let { Triple(it[1].toInt(), it[3].toInt() - 1, it[5].toInt() -1) } } }.let { (stacks, instructions) -> stacks.apply { instructions.forEach { (amount, from, to) -> stacks[to].addAll((0 until amount).map { stacks[from].removeLast() }) } }.joinToString("") { it.last().toString() } } }")
    println("5/2: ${File("./day5/input.txt").readText().trim().split("\n\n").let { it[0].lines().let { it.subList(0, it.size -1) }.reversed().map { it.chunked(4).map { it[1] } }.let { it[0].indices.map { stackIndex -> it.map { it[stackIndex] }.filter { it != ' ' }.toMutableList() } } to it[1].lines().map { it.split(" ").let { Triple(it[1].toInt(), it[3].toInt() - 1, it[5].toInt() -1) } } }.let { (stacks, instructions) -> stacks.apply { instructions.forEach { (amount, from, to) -> stacks[to].addAll((0 until amount).map { stacks[from].removeLast() }.reversed()) } }.joinToString("") { it.last().toString() } } }")

    // Day 6
    println("6/1: ${File("./day6/input.txt").readText().trim().windowed(4).indexOfFirst { it.toCharArray().distinct().size == it.length } + 4}")
    println("6/2: ${File("./day6/input.txt").readText().trim().windowed(14).indexOfFirst { it.toCharArray().distinct().size == it.length } + 14}")
}
