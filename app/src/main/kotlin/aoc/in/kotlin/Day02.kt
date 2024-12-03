package aoc.`in`.kotlin

fun dayTwoPartOne(input : List<String>) : Int {
    var totalSafe = 0
    input.forEach { report ->
        val levels = report.split(" ").map { it.toInt() }

        //Is Increasing or Decreasing?
        // Increasing
       if (levels[0] < levels[1]) {
           println("report is Increasing $report")
           println("checked ${levels[0]} and ${levels[1]}")
           if(levelsChecker(levels, levels, false){ x, y -> y in (x+1)..(x+3) }.also { answer ->
               println("it was $answer")
           }){ totalSafe++}
       }
       // Decreasing
       else if (levels[0] > levels[1]) {
           println("report is Decreasing $report")
           println("checked ${levels[0]} and ${levels[1]}")
           if(levelsChecker(levels, levels, false){ x, y -> y in (x-3)..<x }.also { answer ->
               println("it was $answer")
               }) { totalSafe++}
       }
    }
    return totalSafe
}


fun dayTwoPartTwo(input : List<String>): Int {
    var totalSafe = 0
    input.forEach { report ->
        val levels = report.split(" ").map { it.toInt() }

        // Is Ascending or Descending?
        // Ascending
        if (levels[0] < levels[1]) {
            //println("report is Ascending $report")
            //println("checked ${levels[0]} and ${levels[1]}")
            if(levelsChecker(levels, levels, true){ x, y -> y in (x+1)..(x+3) }
               // .also { answer -> println("it was $answer") }
                ){totalSafe++}
        }
        // Descending
        else if (levels[0] > levels[1]) {
            //println("report is Descending $report")
            //println("checked ${levels[0]} and ${levels[1]}")
            if(levelsChecker(levels, levels, true){ x, y -> y in (x-3)..<x }
                //.also { answer -> println("it was $answer") }
                ) {totalSafe++}
        }
    }
    return totalSafe
}

fun levelsChecker(report: List<Int>, level: List<Int>, pDampener: Boolean, operator: (Int, Int) -> Boolean ):Boolean {
//    println("entering levelsChecker with $level and PDamp as $pDampener")
    if (level.size == 1) return true

    //this covers the end case
    if (level.size == 2 && pDampener) return true

    return if (operator(level[0], level[1])) {
        levelsChecker(report, level.subList(1, level.size),pDampener, operator)
    }  else if (pDampener) {
        println("Used PDamp with $level")
        var flag = false
        // this covers the middle case
        if (operator(level[0], level[2])) {
            flag = levelsChecker(report, report - level[1],false, operator)
            // this covers the first error case
        } else if (level.size+1 == report.size) {
            if (level[1] < level[2])
                flag = levelsChecker(report, level.subList(0, level.size),false)
                { x, y -> y in (x+1)..(x+3) }
            if (level[1] > level[2])
                flag = levelsChecker(report, level.subList(0, level.size),false)
                { x, y -> y in (x-3)..<x }
        }
        return flag
    } else false
}

/*
fun levelsCheck(level: List<Int>,PDampener: Boolean, operator: (Int, Int) -> Boolean ):Boolean {
    if (level.size == 1) return true
    //fixes the problem in the end.
    return if (operator(level[0], level[1])) {
        levelsCheck(level.subList(1, level.size),true, operator).also { println("Calling myself with ${level.subList(1, level.size)}") }
    }  else false
}
 */

fun levelsChecker2(report: List<Int>) {
    val levelDif = mutableListOf<Int>()
    report.forEachIndexed { index, level ->
        if(index != report.lastIndex ) {
            levelDif.add(level - report[index +1] )
        }
    }

    //val whichOrder = levelDif.reduce { acc, dif -> if (dif > 0) acc + 1 else acc }
    var whichOrder = 0
    levelDif.forEach { dif ->
        if (dif > 0) whichOrder += 1
    }

    println("which order is it? $whichOrder")
    println("what's my level dif $levelDif")

    if(whichOrder > 1) {
        println("desing")
        //des
        val levelDif2 = mutableListOf<Int>()
        //val retest = report.drop(report[levelDif.indexOfFirst { level ->  0 < level} + 1])
        val retest = report.toMutableList()
        val test2 = levelDif.indexOfFirst { level -> 0 > level}
        retest.removeAt(test2 + 1)

        println("the old list" + report)
        println("the new list" + retest)

        retest.forEachIndexed { index, level ->
            levelDif2.add(level - report[index +1] )
        }
        println(levelDif2)

    } else if (whichOrder == 1) {
        //ass
        println("assing")
        val levelDif2 = mutableListOf<Int>()
        val retest = report.toMutableList()
        println("we are removing "+ levelDif.indexOfFirst { level -> 0 < level} + " the index")
        val test2 = levelDif.indexOfFirst { level -> 0 < level}
        //val test = report[-1]
        //println("our index we are removing" + test)
        retest.removeAt(test2 + 1)

        println(retest)

        retest.forEachIndexed { index, level ->
            levelDif2.add(level - report[index +1] )
        }
        println("the old list" + report)
        println("the new list" + retest)
        println(levelDif2)
    } else println("oh no")

}