package aoc.`in`.kotlin

fun day03PartOne(input: List<String>): Int {
    var total = 0
    val preTest = doList(input)
    val test = day03InputSetup(preTest)
    test.forEach { it ->
        val found = mulFinder(it)
        if (found != null) {
            total += mul(found.first,found.second)
        }
    }
    return total
}
fun day03PartTwo(input: List<String>): Int {
    var total = 0
    val preTest = doList(input)
    val test = day03InputSetup(preTest)
    println(preTest.size)
    test.forEach { it ->
        val found = mulFinder(it)
        if (found != null) {
            total += mul(found.first,found.second)
        }
    }
    return total
}
fun mul(x : Int,y : Int):Int {return x * y}
fun mulFinder(input:String): Pair<Int,Int>? {
    val target = input.indexOf(")")
    if (target != -1) {
        val command = if (target != input.lastIndex) input.substring(0, target+1)
            else input
        val regex = Regex("\\(([^)]*)\\)")
        val test = regex.find(command)
        if (test != null) {
            if(!test.value.contains(",")) return null
            val (x , y) = test.groupValues[1].split(",")
            if(null == x.toIntOrNull()) return  null
            if(null == y.toIntOrNull()) return  null
            return Pair(x.toInt(),y.toInt())
        }
    }
    return null
}

fun doList(input: List<String>): List<String> {
    val regDosDonts = Regex("""do\(\)|don't\(\)""")
    val doList = mutableListOf<String>()
    var isEnabled = true

    input.forEach {
        var switchingIndex = 0
        regDosDonts.findAll(it).forEach { match ->
            val leftHandRange = it.substring(switchingIndex, match.range.first)
            if (isEnabled && leftHandRange.isNotBlank()) doList.add(leftHandRange)
            if (match.value.length == 4) isEnabled = true
            if (match.value.length == 7) isEnabled = false
            if (match.range.last < it.lastIndex) switchingIndex = match.range.last +1
        }
        val rightHandRange = it.substring(switchingIndex)
        if (isEnabled && rightHandRange.isNotBlank()) doList.add(rightHandRange)
    }
    return doList
}
fun day03InputSetup(input: List<String>): List<String> {
    val regex = Regex("""mul\(""")
    val cleanInput = mutableListOf<String>()
    input.forEach {
        regex.findAll(it).forEach { match ->
            // mul(XXX,XXX)B
            // 0123456789012
            val maxEndIndex = match.range.first + 12
            if (maxEndIndex < it.lastIndex)  {
                cleanInput.add(it.substring(match.range.first, maxEndIndex))
                println(it.substring(match.range.first, maxEndIndex))
                // mul(3,4)mul(3,4)
                // 0123456789012345
            }else {
                cleanInput.add(it.substring(match.range.first))
                println(it.substring(match.range.first))
            }
        }
        println("====")
    }
    return cleanInput
}