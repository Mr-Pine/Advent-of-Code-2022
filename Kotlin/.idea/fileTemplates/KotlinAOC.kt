#set($separatorIndex = $NAME.indexOf('-'))
#set ($day = $NAME.substring(1, $separatorIndex))
import common.AoCData

fun main() {

    val data = AoCData(
        filePath = "./${day}/input.txt",
        """
            
        """
    )

    val result = data.lines()

    println(result)
    
}
