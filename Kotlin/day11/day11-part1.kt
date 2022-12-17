import common.AoCData
import kotlin.reflect.KFunction

fun main() {

    val data = AoCData(
        filePath = "./day11/input.txt",
        """
         Monkey 0:
          Starting items: 79, 98
          Operation: new = old * 19
          Test: divisible by 23
            If true: throw to monkey 2
            If false: throw to monkey 3
        
        Monkey 1:
          Starting items: 54, 65, 75, 74
          Operation: new = old + 6
          Test: divisible by 19
            If true: throw to monkey 2
            If false: throw to monkey 0
        
        Monkey 2:
          Starting items: 79, 60, 97
          Operation: new = old * old
          Test: divisible by 13
            If true: throw to monkey 1
            If false: throw to monkey 3
        
        Monkey 3:
          Starting items: 74
          Operation: new = old + 3
          Test: divisible by 17
            If true: throw to monkey 0
            If false: throw to monkey 1
        """,
        exampleId = null
    )

    val result = data.currentString().split("\n\n").map { it.lines().drop(1) }.map { Monkey(it) }.let { monkeys ->
        monkeys.apply {
            forEach {
                it.throwTrue = monkeys[it.throwTrueIndex]
                it.throwFalse = monkeys[it.throwFalseIndex]
            }
        }
    }.let { monkeys ->
        monkeys.apply { repeat(1) { forEach { it.takeTurn() } } }
    }.map { "${it.items}, ${it.inspectionCount}" }//.sorted().takeLast(2).let { it[0] * it[1] }

    println(result)

}

class Monkey(
    val items: MutableList<Int>,
    val operation: (Int, Int) -> Int,
    val operand: Int?,
    val divisor: Int,
    val throwTrueIndex: Int,
    val throwFalseIndex: Int
) {
    var throwTrue: Monkey? = null
    var throwFalse: Monkey? = null

    var inspectionCount = 0

    constructor(serializedLines: List<String>) : this(
        items = serializedLines[0].trim().drop(16).split(", ").map { it.toInt() }.toMutableList(),
        operation = if (serializedLines[1].contains("*")) Int::times else Int::plus,
        operand = serializedLines[1].trim().split(" ").last().toIntOrNull(),
        divisor = serializedLines[2].trim().split(" ").last().toInt(),
        throwTrueIndex = serializedLines[3].trim().split(" ").last().toInt(),
        throwFalseIndex = serializedLines[4].trim().split(" ").last().toInt()
    )

    fun takeTurn() {
        inspectionCount += items.size
        items.forEach { worryLevel ->
            val newWorryLevel = operation(worryLevel, operand ?: worryLevel) / 3
            (if (newWorryLevel % divisor == 0) throwTrue else throwFalse)!!.items.add(newWorryLevel)
        }
        items.clear()
    }
}
