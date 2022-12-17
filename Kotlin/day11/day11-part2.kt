import common.AoCData

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
        exampleIndex = null
    )

    val result = data.currentString().split("\n\n").map { it.lines().drop(1) }.map { WorryMonkey(it) }.let { monkeys ->
        val commonDivisor = monkeys.map { it.divisor }.fold(1L) {acc, i -> acc * i }
        monkeys.apply {
            forEach {
                it.throwTrue = monkeys[it.throwTrueIndex]
                it.throwFalse = monkeys[it.throwFalseIndex]
                it.commonDivisor = commonDivisor
            }
        }
    }.let { monkeys ->
        monkeys.apply { repeat(10000) {
            forEach { it.takeTurn() } } }
    }.map { it.inspectionCount.toBigInteger() }.sorted().takeLast(2).let { it[0] * it[1] }

    println(result)

}

class WorryMonkey(
    val items: MutableList<Long>,
    val operation: (Long, Long) -> Long,
    val operand: Long?,
    val divisor: Long,
    val throwTrueIndex: Int,
    val throwFalseIndex: Int
) {
    var throwTrue: WorryMonkey? = null
    var throwFalse: WorryMonkey? = null

    var inspectionCount = 0
    var commonDivisor = 0L

    constructor(serializedLines: List<String>) : this(
        items = serializedLines[0].trim().drop(16).split(", ").map { it.toLong() }.toMutableList(),
        operation = if (serializedLines[1].contains("*")) Long::times else Long::plus,
        operand = serializedLines[1].trim().split(" ").last().toLongOrNull(),
        divisor = serializedLines[2].trim().split(" ").last().toLong(),
        throwTrueIndex = serializedLines[3].trim().split(" ").last().toInt(),
        throwFalseIndex = serializedLines[4].trim().split(" ").last().toInt()
    )

    fun takeTurn() {
        inspectionCount += items.size
        items.forEach { worryLevel ->
            val newWorryLevel = operation(worryLevel, operand ?: worryLevel) % commonDivisor
            (if (newWorryLevel % divisor == 0L) throwTrue else throwFalse)!!.items.add(newWorryLevel)
        }
        items.clear()
    }
}
