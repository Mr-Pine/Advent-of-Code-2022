import common.AoCData
import java.lang.Integer.max

fun main() {

    val data = AoCData(
        filePath = "./day13/input.txt",
        """
            [1,1,3,1,1]
            [1,1,5,1,1]
            
            [[1],[2,3,4]]
            [[1],4]
            
            [9]
            [[8,7,6]]
            
            [[4,4],4,4]
            [[4,4],4,4,4]
            
            [7,7,7,7]
            [7,7,7]
            
            []
            [3]
            
            [[[]]]
            [[]]
            
            [1,[2,[3,[4,[5,6,7]]]],8,9]
            [1,[2,[3,[4,[5,6,0]]]],8,9]
        """,
        exampleId = null
    )

    fun compare(elve1: String, elve2: String): Int {
        val elve1Signal = elve1.drop(1).dropLast(1).splitLists()
        val elve2Signal = elve2.drop(1).dropLast(1).splitLists()

        for (i in 0 until max(elve1Signal.size, elve2Signal.size)) {
            if (elve1Signal.lastIndex < i && elve2Signal.lastIndex >= i) return 1
            if (elve2Signal.lastIndex < i && elve1Signal.lastIndex >= i) return -1

            val elve1Int = elve1Signal[i].toIntOrNull()
            val elve2Int = elve2Signal[i].toIntOrNull()

            if (elve1Int != null && elve2Int != null) {
                if (elve1Int < elve2Int) return 1
                if (elve1Int > elve2Int) return -1
            } else {
                compare(elve1Int?.let { "[$it]" } ?: elve1Signal[i],
                    elve2Int?.let { "[$it]" } ?: elve2Signal[i]).let { if (it != 0) return it }
            }

        }

        return 0
    }

    val result = data.currentString().split("\n\n").map {
        it.lines()
    }.map {
        compare(it[0].trim(), it[1].trim())
    }.mapIndexed { index, i -> if(i == 1) index + 1 else 0 }.sum()

    println(result)

}

fun String.splitLists(): List<String> {
    val res = mutableListOf<String>()
    var depth = 0
    var lastSeparator = -1
    forEachIndexed { index, char ->
        if (char == '[') depth++
        else if (char == ']') depth--
        else if (char == ',' && depth == 0) {
            res.add(substring(lastSeparator + 1, index))
            lastSeparator = index
        }
    }
    res.add(substring(lastSeparator + 1))
    return res.filter { it != "" }.toList()
}