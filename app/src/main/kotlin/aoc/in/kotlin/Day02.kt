package aoc.`in`.kotlin

import java.lang.Exception

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
        if(levelsChecker2(levels)) totalSafe++
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

fun levelsChecker2(report: List<Int>): Boolean {

    // sets up a new list where we hold on to the diff
    // of the reports level. ie [A,B,C] => [A-B,B-C]
    val levelDif = mutableListOf<Int>()
    report.forEachIndexed { index, level ->
        if(index != report.lastIndex ) {
            levelDif.add(level - report[index +1] )
        }
    }

    // we count up the diff that's greater then 0
    var whichOrder = 0
    levelDif.forEach { dif ->
        if (dif > 0) whichOrder += 1
    }

    println("which order is it? $whichOrder")
    println("what's my level dif $levelDif")

    // if equal to one it's Descending, with one Ascending error
    if(whichOrder > 1) {
        // Descending
        println("Descending")
        val levelDif2 = mutableListOf<Int>()
        val retest = report.toMutableList()
        val errorIndex = levelDif.indexOfFirst { level -> level !in 1 .. 3 }
        if(errorIndex == -1) return true// didn't found an error
        println("error index on level dif $errorIndex")

        if (errorIndex == 0) {
            println("this might be a first index error")
            val levelDif3 = mutableListOf<Int>()
            val workingList = report.subList(1, report.lastIndex +1)
            println("working with $workingList")
            workingList.forEachIndexed { index, level ->
                if(index != workingList.lastIndex ) {
                    levelDif3.add(level - workingList[index +1] )
                }
            }
            println("leveldif3 $levelDif3")
            if (levelDif3.indexOfFirst { level -> level !in 1..3 } == -1)
                retest.removeAt(0)
            else retest.removeAt(1)
        } else retest.removeAt(errorIndex + 1)

        println("the old list $report")
        println("the new list $retest")

        retest.forEachIndexed { index, level ->
            if(index != retest.lastIndex ) {
                levelDif2.add(level - retest[index + 1])
            }
        }
        println(levelDif2)
        val isSafe = levelDif2.all { diff -> diff in 1..3 }
        println("is the report safe? $isSafe")
        return isSafe
    // if equal to one it's Ascending, with one Descending error
    } else {
        // Ascending
        println("Ascending")
        val levelDif2 = mutableListOf<Int>()
        val retest = report.toMutableList()
        val errorIndex = levelDif.indexOfFirst { level -> level !in -3 .. -1}
        if(errorIndex == -1) return true// didn't found an error

        if (errorIndex == 0) {
            println("this might be a first index error")
            val levelDif3 = mutableListOf<Int>()
            val workingList = report.subList(1, report.lastIndex +1)
            println("working with $workingList")
            workingList.forEachIndexed { index, level ->
                if(index != workingList.lastIndex ) {
                    levelDif3.add(level - workingList[index +1] )
                }
            }
            println("leveldif3 $levelDif3")
            if (levelDif3.indexOfFirst { level -> level !in -3..-1 } == -1)
                retest.removeAt(0)
            else retest.removeAt(1)
        } else retest.removeAt(errorIndex + 1)

        println(retest)

        retest.forEachIndexed { index, level ->
            if(index != retest.lastIndex ) {
                levelDif2.add(level - retest[index +1])
            }
        }

        println("the old list $report")
        println("the new list $retest")

        val isSafe = levelDif2.all { diff -> diff in -3..-1 }
        println(levelDif2)
        println("is the report safe? $isSafe")
        return  isSafe
    }
}