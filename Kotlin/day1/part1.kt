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

    val result = inputString.split(System.lineSeparator().repeat(2)).maxOfOrNull {
        it.split(System.lineSeparator()).sumOf { itemCalories ->
            itemCalories.toInt()
        }
    }
    println(result)
}
