fun main() = println("${java.io.File("./day1/input.txt").readText().trim().split("\n\n").map { it.split("\n").sumOf(String::toInt) }.let { elves -> "1/1: ${ elves.max() }\n1/2: ${elves.sorted().takeLast(3).sum()}" }}\n\n${java.io.File("./day2/input.txt").readText().trim().split("\n").map { it.split(" ").let { it[0][0].code - 'A'.code to it[1][0].code - 'X'.code } }.let { "2/1: ${it.sumOf { (opponent, self) -> (self - opponent + 1).mod(3) * 3 + (self + 1) }}\n2/2: ${it.sumOf { (opponent, strat) -> strat * 3 + ((opponent + strat + 2) % 3 + 1) }}" }}\n\n${java.io.File("./day3/input.txt").readText().trim().lines().let { lines -> "3/1: ${lines.map { it.chunked(it.length / 2) }.sumOf { halves -> halves[0].find { halves[1].contains(it) }!!.let { (('a'..'z') + ('A'..'Z')).indexOf(it) + 1 } }}\n3/2: ${lines.chunked(3).sumOf { elves -> elves[0].find { elves[1].contains(it) && elves[2].contains(it) }!!.let { (('a'..'z') + ('A'..'Z')).indexOf(it) + 1 } }}" }}\n\n${java.io.File("./day4/input.txt").readText().trim().lines().map { it.split(",").map { it.split("-") }.map { it[0].toInt()..it[1].toInt() } }.let { ranges -> "4/1: ${ranges.count { (it[0].contains(it[1].first) && it[0].contains(it[1].last)) || (it[1].contains(it[0].first) && it[1].contains(it[0].last)) }}\n4/2: ${ranges.count { (it[0].contains(it[1].first) || it[0].contains(it[1].last)) || it[1].contains(it[0].first) }}" }}\n\n${java.io.File("./day5/input.txt").readText().trim().split("\n\n").let { it[0].lines().let { it.subList(0, it.size -1) }.reversed().map { it.chunked(4).map { it[1] } }.let { it[0].indices.map { stackIndex -> it.map { it[stackIndex] }.filter { it != ' ' }.toMutableList() } } to it[1].lines().map { it.split(" ").let { Triple(it[1].toInt(), it[3].toInt() - 1, it[5].toInt() -1) } } }.let { (stacks, instructions) -> "5/1: ${stacks.map { it.map { it }.toMutableList() }.let { stacks1 -> stacks1.apply { instructions.forEach { (amount, from, to) -> stacks1[to].addAll((0 until amount).map { stacks1[from].removeLast() }) } }.joinToString("") { it.last().toString() }}}\n5/2: ${stacks.map { it }.apply { instructions.forEach { (amount, from, to) -> stacks[to].addAll((0 until amount).map { stacks[from].removeLast() }.reversed()) } }.joinToString("") { it.last().toString() }}" }}\n\n${java.io.File("./day6/input.txt").readText().trim().let { listOf(4, 14).associateWith { windowSize -> it.windowed(windowSize).indexOfFirst { it.toCharArray().distinct().size == it.length } + windowSize } }.let { "6/1: ${it[4]}\n6/2: ${it[14]}" }}\n\n${java.io.File("./day7/input.txt").readText().trim().split("$").map(String::trim).fold(listOf<String>() to listOf<Pair<String, Int>>()){ (pathStack, dirs), currentInstruction -> currentInstruction.lines().let{ lines -> (lines[0].split(" ").takeIf { it[0]  == "cd" }?.let{ it[1].takeIf { dir -> dir != ".." }?.let { pathStack + it } ?: pathStack.dropLast(1) } ?: pathStack) to (lines.takeIf { it[0].split(" ")[0] == "ls" }?.drop(1)?.map { it.split(" ") }?.filter { it[0] != "dir" }?.sumOf { it[0].toInt() }?.let { size -> pathStack.indices.map { dirIndex -> pathStack.subList(0, dirIndex + 1).joinToString("/") to size } }?.let{ it + dirs } ?: dirs) }}.second.groupBy(Pair<String, Int>::first).mapValues { it.value.sumOf(Pair<String, Int>::second) }.let{ dirMap -> "7/1: ${dirMap.values.filter { it <= 100000 }.sum()}\n7/2: ${dirMap.let{ sizedDirs -> sizedDirs.values.filter { it >= (sizedDirs["/"]!! - 40000000) }.min() } }" } }\n\n${java.io.File("./day8/input.txt").readText().trim().lines().map { it.map { it.digitToInt() } }.let{ grid -> grid.mapIndexed { rowIndex, treeRow -> treeRow.mapIndexed { treeIndex, tree -> grid.map { it[treeIndex] }.let { column -> listOf(column.subList(0, rowIndex), column.reversed().subList(0, column.lastIndex - rowIndex), treeRow.subList(0, treeIndex), treeRow.reversed().subList(0, column.lastIndex - treeIndex)).let { it.map { it.maxOrNull()?.let { it < tree } ?: true }.fold(false) { acc, value -> acc || value } to it.map { it.reversed().takeIf { it.isNotEmpty() }?.let { it.indexOfFirst { it >= tree }.takeIf { it != -1 }?.let { it + 1 } ?: it.size } ?: 0 }.fold(1) { product, value -> product * value } } } } } }.let { it.flatten().let { "8/1: ${it.count { it.first }}\n8/2: ${it.maxOf { it.second }}" } } }\n\n${java.io.File("./day9/input.txt").readText().trim().lines().asSequence().map { it.split(" ").let { instruction -> List(instruction[1].toInt()) { instruction[0] } } }.flatten().let{ instructions -> listOf(2, 10).map { instructions.scan(List(it) { 0 to 0 }) { knots, move -> knots.drop(1).scan(mapOf("R" to (1 to 0), "L" to (-1 to 0), "U" to (0 to 1), "D" to (0 to -1))[move]!!.let { knots[0].first + it.first to knots[0].second + it.second }) { previousKnot, currentKnot -> if (!((previousKnot.first - currentKnot.first).let{ kotlin.math.abs(it) } <= 1 && (previousKnot.second - currentKnot.second).let{ kotlin.math.abs(it) } <= 1)) { ( (previousKnot.first - currentKnot.first).let { kotlin.math.sign(it.toDouble()).toInt() * Integer.min( kotlin.math.abs(it), 1 ) } to (previousKnot.second - currentKnot.second).let { kotlin.math.sign(it.toDouble()).toInt() * Integer.min( kotlin.math.abs(it), 1 ) } ).let { currentKnot.first + it.first to currentKnot.second + it.second } } else { currentKnot } } }.map { it.last() }.toSet().size } }.let { "9/1: ${it[0]}\n9/2: ${it[1]}" } }")