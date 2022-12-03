import day3.priority
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

    val lines = inputString.lines()

    val result = (0 until lines.size / 3).map { lines.subList(it * 3, it * 3 + 3) }.map {block ->
        block[0].find {
            block[1].contains(it) && block[2].contains(it)
        }!!.priority()
    }.sum()

    println(result)
}

//fun Char.priority() = code - (if(isLowerCase()) 'a'.code else ('A'.code - 26)) + 1
