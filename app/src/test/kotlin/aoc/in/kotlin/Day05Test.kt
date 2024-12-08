package aoc.`in`.kotlin

import aoc.`in`.kotlin.utils.readInput
import kotlin.test.Test
import kotlin.test.assertEquals
import aoc.`in`.kotlin.doList

class Day05Test {

    @Test fun`example input, 4 mul commands should be found summing up to 161`() {
        val total = day03PartOne(readInput("Day03_test"))
        assertEquals(161, total , "Should be the same")
    }
    @Test fun`example input, 4 mul commands should be found summing up to`() {
        val total = day03PartTwo(readInput("Day03_test2"))
        assertEquals(48, total , "Should be the same")
    }

    @Test fun`shouldn't find anything mul commands, square brakes test`() {
        val result = mulFinder("mul[23,3]")
        assertEquals(null, result, "Good")
    }
    @Test fun`shouldn't find anything mul commands, one square brakes test`() {
        val result = mulFinder("mul[23,3)")
        assertEquals(null, result, "Good")
    }
    @Test fun`shouldn't find anything mul commands, symbol in input`() {
        val result = mulFinder("mul(23*,3)")
        assertEquals(null, result, "Good")
    }
    @Test fun`shouldn't find anything mul commands, spaces in input`() {
        val result = mulFinder("mul(23, 3)")
        assertEquals(null, result, "Good")
    }
    @Test fun`shouldn't find anything mul commands, incomplete input`() {
        val result = mulFinder("mul(23,3")
        assertEquals(null, result, "Good")
    }
    @Test fun`shouldn't find anything mul commands, incomplete input two`() {
        val result = mulFinder("mul(23why()")
        assertEquals(null, result, "Good")
    }
    @Test fun`shouldn't find anything mul commands, incomplete input three`() {
        val result = mulFinder("mul(why()")
        assertEquals(null, result, "Good")
    }
    @Test fun`doList, test`() {
        val test = doList(listOf("do()do()do()mul(456,456)don't()", "don't()3do()don't()mul(123,123)don't()do()mul(345,234)","do()don't()5"))
        assertEquals(2, test.size, "the last enabled set sometime easy to miss")
    }
}