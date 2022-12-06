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
            if (index < 13)
                false
            else
                it.substring(index - 13, index + 1).toCharArray().distinct().size == 14
        }
    }.indexOfFirst { it } + 1

    println(result)

}
