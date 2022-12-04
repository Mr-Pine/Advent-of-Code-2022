package common

import java.io.File

class AoCData(filePath: String, vararg exampleStrings: String, private var exampleId: Int? = null) {
    val aocInput = File(filePath).readText().trim()
    val exampleStrings = exampleStrings

    fun currentString() = exampleId?.let { exampleStrings[it].trimIndent() } ?: aocInput

    fun lines() = currentString().lines()
}
