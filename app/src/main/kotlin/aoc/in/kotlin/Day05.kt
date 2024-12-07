package aoc.`in`.kotlin

fun dayFivePartOne(pagesPrintOrderRule: MutableList<List<Int>>, pagesOrderList: MutableList<List<Int>>): Int {
    var count = 0
    pagesOrderList.forEach { pageOrder ->
        if (valMaster(pageOrder, pagesPrintOrderRule)) {
            count += pageOrder[(pageOrder.size/2)]
        }
    }
    return count
}
fun dayFivePartTwo(pagesPrintOrderRule: MutableList<List<Int>>, pagesOrderList: MutableList<List<Int>>): Int {
    var count = 0
    pagesOrderList.forEach { pageOrder ->
        if (!valMaster(pageOrder, pagesPrintOrderRule)) {
            count += valMaster2000(pageOrder, pagesPrintOrderRule)
        }
    }
    return count
}
fun valMaster(row : List<Int>, ruleSet: MutableList<List<Int>>):Boolean {
    if (row.size == 1) return true
    val filterRuleSet = ruleSet.filter { x -> x[0] == row.first() }
    val checkList = row.subList(1, row.size)
    var passCount = 0
    checkList.forEach {
        filterRuleSet.any { rule -> rule[1] == it }.also { if (it) passCount++ }
    }
    if(passCount == checkList.size) return valMaster(checkList, ruleSet)
   return false
}


fun valMaster2000(row : List<Int>,ruleSet: MutableList<List<Int>> ): Int {
    val test = row.toMutableList()
    for(entry in row) {
        val filterRuleSet = ruleSet.filter { x -> x[0] == entry }
        val checkListRight = row.subList(row.indexOf(entry), row.size)
        val checkListLeft = row.subList(0,row.indexOf(entry))
        var leftCount = 0
        var rightCount = 0

        println("==============")
        println("Row we are looking at \n $row")
        println("Filter rule set \n $filterRuleSet")
        println("Current entry we are looking at \n $entry")
        println("The list we are checking to the Right \n $checkListRight")
        println("The list we are checking to the Left \n $checkListLeft")
        //if (filterRuleSet.size == 0) valMaster2000(checkList + entry, ruleSet)
        checkListLeft.forEach {
            filterRuleSet.any { rule -> rule[1] == it }.also { if (it) leftCount++ }

        }
        checkListRight.forEach {
            filterRuleSet.any { rule -> rule[1] == it }.also { if (it) rightCount++ }
        }
        println("Right Pass Count \n $rightCount")
        println("Left Pass Count \n $leftCount")
        test[rightCount+leftCount] = entry
    }
    return test[(test.size/2)]
}