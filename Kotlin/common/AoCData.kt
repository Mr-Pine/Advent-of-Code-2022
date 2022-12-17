package common

import java.io.File

class AoCData(filePath: String, vararg exampleStrings: String, private var exampleIndex: Int? = null) {
    val aocInput = File(filePath).readText().trim()
    val exampleStrings = exampleStrings

    fun currentString() = exampleIndex?.let { exampleStrings[it].trimIndent() } ?: aocInput

    fun lines() = currentString().lines()
}

fun List<Int>.product() {
    fold(1) { acc, i -> acc * i }
}
