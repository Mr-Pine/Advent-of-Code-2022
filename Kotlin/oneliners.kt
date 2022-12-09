import java.io.File
import kotlin.math.absoluteValue
import kotlin.math.sign

fun main() {
    // Day 1
    println("1/1: " + File("./day1/input.txt").readText().trim().split("\n\n").maxOfOrNull { it.split("\n").sumOf(String::toInt) })
    println("1/2: " + File("./day1/input.txt").readText().trim().split("\n\n").map { it.split("\n").sumOf(String::toInt) }.sorted().takeLast(3).sum())

    // Day 2
    println("2/1: " + File("./day2/input.txt").readText().trim().split("\n").map { it.split(" ").let { it[0][0].code - 'A'.code to it[1][0].code - 'X'.code } }.sumOf { (opponent, self) -> (self - opponent + 1).mod(3) * 3 + (self + 1) })
    println("2/2: " + File("./day2/input.txt").readText().trim().split("\n").map { it.split(" ").let { it[0][0].code - 'A'.code to it[1][0].code - 'X'.code } }.sumOf { (opponent, strat) -> strat * 3 + ((opponent + strat + 2) % 3 + 1) })

    // Day 3
    println("3/1: " + File("./day3/input.txt").readText().trim().lines().map { it.chunked(it.length / 2) }.sumOf { halves -> halves[0].find { halves[1].contains(it) }!!.let { (('a'..'z') + ('A'..'Z')).indexOf(it) + 1 } })
    println("3/2: " + File("./day3/input.txt").readText().trim().lines().chunked(3).sumOf { elves -> elves[0].find { elves[1].contains(it) && elves[2].contains(it) }!!.let { (('a'..'z') + ('A'..'Z')).indexOf(it) + 1 } })

    // Day 4
    println("4/1: " + File("./day4/input.txt").readText().trim().lines().map { it.split(",").map { it.split("-") }.map { it[0].toInt()..it[1].toInt() } }.count { (it[0].contains(it[1].first) && it[0].contains(it[1].last)) || (it[1].contains(it[0].first) && it[1].contains(it[0].last)) })
    println("4/2: " + File("./day4/input.txt").readText().trim().lines().map { it.split(",").map { it.split("-") }.map { it[0].toInt()..it[1].toInt() } }.count { (it[0].contains(it[1].first) || it[0].contains(it[1].last)) || it[1].contains(it[0].first) })

    // Day 5
    println("5/1: " + File("./day5/input.txt").readText().trim().split("\n\n").let { it[0].lines().let { it.subList(0, it.size -1) }.reversed().map { it.chunked(4).map { it[1] } }.let { it[0].indices.map { stackIndex -> it.map { it[stackIndex] }.filter { it != ' ' }.toMutableList() } } to it[1].lines().map { it.split(" ").let { Triple(it[1].toInt(), it[3].toInt() - 1, it[5].toInt() -1) } } }.let { (stacks, instructions) -> stacks.apply { instructions.forEach { (amount, from, to) -> stacks[to].addAll((0 until amount).map { stacks[from].removeLast() }) } }.joinToString("") { it.last().toString() } })
    println("5/2: " + File("./day5/input.txt").readText().trim().split("\n\n").let { it[0].lines().let { it.subList(0, it.size -1) }.reversed().map { it.chunked(4).map { it[1] } }.let { it[0].indices.map { stackIndex -> it.map { it[stackIndex] }.filter { it != ' ' }.toMutableList() } } to it[1].lines().map { it.split(" ").let { Triple(it[1].toInt(), it[3].toInt() - 1, it[5].toInt() -1) } } }.let { (stacks, instructions) -> stacks.apply { instructions.forEach { (amount, from, to) -> stacks[to].addAll((0 until amount).map { stacks[from].removeLast() }.reversed()) } }.joinToString("") { it.last().toString() } })

    // Day 6
    println("6/1: " + File("./day6/input.txt").readText().trim().windowed(4).indexOfFirst { it.toCharArray().distinct().size == it.length } + 4)
    println("6/2: " + File("./day6/input.txt").readText().trim().windowed(14).indexOfFirst { it.toCharArray().distinct().size == it.length } + 14)

    // Day 7
    println("7/1: " + File("./day7/input.txt").readText().trim().split("$").map(String::trim).fold(listOf<String>() to listOf<Pair<String, Int>>()){ (pathStack, dirs), currentInstruction -> currentInstruction.lines().let{ lines -> (lines[0].split(" ").takeIf { it[0]  == "cd" }?.let{ it[1].takeIf { dir -> dir != ".." }?.let { pathStack + it } ?: pathStack.dropLast(1) } ?: pathStack) to (lines.takeIf { it[0].split(" ")[0] == "ls" }?.drop(1)?.map { it.split(" ") }?.filter { it[0] != "dir" }?.sumOf { it[0].toInt() }?.let { size -> pathStack.indices.map { dirIndex -> pathStack.subList(0, dirIndex + 1).joinToString("/") to size } }?.let{ it + dirs } ?: dirs) }}.second.groupBy(Pair<String, Int>::first).mapValues { it.value.sumOf(Pair<String, Int>::second) }.values.filter { it <= 100000 }.sum())
    println("7/2: " + File("./day7/input.txt").readText().trim().split("$").map(String::trim).fold(listOf<String>() to listOf<Pair<String, Int>>()){ (pathStack, dirs), currentInstruction -> currentInstruction.lines().let{ lines -> (lines[0].split(" ").takeIf { it[0]  == "cd" }?.let{ it[1].takeIf { dir -> dir != ".." }?.let { pathStack + it } ?: pathStack.dropLast(1) } ?: pathStack) to (lines.takeIf { it[0].split(" ")[0] == "ls" }?.drop(1)?.map { it.split(" ") }?.filter { it[0] != "dir" }?.sumOf { it[0].toInt() }?.let { size -> pathStack.indices.map { dirIndex -> pathStack.subList(0, dirIndex + 1).joinToString("/") to size } }?.let{ it + dirs } ?: dirs) }}.second.groupBy(Pair<String, Int>::first).mapValues { it.value.sumOf(Pair<String, Int>::second) }.let{ sizedDirs -> sizedDirs.values.filter { it >= (sizedDirs["/"]!! - 40000000) }.min()})

    // Day 8
    println("8/1: " + File("./day8/input.txt").readText().trim().lines().map { it.map { it.digitToInt() } }.let{ grid -> grid.mapIndexed { rowIndex, treeRow -> treeRow.mapIndexed { treeIndex, tree -> grid.map { it[treeIndex] }.let { column -> listOf(column.subList(0, rowIndex), column.reversed().subList(0, column.lastIndex - rowIndex), treeRow.subList(0, treeIndex), treeRow.reversed().subList(0, column.lastIndex - treeIndex)).map { it.maxOrNull()?.let { it < tree } ?: true }.fold(false) { acc, value -> acc || value } } } } }.sumOf { it.count { it } })
    println("8/1: " + File("./day8/input.txt").readText().trim().lines().map { it.map { it.digitToInt() } }.let{ grid -> grid.mapIndexed { rowIndex, treeRow -> treeRow.mapIndexed { treeIndex, tree -> grid.map { it[treeIndex] }.let { column -> listOf(column.subList(0, rowIndex), column.reversed().subList(0, column.lastIndex - rowIndex), treeRow.subList(0, treeIndex), treeRow.reversed().subList(0, column.lastIndex - treeIndex)).map { it.reversed().takeIf { it.isNotEmpty() }?.let { it.indexOfFirst { it >= tree }.takeIf { it != -1 }?.let { it + 1 } ?: it.size } ?: 0 }.fold(1) { product, value -> product * value } } } } }.maxOf { it.max() } )

    // Day 9
    println("9/1: " + File("./day9/input.txt").readText().trim().lines().map { it.split(" ").let { instruction -> List(instruction[1].toInt()) { instruction[0] } } }.flatten() .scan((0 to 0) to (0 to 0)) { (oldHead, tail), move -> mapOf("R" to (1 to 0), "L" to (-1 to 0), "U" to (0 to 1), "D" to (0 to -1))[move]!!.let { oldHead.first + it.first to oldHead.second + it.second }.let { head -> head to if (!((head.first - tail.first).absoluteValue <= 1 && (head.second - tail.second).absoluteValue <= 1)) ((head.first - tail.first).let { it.sign * (Integer.min(it.absoluteValue, 1)) } + tail.first to (head.second - tail.second).let { it.sign * Integer.min(it.absoluteValue, 1) } + tail.second) else tail } }.map { it.second }.toSet().size )
    println("9/2: " + File("./day9/input.txt").readText().trim().lines().asSequence().map { it.split(" ").let { instruction -> List(instruction[1].toInt()) { instruction[0] } } }.flatten().scan(List(10) { 0 to 0 }) { knots, move -> knots.drop(1).scan(mapOf("R" to (1 to 0), "L" to (-1 to 0), "U" to (0 to 1), "D" to (0 to -1))[move]!!.let { knots[0].first + it.first to knots[0].second + it.second }) { previousKnot, currentKnot -> if (!((previousKnot.first - currentKnot.first).absoluteValue <= 1 && (previousKnot.second - currentKnot.second).absoluteValue <= 1)) { ( (previousKnot.first - currentKnot.first).let { it.sign * Integer.min( it.absoluteValue, 1 ) } to (previousKnot.second - currentKnot.second).let { it.sign * Integer.min( it.absoluteValue, 1 ) } ).let { currentKnot.first + it.first to currentKnot.second + it.second } } else { currentKnot } } }.map { it.last() }.toSet().size)
}