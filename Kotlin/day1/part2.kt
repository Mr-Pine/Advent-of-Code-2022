import java.io.File

fun main(args: Array<String>) {
    println("Hello World!")

    /*val inputString = """
        1000
        2000
        3000

        4000

        5000
        6000

        7000
        8000
        9000

        10000
    """.trimIndent()*/

    val inputString = File("./src/main/resources/1-1-input.txt").readText().trim()

    val lineSeparator = System.lineSeparator()

    val result = inputString.split(lineSeparator.repeat(2)).map {
        it.split(lineSeparator).sumOf { itemCalories ->
            itemCalories.toInt()
        }
    }.sorted().takeLast(3).sum()
    println(result)
}
