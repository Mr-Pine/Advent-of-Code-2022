package day3
import java.io.File

fun main(args: Array<String>) {

    /*val inputString = """
        vJrwpWtwJgWrhcsFMMfFFhFp
        jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
        PmmdzqPrVvPwwTWBwg
        wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
        ttgJtRGJQctTZtZT
        CrZsJsPPZsGzwwsLwLmpwMDw
    """.trimIndent()*/

    val inputString = File("./src/main/resources/3-input.txt").readText().trim()

    val result = inputString.lines().map { line ->
        line.trim().let { Pair(it.substring(0 until it.length / 2), it.substring(it.length / 2)) }
    }.map { (first, second) ->
        first.find { second.contains(it) }!!.priority()
    }.sum()

    println(result)
}

fun Char.priority() = code - (if(isLowerCase()) 'a'.code else ('A'.code - 26)) + 1
