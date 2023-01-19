package pl.put.poznan.sorting_madness.rest

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import pl.put.poznan.sorting_madness.logic.algorithms.Algorithm
import pl.put.poznan.sorting_madness.logic.algorithms.SortingOrder
import pl.put.poznan.sorting_madness.rest.model.RequestModel
import java.util.*


class SortingControllerTestString {
    private var sortingService: SortingService? = null

    @BeforeEach
    fun setUp() {
        sortingService = SortingService()
    }

    @Test
    fun testSortOneDimensionalDataSetStringSS() {
        // Create a RequestModel object with the data to be sorted
        val requestModel = RequestModel<String>(ArrayList(listOf("Warszawa", "Kraków", "Poznań", "Zakopane", "Amsterdam")),
            "length", SortingOrder.ASCENDING, 0, Algorithm.SELECTION_SORT)

        // Call the function and get the result
        val response = sortingService!!.sortOneDimensionalDataSetString(requestModel)

        // Assert that the result is not null
        assertNotNull(response)

        // Assert that the result is sorted correctly
        assertEquals("Amsterdam", response[0].sortedData[0])
        assertEquals("Zakopane", response[0].sortedData[response[0].sortedData.size - 1])

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
    fun testSortOneDimensionalDataSetStringQS() {
        // Create a RequestModel object with the data to be sorted
        val requestModel = RequestModel<String>(ArrayList(listOf("Warszawa", "Kraków", "Poznań", "Zakopane", "Amsterdam")),
            "length", SortingOrder.DESCENDING, 0, Algorithm.QUICK_SORT)

        // Call the function and get the result
        val response = sortingService!!.sortOneDimensionalDataSetString(requestModel)

        // Assert that the result is not null
        assertNotNull(response)

        // Assert that the result is sorted correctly
        assertEquals("Zakopane", response[0].sortedData[0])
        assertEquals("Amsterdam", response[0].sortedData[response[0].sortedData.size - 1])

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
    fun testSortOneDimensionalDataSetStringIS() {
        // Create a RequestModel object with the data to be sorted
        val requestModel = RequestModel<String>(ArrayList(listOf("Warszawa", "Kraków", "Poznań", "Zakopane", "Amsterdam")),
            "length", SortingOrder.ASCENDING, 0, Algorithm.INSERTION_SORT)

        // Call the function and get the result
        val response = sortingService!!.sortOneDimensionalDataSetString(requestModel)

        // Assert that the result is not null
        assertNotNull(response)

        // Assert that the result is sorted correctly
        assertEquals("Amsterdam", response[0].sortedData[0])
        assertEquals("Zakopane", response[0].sortedData[response[0].sortedData.size - 1])

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
    fun testSortOneDimensionalDataSetStringBS() {
        // Create a RequestModel object with the data to be sorted
        val requestModel = RequestModel<String>(ArrayList(listOf("Warszawa", "Kraków", "Poznań", "Zakopane", "Amsterdam")),
            "length", SortingOrder.ASCENDING, 0, Algorithm.BUBBLE_SORT)

        // Call the function and get the result
        val response = sortingService!!.sortOneDimensionalDataSetString(requestModel)

        // Assert that the result is not null
        assertNotNull(response)

        // Assert that the result is sorted correctly
        assertEquals("Amsterdam", response[0].sortedData[0])
        assertEquals("Zakopane", response[0].sortedData[response[0].sortedData.size - 1])

        // Assert that the sorting algorithm used is bubble sort
        assertEquals(Algorithm.BUBBLE_SORT, response[0].algorithm)

        // Assert that the number of iterations is 0
        assertEquals(0, response[0].iterationNumber)

        // Assert that the sorting order is ascending
        assertEquals(SortingOrder.ASCENDING, response[0].sortingOrder)

        // Assert that the property used for sorting is "length"
        assertEquals("length", response[0].property)
    }

    @Test
    fun testSortOneDimensionalDataSetStringHS() {
        // Create a RequestModel object with the data to be sorted
        val requestModel = RequestModel<String>(ArrayList(listOf("Warszawa", "Kraków", "Poznań", "Zakopane", "Amsterdam")),
            "length", SortingOrder.DESCENDING, 0, Algorithm.HEAP_SORT)

        // Call the function and get the result
        val response = sortingService!!.sortOneDimensionalDataSetString(requestModel)

        // Assert that the result is not null
        assertNotNull(response)

        // Assert that the result is sorted correctly
        assertEquals("Zakopane", response[0].sortedData[0])
        assertEquals("Amsterdam", response[0].sortedData[response[0].sortedData.size - 1])

        // Assert that the sorting algorithm used is heap sort
        assertEquals(Algorithm.HEAP_SORT, response[0].algorithm)

        // Assert that the number of iterations is 0
        assertEquals(0, response[0].iterationNumber)

        // Assert that the sorting order is descending
        assertEquals(SortingOrder.DESCENDING, response[0].sortingOrder)

        // Assert that the property used for sorting is "length"
        assertEquals("length", response[0].property)
    }
}