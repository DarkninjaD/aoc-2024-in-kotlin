package aoc.`in`.kotlin

import aoc.`in`.kotlin.utils.readInput
import kotlin.test.Test
import kotlin.test.assertEquals

class Day02Test {
    // Test to cover the example provided
    @Test fun`we should find two safe reports`() {
        val totalSafeReports = dayTwoPartOne(readInput("Day02_test"))
        assertEquals(2, totalSafeReports, "We should only have two")
    }
    @Test fun`Now with Problem Dampener`() {
        val totalSafeReports = dayTwoPartTwo(readInput("Day02_test"))
        assertEquals(4, totalSafeReports, "4th and 5th reports should past")
    }
    // the following Test should fail

    @Test fun`Regardless which level is removed it should fail`() {
        val isSafe = levelsChecker2(listOf(9,7,6,2,1))
        assertEquals(false,isSafe, "removing any will fail")
    }

    // Bellow are all the edge case that should pass
    // the following test should handle ascending vs descending errors
    // Ascending list with one Descending error
    @Test fun`Ascending list, with one Descending starting level error`() {
        val isSafe = levelsChecker2(listOf(10,2,3,4,5))
        assertEquals(true, isSafe, "Its not managing starting Descending error")
    }
    @Test fun`Ascending list, with one Descending second level error`() {
        val report = levelsChecker2(listOf(1,-10,2,3,4,5))
        assertEquals(true ,report, "Its not managing second Descending error")
    }
    @Test fun`Ascending list, with one Descending middling level error`() {
        val isSafe = levelsChecker2(listOf(1,2,3,-10,4,5))
        assertEquals(true, isSafe, "Its not managing middling Descending error")
    }
    @Test fun`Ascending list, with one Descending error at ending level error`() {
        val isSafe = levelsChecker2(listOf(1,2,3,4,5,-10))
        assertEquals(true, isSafe, "Its not managing ending Descending error")
    }
    // Descending list with one Ascending error
    @Test fun`Descending list, with one Ascending starting level error`() {
        val isSafe = levelsChecker2(listOf(-10,5,4,2,1))
        assertEquals(true, isSafe, "Descending list is not managing ending Ascending error")
    }
    @Test fun`Descending list, with one Ascending second level error`() {
        val report = levelsChecker2(listOf(5,10,4,3,2,1))
        assertEquals(true ,report, "Its not managing second Descending error")
    }
    @Test fun`Descending list, with one Ascending middling level error`() {
        val isSafe = levelsChecker2(listOf(5,4,10,3,1))
        assertEquals(true, isSafe, "Descending list is not managing ending Ascending error")
    }
    @Test fun`Descending list, with one Ascending ending level error`() {
        val isSafe = levelsChecker2(listOf(4,3,2,1,10))
        assertEquals(true, isSafe, "Descending list is not managing ending Ascending error")
    }

    // The following test should handle out of range error
    // Ascending list with one Ascending error
    @Test fun`Ascending list, with one Ascending starting level error`() {
        val isSafe = levelsChecker2(listOf(-10,2,3,4,5))
        assertEquals(true, isSafe, "Ascending list is not managing starting Ascending error")
    }
    @Test fun`Ascending list, with one Ascending second level error`() {
        val report = levelsChecker2(listOf(1,10,3,4,5))
        assertEquals(true ,report, "Its not managing second Ascending error")
    }
    @Test fun`Ascending list, with one Ascending middling level error`() {
        val isSafe = levelsChecker2(listOf(1,3,10,4,5))
        assertEquals(true, isSafe, "Ascending list is not managing middling Ascending error")
    }
    @Test fun`Ascending list, with one Ascending ending level error`() {
        val isSafe = levelsChecker2(listOf(1,2,4,5,10))
        assertEquals(true, isSafe, "Ascending list is not managing ending Ascending error")
    }
    // Descending list with one Descending error
    @Test fun`Descending list, with one Descending starting level error`() {
        val isSafe = levelsChecker2(listOf(10,5,4,2,1))
        assertEquals(true, isSafe, "Descending list is not managing starting Descending error")
    }
    @Test fun`Descending list, with one Descending second level error`() {
        val report = levelsChecker2(listOf(5,-10,4,3,2,1))
        assertEquals(true ,report, "Its not managing second Descending error")
    }
    @Test fun`Descending list, with one Descending middling level error`() {
        val isSafe = levelsChecker2(listOf(5,4,-10,3,1))
        assertEquals(true, isSafe, "Descending list is not managing middling Descending error")
    }
    @Test fun`Descending list, with one Descending ending level error`() {
        val isSafe = levelsChecker2(listOf(4,3,2,1,-10))
        assertEquals(true, isSafe, "Descending list is not managing ending Descending error")
    }
}