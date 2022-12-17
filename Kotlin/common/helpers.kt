package common

import kotlin.math.absoluteValue


operator fun Pair<Int, Int>.plus(other: Pair<Int, Int>) = first + other.first to second + other.second
operator fun Pair<Int, Int>.minus(other: Pair<Int, Int>) = first - other.first to second - other.second
val Pair<Int, Int>.abs: Pair<Int, Int>
    get() = first.absoluteValue to second.absoluteValue

fun Pair<Int, Int>.manhattanDistance(other: Pair<Int, Int>): Int = (this - other).abs.let { it.first + it.second }

typealias Point = Pair<Int, Int>
