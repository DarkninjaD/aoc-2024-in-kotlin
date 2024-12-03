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
