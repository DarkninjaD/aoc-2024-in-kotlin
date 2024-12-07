package aoc.`in`.kotlin

import aoc.`in`.kotlin.utils.readInput
import aoc.`in`.kotlin.valMaster
import aoc.`in`.kotlin.dayFiveSetup
import kotlin.test.Test
import kotlin.test.assertEquals

class Day05KtTest{
    @Test fun`example input, part one`() {
        val (rule, row) = dayFiveSetup(readInput("Day05_test"))
        val count = dayFivePartOne(rule, row)
        assertEquals(143, count, "we should only have 3")
    }
    @Test fun`First Example`() {
        val (rule, row) = dayFiveSetup(readInput("Day05_test"))
        val valid = valMaster(row[0], rule)
        assertEquals(true, valid, "all should be valid")
    }
    @Test fun`Second Example`() {
        val (rule, row) = dayFiveSetup(readInput("Day05_test"))
        val valid = valMaster(row[1], rule)
        assertEquals(true, valid, "all should be valid")
    }
    @Test fun`Third Example`() {
        val (rule, row) = dayFiveSetup(readInput("Day05_test"))
        val valid = valMaster(row[2], rule)
        assertEquals(true, valid, "all should be valid")
    }
    @Test fun`Fourth Example, should fail`() {
        val (rule, row) = dayFiveSetup(readInput("Day05_test"))
        val valid = valMaster(row[3], rule)
        assertEquals(false, valid, "should fail at ")
    }
    @Test fun`Fifth Example, should fail`() {
        val (rule, row) = dayFiveSetup(readInput("Day05_test"))
        val valid = valMaster(row[4], rule)
        assertEquals(false, valid, "should fail at ")
    }
    @Test fun`Sixth Example, should fail`() {
        val (rule, row) = dayFiveSetup(readInput("Day05_test"))
        val valid = valMaster(row[5], rule)
        assertEquals(false, valid, "should fail at ")
    }
    @Test fun`example input, part two`() {
        val (rule, row) = dayFiveSetup(readInput("Day05_test"))
        val count = dayFivePartTwo(rule, row)
        assertEquals(123, count, "we should only have 3")
    }
    @Test fun`First Example, should reorder 97,75`() {
        val (rule, row) = dayFiveSetup(readInput("Day05_test"))
        val valid = valMaster2000(listOf(75,97,47,61,53), rule)
        assertEquals(47, valid, "should fail at ")
    }
    @Test fun`Second Example, should reorder 13, 29`() {
        val (rule, row) = dayFiveSetup(readInput("Day05_test"))
        val valid = valMaster2000(listOf(61,13,29), rule)
        assertEquals(29, valid, "all should be valid")
    }
    @Test fun`Third Example, should reorder 47,13`() {
        val (rule, row) = dayFiveSetup(readInput("Day05_test"))
        val valid = valMaster2000(listOf(97,13,75,29,47), rule)
        assertEquals(47, valid, "all should be valid")
    }
}