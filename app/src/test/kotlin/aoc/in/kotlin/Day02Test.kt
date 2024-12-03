package aoc.`in`.kotlin

import aoc.`in`.kotlin.utils.readInput
import kotlin.test.Test
import kotlin.test.assertEquals

class Day02Test {
    @Test fun`we should find two safe reports`() {
        val totalSafeReports = dayTwoPartOne(readInput("Day02_test"))
        assertEquals(2, totalSafeReports, "We should only have two")
    }
    @Test fun`Now with Problem Dampener`() {
        val totalSafeReports = dayTwoPartTwo(readInput("Day02_test"))
        assertEquals(4, totalSafeReports, "4th and 5th reports should past")
    }

    @Test fun`Regardless which level is removed it should fail`() {
        val report = dayTwoPartTwo(listOf("9 7 6 2 1"))
        assertEquals(0 ,report, "should be zero")
    }

    @Test fun`the 2 in the middle is a bad level`() {
        val report = dayTwoPartTwo(listOf("1 3 2 4 5"))
        assertEquals(1 ,report, "should be good")
    }
    @Test fun`the 2 in the middle is a bad level second test`() {
        val report = dayTwoPartTwo(listOf("1 3 4 2 5"))
        assertEquals(1 ,report, "should be good")
    }
    @Test fun`the 6 in the middle is a bad level third test`() {
        val report = dayTwoPartTwo(listOf("1 6 3 4 5"))
        assertEquals(1 ,report, "should be good")
    }
    @Test fun`Last number is a bad level`() {
        val report = dayTwoPartTwo(listOf("1 2 4 5 3"))
        assertEquals(1 ,report, "should be good")
    }
    @Test fun`First number is a bad level`() {
        val report = dayTwoPartTwo(listOf("3 1 2 4 5"))
        assertEquals(1 ,report, "should be good")
    }
}