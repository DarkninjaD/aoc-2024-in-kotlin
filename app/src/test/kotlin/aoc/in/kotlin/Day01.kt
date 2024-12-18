/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package aoc.`in`.kotlin

import aoc.`in`.kotlin.utils.readInput
import kotlin.test.Test
import kotlin.test.assertEquals

class Day01Test {
    @Test fun `Finds the example Q&A of part one`() {
        val totalDistance = dayOnePartOne(readInput("Day01_test"))
        assertEquals(11, totalDistance, "it should be 2 + 1 + 0 + 1 + 2 + 5 = 11")
    }
    @Test fun `Finds the example Q&A of part two`() {
        val totalDistance = dayOnePartTwo(readInput("Day01_test"))
        assertEquals(31, totalDistance, "it should be 9 + 4 + 0 + 0 + 9 + 9 = 31")
    }
}
