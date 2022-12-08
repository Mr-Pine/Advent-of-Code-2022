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
                val top = column.subList(0, rowIndex).reversed().takeIf { it.isNotEmpty() }?.let{ it.indexOfFirst { it >= tree }.takeIf { it != -1 }?.let { it + 1 } ?: it.size } ?: 0
                val bottom = column.reversed().subList(0, column.lastIndex - rowIndex).reversed().takeIf { it.isNotEmpty() }?.let{ it.indexOfFirst { it >= tree }.takeIf { it != -1 }?.let { it + 1 } ?: it.size } ?: 0
                val left = treeRow.subList(0, treeIndex).reversed().takeIf { it.isNotEmpty() }?.let{ it.indexOfFirst { it >= tree }.takeIf { it != -1 }?.let { it + 1 } ?: it.size } ?: 0
                val right = treeRow.reversed().subList(0, column.lastIndex - treeIndex).reversed().takeIf { it.isNotEmpty() }?.let{ it.indexOfFirst { it >= tree }.takeIf { it != -1 }?.let { it + 1 } ?: it.size } ?: 0
                top * bottom * left * right
            }
        }
    } }.maxOf { it.max() }

    println(result)

}