import common.AoCData

fun main(args: Array<String>) {

    val data = AoCData(
        filePath = "./day6/input.txt",
        """
            mjqjpqmgbljsphdztnvjfqwrcgsmlb
        """,
        "bvwbjplbgvbhsrlpgdmjqwftvncz",
        "nppdvjthqldpwncqszvftbrmjlhg",
        "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg",
        exampleId = null
    )

    val result = data.currentString().let {
        it.mapIndexed { index, c ->
            if (index < 4)
                false
            else
                arrayOf(c, it[index - 1], it[index - 2], it[index - 3]).distinct().size == 4
        }
    }.indexOfFirst { it } + 1

    println(result)

}
