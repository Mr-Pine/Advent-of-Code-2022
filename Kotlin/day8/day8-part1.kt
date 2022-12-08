import common.AoCData

fun main() {

    val data = AoCData(
        filePath = "./day8/input.txt",
        """
            30373
            25512
            65332
            33549
            35390
        """,
        exampleId = null
    )

    val result = data.lines().map { it.map { it.digitToInt() } }.let{ grid -> grid.mapIndexed { rowIndex, treeRow ->
        treeRow.mapIndexed { treeIndex, tree ->
            grid.map { it[treeIndex] }.let { column ->
                val top = column.subList(0, rowIndex)
                val bottom = column.reversed().subList(0, column.lastIndex - rowIndex)
                val left = treeRow.subList(0, treeIndex)
                val right = treeRow.reversed().subList(0, column.lastIndex - treeIndex)
                top.maxOrNull()?.let { it < tree } ?: true || bottom.maxOrNull()?.let { it < tree } ?: true || left.maxOrNull()?.let { it < tree } ?: true || right.maxOrNull()?.let { it < tree } ?: true
            }
        }
    } }.sumOf { it.count { it } }

    println(result)

}