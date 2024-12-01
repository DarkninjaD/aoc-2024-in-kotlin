package aoc.`in`.kotlin

import kotlin.math.absoluteValue


fun dayOnePartOne(input : List<String>): Int {

  val leftList = mutableListOf<Int>()
  val rightList = mutableListOf<Int>()

  input.forEach { pair ->
   val holder = pair.split("   ")
    leftList.add(holder[0].toInt())
    rightList.add(holder[1].toInt())
  }

    leftList.sort()
    rightList.sort()
    var totalDistance = 0

  for (i in input.indices) {
      println("left side " + leftList[i].toString())
      println("right side " + rightList[i].toString())
      val distance = (leftList[i] - rightList[i]).absoluteValue
      println("distance of the two $distance")
      totalDistance += distance
      println("current total $totalDistance")

  }
    println("our total is $totalDistance")

  return totalDistance
}

fun dayOnePartTwo(input: List<String>): Int  {

    val leftList = mutableListOf<Int>()
    val rightList = mutableListOf<Int>()

    input.forEach { pair ->
        val holder = pair.split("   ")
        leftList.add(holder[0].toInt())
        rightList.add(holder[1].toInt())
    }

    val rightSortedMap = mutableMapOf<Int, Int>()

    rightList.forEach {
        if (rightSortedMap.containsKey(it)) {
            val value = rightSortedMap[it]?: 0
            rightSortedMap[it] = value + 1
        } else {
            rightSortedMap[it] = 1
        }
    }

    var similarityScore = 0
    leftList.forEach { leftSideInt ->
        val amount = rightSortedMap[leftSideInt]?: 0
        similarityScore += leftSideInt * amount
    }

    return similarityScore
}