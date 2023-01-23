package pl.put.poznan.sorting_madness.rest

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import pl.put.poznan.sorting_madness.logic.algorithms.Algorithm
import pl.put.poznan.sorting_madness.logic.algorithms.SortingOrder
import pl.put.poznan.sorting_madness.rest.model.RequestModel
import java.util.*

class SortingControllerTestFloat {
    private var sortingService: SortingService? = null

    @BeforeEach
    fun setUp() {
        sortingService = SortingService()
    }

    @Test
    fun testSortOneDimensionalDataSetFloatSS() {
        // Create a RequestModel object with the data to be sorted
        val requestModel = RequestModel<Float>(ArrayList(listOf(34.5f, -90.7f, 78.0f, 6.5f, 23.3f, 23.0f)),
            "length", SortingOrder.ASCENDING, 0, Algorithm.SELECTION_SORT)

        // Call the function and get the result
        val response = sortingService!!.sortOneDimensionalDataSetFloat(requestModel)

        // Assert that the result is not null
        assertNotNull(response)

        // Assert that the result is sorted correctly
        assertEquals(-90.7f, response[0].sortedData[0])
        assertEquals(78.0f, response[0].sortedData[response[0].sortedData.size - 1])

        // Assert that the sorting algorithm used is selection sort
        assertEquals(Algorithm.SELECTION_SORT, response[0].algorithm)

        // Assert that the number of iterations is 0
        assertEquals(0, response[0].iterationNumber)

        // Assert that the sorting order is ascending
        assertEquals(SortingOrder.ASCENDING, response[0].sortingOrder)

        // Assert that the property used for sorting is "length"
        assertEquals("length", response[0].property)
    }

    @Test
    fun testSortOneDimensionalDataSetFloatQS() {
        // Create a RequestModel object with the data to be sorted
        val requestModel = RequestModel<Float>(ArrayList(listOf(34.5f, -90.7f, 78.0f, 6.5f, 23.3f, 23.0f)),
            "length", SortingOrder.DESCENDING, 0, Algorithm.QUICK_SORT)

        // Call the function and get the result
        val response = sortingService!!.sortOneDimensionalDataSetFloat(requestModel)

        // Assert that the result is not null
        assertNotNull(response)

        // Assert that the result is sorted correctly
        assertEquals(78.0f, response[0].sortedData[0])
        assertEquals(-90.7f, response[0].sortedData[response[0].sortedData.size - 1])

        // Assert that the sorting algorithm used is quick sort
        assertEquals(Algorithm.QUICK_SORT, response[0].algorithm)

        // Assert that the number of iterations is 0
        assertEquals(0, response[0].iterationNumber)

        // Assert that the sorting order is descending
        assertEquals(SortingOrder.DESCENDING, response[0].sortingOrder)

        // Assert that the property used for sorting is "length"
        assertEquals("length", response[0].property)
    }

    @Test
    fun testSortOneDimensionalDataSetFloatIS() {
        // Create a RequestModel object with the data to be sorted
        val requestModel = RequestModel<Float>(ArrayList(listOf(34.5f, -90.7f, 78.0f, 6.5f, 23.3f, 23.0f)),
            "length", SortingOrder.ASCENDING, 0, Algorithm.INSERTION_SORT)

        // Call the function and get the result
        val response = sortingService!!.sortOneDimensionalDataSetFloat(requestModel)

        // Assert that the result is not null
        assertNotNull(response)

        // Assert that the result is sorted correctly
        assertEquals(-90.7f, response[0].sortedData[0])
        assertEquals(78.0f, response[0].sortedData[response[0].sortedData.size - 1])

        // Assert that the sorting algorithm used is insertion sort
        assertEquals(Algorithm.INSERTION_SORT, response[0].algorithm)

        // Assert that the number of iterations is 0
        assertEquals(0, response[0].iterationNumber)

        // Assert that the sorting order is ascending
        assertEquals(SortingOrder.ASCENDING, response[0].sortingOrder)

        // Assert that the property used for sorting is "length"
        assertEquals("length", response[0].property)
    }

    @Test
    fun testSortOneDimensionalDataSetFloatHS() {
        // Create a RequestModel object with the data to be sorted
        val requestModel = RequestModel<Float>(ArrayList(listOf(34.5f, -90.7f, 78.0f, 6.5f, 23.3f, 23.0f)),
            "length", SortingOrder.DESCENDING, 0, Algorithm.HEAP_SORT)

        // Call the function and get the result
        val response = sortingService!!.sortOneDimensionalDataSetFloat(requestModel)

        // Assert that the result is not null
        assertNotNull(response)

        // Assert that the result is sorted correctly
        assertEquals(78.0f, response[0].sortedData[0])
        assertEquals(-90.7f, response[0].sortedData[response[0].sortedData.size - 1])

        // Assert that the sorting algorithm used is heap sort
        assertEquals(Algorithm.HEAP_SORT, response[0].algorithm)

        // Assert that the number of iterations is 0
        assertEquals(0, response[0].iterationNumber)

        // Assert that the sorting order is descending
        assertEquals(SortingOrder.DESCENDING, response[0].sortingOrder)

        // Assert that the property used for sorting is "length"
        assertEquals("length", response[0].property)
    }

    @Test
    fun testSortOneDimensionalDataSetFloatBS() {
        // Create a RequestModel object with the data to be sorted
        val requestModel = RequestModel<Float>(ArrayList(listOf(34.5f, -90.7f, 78.0f, 6.5f, 23.3f, 23.0f)),
            "length", SortingOrder.ASCENDING, 0, Algorithm.BUBBLE_SORT)

        // Call the function and get the result
        val response = sortingService!!.sortOneDimensionalDataSetFloat(requestModel)

        // Assert that the result is not null
        assertNotNull(response)

        // Assert that the result is sorted correctly
        assertEquals(-90.7f, response[0].sortedData[0])
        assertEquals(78.0f, response[0].sortedData[response[0].sortedData.size - 1])

        // Assert that the sorting algorithm used is bubble sort
        assertEquals(Algorithm.BUBBLE_SORT, response[0].algorithm)

        // Assert that the number of iterations is 0
        assertEquals(0, response[0].iterationNumber)

        // Assert that the sorting order is ascending
        assertEquals(SortingOrder.ASCENDING, response[0].sortingOrder)

        // Assert that the property used for sorting is "length"
        assertEquals("length", response[0].property)
    }
}