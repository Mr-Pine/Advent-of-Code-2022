import common.AoCData

fun main() {

    val data = AoCData(
        filePath = "./day6/input.txt",
        "mjqjpqmgbljsphdztnvjfqwrcgsmlb",
        "bvwbjplbgvbhsrlpgdmjqwftvncz",
        "nppdvjthqldpwncqszvftbrmjlhg",
        "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg",
        exampleIndex = null
    )

    val result = data.currentString().windowed(14).indexOfFirst { it.toCharArray().distinct().size == it.length } + 14

    println(result)

}
