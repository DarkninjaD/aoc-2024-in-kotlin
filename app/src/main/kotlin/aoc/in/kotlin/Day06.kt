package aoc.`in`.kotlin



fun day06PartOne(map: List<String>): Int {
    lateinit var theGuard : Guard
    var counter = 0
    // maybe not a foreach as we just need to find '^'
    map.forEachIndexed { rowIndex, row ->
        row.forEachIndexed { columnIndex, column ->
            if (column == '^') {
                theGuard = Guard(rowIndex, columnIndex, Facing.North)
            }
        }
    }
    println(map)
    while (!theGuard.isEdge(map)) {
        // println("===")
        // theGuard.debugDisplay(map).forEach{
        //    println(it)
        // }

        if (theGuard.isClear(map)) {
            println("====")
            theGuard.moveForward()
            if(theGuard.isTrappable(map)) {
                println("trapped")
                theGuard.debugDisplay(map).forEach {
                    println(it)
                }
               counter++ }
        } else {
            // println("turning")
            theGuard.turn()
        }
    }

    println("the guard as been to " + theGuard.whereHaveIBeen.size + " locations")
    println("the guard can be trapped " + counter + " times")
    return counter
    //return theGuard.whereHaveIBeen.size // For part one
}

class Guard(private var row: Int, private var column: Int, private var facing: Facing) {
    val whereHaveIBeen = mutableSetOf(Pair(row, column))
    val lastThreeTurn = mutableListOf<Pair<Int,Int>>()

    fun moveForward() {
       when(facing) {
           Facing.North -> row -= 1
           Facing.South -> row += 1
           Facing.East -> column += 1
           Facing.West -> column -= 1
       }
       whereHaveIBeen.add(Pair(row, column))
    }
    fun isClear(map: List<String>,facing: Facing = this.facing, row: Int = this.row, column: Int = this.column): Boolean {
        return when(facing) {
            Facing.North -> map[row -1][column] != '#'
            Facing.South -> map[row+1][column] != '#'
            Facing.East -> map[row][column+1] != '#'
            Facing.West -> map[row][column-1] != '#'
        }
    }

    fun isEdge(map: List<String>, facing: Facing = this.facing,  row : Int = this.row, column: Int = this.column): Boolean {
        return when(facing) {
            Facing.North -> row -1 < 0
            Facing.South -> row +1 > map.lastIndex
            Facing.East -> map[row].lastIndex < column+1
            Facing.West -> column-1 < 0
        }
    }

   fun turn() {
       when(facing) {
           Facing.North -> facing = Facing.East
           Facing.South -> facing = Facing.West
           Facing.East -> facing = Facing.South
           Facing.West -> facing = Facing.North
       }
       lastThreeTurn.add(Pair(row,column))
   }

    fun isTrappable(map: List<String>) : Boolean {
        if (lastThreeTurn.size > 3) {
            val firstIndex = lastThreeTurn.lastIndex
            val cornerOne = lastThreeTurn[firstIndex]
            val cornerTwo = lastThreeTurn[firstIndex -1]
            val cornerThree = lastThreeTurn[firstIndex -2]
            // take the number above and find the Fourth Corner.
            // track to see if we cross over that point
        }

        return false
    }

    /*
    fun isTrapable(map: List<String>) : Boolean {
        return if (whereHaveIBeen.contains(Pair(row, column))) {
            when(facing) {
                Facing.North -> whereHaveIBeen.contains(Pair(row, column + 1)) //&& trapCheck(map, Facing.East, row, column + 1)
                Facing.South -> whereHaveIBeen.contains(Pair(row, column -1))  //&& trapCheck(map, Facing.West, row, column - 1)
                Facing.East  -> whereHaveIBeen.contains(Pair(row +1, column))  //&& trapCheck(map, Facing.South, row + 1, column)
                Facing.West  -> whereHaveIBeen.contains(Pair(row-1, column))   //&& trapCheck(map, Facing.North, row - 1, column)
            }
        } else false
    }
    fun trapCheck(map: List<String>, facing: Facing, row: Int, column: Int) : Boolean {
        return when(facing) {
            Facing.North -> {
                if (!isEdge(map, facing, row, column)) {
                    if (isClear(map,facing, row, column)) {
                         trapCheck(map, facing, row -1, column)
                    } else true
                } else false
            }
            Facing.South -> {
                if (!isEdge(map,facing,  row, column)) {
                    if (isClear(map,facing, row, column)) {
                         trapCheck(map, facing, row+1, column)
                    } else true
                } else false
            }
            Facing.East  -> {
                if (!isEdge(map,facing, row, column)) {
                    if (isClear(map,facing, row, column)) {
                         trapCheck(map, facing, row, column+1)
                    } else true
                } else false
            }
            Facing.West  -> {
                if (!isEdge(map,facing, row, column)) {
                    if (isClear(map,facing, row, column)) {
                         trapCheck(map, facing, row, column-1)
                    } else true
                } else false
            }
        }
    }
     */
    fun debugDisplay(map: List<String>): List<String> {
        val test = map.toMutableList()
        test.forEachIndexed { row, s ->
            var rowString = s
            s.forEachIndexed { column, c ->
                if (whereHaveIBeen.contains(Pair(row, column))) {
                    rowString = rowString.substring(0, column) + "X" + rowString.substring(column+1)
            }
                test[row] = rowString
           }
        }
        return test
    }
}

enum class Facing {
    North,
    South,
    East,
    West
}
