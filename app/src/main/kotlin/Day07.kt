

fun Day07PartOne(input: List<String>): Int {
    val puzzleInput = Day07Setup(input)
    val opsList = listOf(::plusOp, ::plusMult)
    /*
    puzzleInput.forEach { (target, list) ->
        confirmTotal(opsList, target, list, 0, 0, mutableListOf())
    }
     */
    opsList.forEach { action ->
        println(action.name)
    }
    println("target total is ${puzzleInput[0].first}")
    println("The list is ${puzzleInput[0].second}")
    confirmTotal(opsList, puzzleInput[0].first, puzzleInput[0].second, 0, 0 , mutableListOf())
    return 0
}

fun plusOp(x: Int , y:Int): Int {return x + y}
fun plusMult(x: Int , y:Int): Int {return x * y}

fun confirmTotal(opsList: List<(Int, Int) -> Int>, target: Int, list: List<Int>, pointer: Int, workingTotal : Int, opsNames: MutableList<String>) {
    println("working total is $workingTotal")
    if (pointer == list.lastIndex) {
       if (workingTotal == target) {
           println("we go the total with using $opsNames")
       }
    } else {
        opsList.forEach { function ->
            opsNames.add(function.javaClass.simpleName)
            println("branching... on ${function.javaClass.simpleName}")
            val test = function(workingTotal, list[pointer])
            confirmTotal(opsList, target, list, pointer+1, test, opsNames)
        }
    }
}

fun Day07Setup(input: List<String>): MutableList<Pair<Int, List<Int>>> {
    val inputClean  = mutableListOf<Pair<Int, List<Int>>>()
    input.forEach { entry ->
        val leftRight = entry.split(": ")
        val total = leftRight[0].toInt()
        val digits = leftRight[1].split(" ").map { it.toInt() }
        inputClean.add(Pair(total, digits))
    }
    return inputClean
}