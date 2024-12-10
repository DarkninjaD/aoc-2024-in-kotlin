package aoc.`in`.kotlin

fun day04PartOne(puzzleMap : List<String>): Int {
    // part One
    val find = "XMAS"
    val target = 'X'

   // part Two
    val find2 = "MAS"
    val target2 = 'A'

    val allStartingLocations = mutableListOf<Pair<Int,Int>>()
    val newMap = mutableMapOf<Pair<Int,Int>, ElementPuzzle>()

    // Parsing Phase
    puzzleMap.forEachIndexed { rowIndex, row ->
        row.forEachIndexed { columnIndex, char ->
            val current = ElementPuzzle(char, rowIndex, columnIndex)
            newMap[current.cords] = current
            if (char == target2) allStartingLocations.add(Pair(rowIndex, columnIndex))
        }
    }

    // Linking Phase
    newMap.forEach { entry ->
        val north = newMap[Pair(entry.key.first - 1, entry.key.second)]
        val south = newMap[Pair(entry.key.first + 1, entry.key.second)]
        val east  = newMap[Pair(entry.key.first, entry.key.second + 1)]
        val west  = newMap[Pair(entry.key.first, entry.key.second - 1)]
        val northEast  = newMap[Pair(entry.key.first - 1, entry.key.second + 1)]
        val southEast  = newMap[Pair(entry.key.first + 1, entry.key.second + 1)]
        val southWest  = newMap[Pair(entry.key.first + 1, entry.key.second - 1)]
        val northWest  = newMap[Pair(entry.key.first - 1, entry.key.second - 1)]

        if (north != null) entry.value.setDirection(north, Directions.North)
        if (south != null) entry.value.setDirection(south, Directions.South)
        if (east != null) entry.value.setDirection(east, Directions.East)
        if (west != null) entry.value.setDirection(west, Directions.West)
        if (northEast != null) entry.value.setDirection(northEast, Directions.NorthEast)
        if (southEast != null) entry.value.setDirection(southEast, Directions.SouthEast)
        if (southWest != null) entry.value.setDirection(southWest, Directions.SouthWest)
        if (northWest!= null) entry.value.setDirection(northWest, Directions.NorthWest)
    }

    return partTwo(find2, allStartingLocations, newMap)
    //return partOne(find, allStartingLocations, newMap)
}

fun partOne(
    find: String,
    allStartingLocations: MutableList<Pair<Int,Int>> ,
    newMap: Map<Pair<Int,Int>, ElementPuzzle>): Int {
    var counter = 0
    allStartingLocations.forEachIndexed {index , it ->
        val current = newMap[it]
        if (current != null) {
            println("$index looking on")
            totalRecall(find, 1, Directions.North, current).also {
                if (it) println("North Found")
                if (it) counter++
            }
            totalRecall(find, 1, Directions.East, current).also {
                if (it) println("East Found")
                if (it) counter++
            }
            totalRecall(find, 1, Directions.West, current).also {
                if (it) println("West Found")
                if (it) counter++
            }
            totalRecall(find, 1, Directions.South, current).also {
                if (it) println("South Found")
                if (it) counter++
            }
            totalRecall(find, 1, Directions.NorthEast, current).also {
                if (it) println("North East Found")
                if (it) counter++
            }
            totalRecall(find, 1, Directions.SouthEast, current).also {
                if (it) println("South East Found")
                if (it) counter++
            }
            totalRecall(find, 1, Directions.SouthWest, current).also {
                if (it) println("South West Found")
                if (it) counter++
            }
            totalRecall(find, 1, Directions.NorthWest, current).also {
                if (it) println("North West Found")
                if (it) counter++
            }
        }
    }
    return counter
}
fun partTwo(
    find: String,
    allStartingLocations: MutableList<Pair<Int,Int>> ,
    newMap: Map<Pair<Int,Int>, ElementPuzzle>): Int {
    var counter = 0
    println("there should be nine ${allStartingLocations.size}")
    allStartingLocations.forEach {
        val current = newMap[it]
        if (current != null) {
        // South West
            // SW and NE contains M or S
            // and
            // SE and NW contains M or S
            if (
                current.whatsToMy(Directions.SouthWest)?.letter  == 'M' &&
                current.whatsToMy(Directions.NorthEast)?.letter  == 'S'
                ) {
                if (
                    current.whatsToMy(Directions.SouthEast)?.letter  == 'M' &&
                    current.whatsToMy(Directions.NorthWest)?.letter == 'S'
                ) {
                    counter ++
                }
            }
            if (
                current.whatsToMy(Directions.SouthWest)?.letter  == 'M' &&
                current.whatsToMy(Directions.NorthEast)?.letter  == 'S'
            ) {
                if (
                    current.whatsToMy(Directions.SouthEast)?.letter  == 'S' &&
                    current.whatsToMy(Directions.NorthWest)?.letter == 'M'
                ) {
                    counter ++
                }
            }
            if (
                current.whatsToMy(Directions.SouthWest)?.letter  == 'S' &&
                current.whatsToMy(Directions.NorthEast)?.letter  == 'M'
            ) {
                if (
                    current.whatsToMy(Directions.SouthEast)?.letter  == 'S' &&
                    current.whatsToMy(Directions.NorthWest)?.letter == 'M'
                ) {
                    counter ++
                }
            }
            if (
                current.whatsToMy(Directions.SouthWest)?.letter  == 'S' &&
                current.whatsToMy(Directions.NorthEast)?.letter  == 'M'
            ) {
                if (
                    current.whatsToMy(Directions.SouthEast)?.letter  == 'M' &&
                    current.whatsToMy(Directions.NorthWest)?.letter == 'S'
                ) {
                    counter ++
                }
            }
        }

    }

    return counter
}

fun totalRecall(find: String, on:Int, direction: Directions, ref: ElementPuzzle) : Boolean {
    val lookingAt = ref.whatsToMy(direction) ?: return false
    if (lookingAt.letter == find[on]) {
        return if (on == find.lastIndex) true else {
            totalRecall(find, on+1,direction,lookingAt)
        }
    }
    return false
}

class ElementPuzzle(char : Char, row: Int, column : Int) {
    var cords = Pair(row,column)
    val letter = char
    private var north : ElementPuzzle? = null
    private var south : ElementPuzzle? = null
    private var east : ElementPuzzle? = null
    private var west : ElementPuzzle? = null
    private var northEast : ElementPuzzle? = null
    private var southEast : ElementPuzzle? = null
    private var southWest : ElementPuzzle? = null
    private var northWest : ElementPuzzle? = null
    fun setDirection(ref: ElementPuzzle, direction: Directions) {
        when(direction) {
            Directions.North -> north = ref
            Directions.South -> south = ref
            Directions.East -> east = ref
            Directions.West -> west = ref
            Directions.NorthEast -> northEast = ref
            Directions.SouthEast -> southEast = ref
            Directions.SouthWest -> southWest = ref
            Directions.NorthWest -> northWest = ref
        }
    }
    fun whatsToMy(direction: Directions): ElementPuzzle? {
        return when(direction) {
            Directions.North -> north
            Directions.South -> south
            Directions.East -> east
            Directions.West -> west
            Directions.NorthEast ->  northEast
            Directions.SouthEast -> southEast
            Directions.SouthWest -> southWest
            Directions.NorthWest -> northWest
        }
    }
}
enum class Directions {
    North,
    South,
    East,
    West,
    NorthEast,
    SouthEast,
    SouthWest,
    NorthWest
}