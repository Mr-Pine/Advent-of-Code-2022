import common.AoCData

fun main(args: Array<String>) {

    val data = AoCData(
        filePath = "./day5/input.txt",
        """
                [D]    
            [N] [C]    
            [Z] [M] [P]
             1   2   3 
            
            move 1 from 2 to 1
            move 3 from 1 to 3
            move 2 from 2 to 1
            move 1 from 1 to 2
        """,
        exampleIndex = null
    )

    var stackLines = data.currentString().split("\n\n")[0].lines()
    val stackLinesWidth = stackLines.last().length + 1
    stackLines = stackLines.subList(0, stackLines.size - 1)
    val instructions = data.currentString().split("\n\n")[1].lines()

    val stacks = List((stackLinesWidth + 1) / 4) { mutableListOf<Char>() }.toMutableList()

    stackLines.reversed().forEach { line ->
        val crates = line.padEnd(stackLinesWidth).chunked(4) { it[1] }
        crates.forEachIndexed() { index, content ->
            if (content != ' ') {
                stacks[index].add(content)
            }
        }
    }

    instructions.forEach {
        val split = it.split(" ")
        val count = split[1].toInt()
        val from = split[3].toInt() - 1
        val to = split[5].toInt() - 1

        stacks[to].addAll(stacks[from].let { it.subList(it.size - count, it.size) })
        stacks[from] = stacks[from].subList(0, stacks[from].size -count)
    }

    var result = ""
    stacks.forEach { result += it.last() }

    println(result)

}
