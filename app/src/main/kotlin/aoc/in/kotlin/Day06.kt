package aoc.`in`.kotlin


fun day06PartOne(map: List<String>): Int {
    var counter = 1
    // maybe not a foreach as we just need to find '^'
    map.forEachIndexed { rowIndex, row ->
        row.forEachIndexed { columnIndex, column ->
            if (column == '^') {
                Guard(rowIndex, columnIndex, Facing.North)
            }
        }
    }


    // Find the Guard
    // Move to facing
    // Turn Right if hit something

    return counter
}

class Guard(private var row: Int, private var column: Int, private var facing: Facing) {

    fun moveForward() {
       when(facing) {
           Facing.North -> row - 1
           Facing.South -> row + 1
           Facing.East -> column + 1
           Facing.West -> column - 1
       }
    }
    fun isClear(map: List<String>): Boolean {
        return when(facing) {
            Facing.North -> map[row -1][column] == '.'
            Facing.South -> map[row+1][column] == '.'
            Facing.East -> map[row][column+1] == '.'
            Facing.West -> map[row][column-1] == '.'
        }
    }

   fun isEdge(map: List<String>): Boolean {
        return when(facing) {
            Facing.North -> TODO()
            Facing.South -> TODO()
            Facing.East -> TODO()
            Facing.West -> TODO("check of null or out of bound for array")
        }
    }
}

enum class Facing {
    North,
    South,
    East,
    West
}
