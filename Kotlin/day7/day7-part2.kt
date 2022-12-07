import common.AoCData

fun main(args: Array<String>) {

    val data = AoCData(
        filePath = "./day7/input.txt",
        """
            ${'$'} cd /
            ${'$'} ls
            dir a
            14848514 b.txt
            8504156 c.dat
            dir d
            ${'$'} cd a
            ${'$'} ls
            dir e
            29116 f
            2557 g
            62596 h.lst
            ${'$'} cd e
            ${'$'} ls
            584 i
            ${'$'} cd ..
            ${'$'} cd ..
            ${'$'} cd d
            ${'$'} ls
            4060174 j
            8033020 d.log
            5626152 d.ext
            7214296 k
        """,
        exampleId = null
    )

    val directories = mutableMapOf<String, Int>()
    val pathStack = mutableListOf<String>()
    val counted = mutableListOf<String>()

    data.currentString().split("$").map(String::trim).filter(String::isNotBlank).forEach {
        val commandArgs = it.lines()[0].split(" ")
        if(commandArgs[0] == "cd") {
            if(commandArgs[1] == "..") pathStack.removeLast()
            else pathStack.add(commandArgs[1])
        } else {
            if(!counted.contains(pathStack.joinToString("/"))) {
                counted.add(pathStack.joinToString("/"))
                it.lines().drop(1).map { it.split(" ") }.filter { it[0] != "dir" }.map { it[0].toInt() }
                    .forEach { fileSize ->
                        pathStack.indices.forEach { dirIndex ->
                            val path = pathStack.subList(0, dirIndex + 1).joinToString("/")
                            directories[path] = (directories[path] ?: 0) + fileSize
                        }
                    }
            }
        }
    }

    println(directories.filter { it.value >= (directories["/"]!! - 40000000)}.minBy(Map.Entry<String, Int>::value))

}
