package pl.put.poznan.sorting_madness.rest

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.json.JSONException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach
import pl.put.poznan.sorting_madness.logic.algorithms.Algorithm
import pl.put.poznan.sorting_madness.logic.algorithms.SortingOrder
import pl.put.poznan.sorting_madness.rest.model.RequestJsonModel
import pl.put.poznan.sorting_madness.rest.model.ResponseModel


class SortingControllerTestJsonModel {
    private var sortingService: SortingService? = null
    private val data = JsonArray();

    @BeforeEach
    fun setUp() {
        sortingService = SortingService()
        val jsonObj1 = JsonObject()
        jsonObj1.addProperty("name", "Karol")
        jsonObj1.addProperty("age", 25)
        data.add(jsonObj1)

        val jsonObj2 = JsonObject()
        jsonObj2.addProperty("name", "Adam")
        jsonObj2.addProperty("age", 35)
        data.add(jsonObj2)

        val jsonObj3 = JsonObject()
        jsonObj3.addProperty("name", "Sara")
        jsonObj3.addProperty("age", 35)
        data.add(jsonObj3)

        val jsonObj4 = JsonObject()
        jsonObj4.addProperty("name", "Anna")
        jsonObj4.addProperty("age", 27)
        data.add(jsonObj4)
    }

    @Test
    @Throws(JSONException::class)
    fun testSortMultiDimensionalDataSetTestStringIS() {
        val requestModel = RequestJsonModel(data, "name", SortingOrder.ASCENDING, 0, Algorithm.INSERTION_SORT)

        // Call the function and get the result
        val response: ArrayList<ResponseModel<JsonArray>> = sortingService!!.sortMultiDimensionalDataSet(requestModel)

        // Assert that the result is not null
        assertNotNull(response)

        // Assert that the result is sorted correctly
        assertEquals("\"Adam\"", response[0].sortedData[0].get(0).asJsonObject.get("name").toString())
        assertEquals("\"Sara\"", response[0].sortedData[0].get(response[0].sortedData[0].size() - 1).asJsonObject.get("name").toString())

        // Assert that the sorting algorithm used is insertion sort
        assertEquals(Algorithm.INSERTION_SORT, response[0].algorithm)

        // Assert that the number of iterations is 0
        assertEquals(0, response[0].iterationNumber)

        // Assert that the sorting order is ascending
        assertEquals(SortingOrder.ASCENDING, response[0].sortingOrder)

        // Assert that the property used for sorting is "name"
        assertEquals("name", response[0].property)
    }

    @Test
    @Throws(JSONException::class)
    fun testSortMultiDimensionalDataSetTestIntHS() {
        val requestModel = RequestJsonModel(data, "age", SortingOrder.DESCENDING, 0, Algorithm.HEAP_SORT)

        // Call the function and get the result
        val response: ArrayList<ResponseModel<JsonArray>> = sortingService!!.sortMultiDimensionalDataSet(requestModel)

        // Assert that the result is not null
        assertNotNull(response)

        // Assert that the result is sorted correctly
        assertEquals(35, response[0].sortedData[0].get(0).asJsonObject.get("age").toString().toInt())
        assertEquals(25, response[0].sortedData[0].get(response[0].sortedData[0].size() - 1).asJsonObject.get("age").toString().toInt())

        // Assert that the sorting algorithm used is heap sort
        assertEquals(Algorithm.HEAP_SORT, response[0].algorithm)

        // Assert that the number of iterations is 0
        assertEquals(0, response[0].iterationNumber)

        // Assert that the sorting order is descending
        assertEquals(SortingOrder.DESCENDING, response[0].sortingOrder)

        // Assert that the property used for sorting is "name"
        assertEquals("age", response[0].property)
    }

    @Test
    @Throws(JSONException::class)
    fun testSortMultiDimensionalDataSetTestStringBB() {
        val requestModel = RequestJsonModel(data, "name", SortingOrder.ASCENDING, 0, Algorithm.BUBBLE_SORT)

        // Call the function and get the result
        val response: ArrayList<ResponseModel<JsonArray>> = sortingService!!.sortMultiDimensionalDataSet(requestModel)

        // Assert that the result is not null
        assertNotNull(response)

        // Assert that the result is sorted correctly
        assertEquals("\"Adam\"", response[0].sortedData[0].get(0).asJsonObject.get("name").toString())
        assertEquals("\"Sara\"", response[0].sortedData[0].get(response[0].sortedData[0].size() - 1).asJsonObject.get("name").toString())

        // Assert that the sorting algorithm used is bubble sort
        assertEquals(Algorithm.BUBBLE_SORT, response[0].algorithm)

        // Assert that the number of iterations is 0
        assertEquals(0, response[0].iterationNumber)

        // Assert that the sorting order is ascending
        assertEquals(SortingOrder.ASCENDING, response[0].sortingOrder)

        // Assert that the property used for sorting is "name"
        assertEquals("name", response[0].property)
    }

    @Test
    @Throws(JSONException::class)
    fun testSortMultiDimensionalDataSetTestIntMS() {
        val requestModel = RequestJsonModel(data, "age", SortingOrder.DESCENDING, 0, Algorithm.MERGE_SORT)

        // Call the function and get the result
        val response: ArrayList<ResponseModel<JsonArray>> = sortingService!!.sortMultiDimensionalDataSet(requestModel)

        // Assert that the result is not null
        assertNotNull(response)

        // Assert that the result is sorted correctly
        assertEquals(35, response[0].sortedData[0].get(0).asJsonObject.get("age").toString().toInt())
        assertEquals(25, response[0].sortedData[0].get(response[0].sortedData[0].size() - 1).asJsonObject.get("age").toString().toInt())

        // Assert that the sorting algorithm used is merge sort
        assertEquals(Algorithm.MERGE_SORT, response[0].algorithm)

        // Assert that the number of iterations is 0
        assertEquals(0, response[0].iterationNumber)

        // Assert that the sorting order is descending
        assertEquals(SortingOrder.DESCENDING, response[0].sortingOrder)

        // Assert that the property used for sorting is "name"
        assertEquals("age", response[0].property)
    }

    @Test
    @Throws(JSONException::class)
    fun testSortMultiDimensionalDataSetTestStringSS() {
        val requestModel = RequestJsonModel(data, "name", SortingOrder.ASCENDING, 0, Algorithm.SELECTION_SORT)

        // Call the function and get the result
        val response: ArrayList<ResponseModel<JsonArray>> = sortingService!!.sortMultiDimensionalDataSet(requestModel)

        // Assert that the result is not null
        assertNotNull(response)

        // Assert that the result is sorted correctly
        assertEquals("\"Adam\"", response[0].sortedData[0].get(0).asJsonObject.get("name").toString())
        assertEquals("\"Sara\"", response[0].sortedData[0].get(response[0].sortedData[0].size() - 1).asJsonObject.get("name").toString())

        // Assert that the sorting algorithm used is selection sort
        assertEquals(Algorithm.SELECTION_SORT, response[0].algorithm)

        // Assert that the number of iterations is 0
        assertEquals(0, response[0].iterationNumber)

        // Assert that the sorting order is ascending
        assertEquals(SortingOrder.ASCENDING, response[0].sortingOrder)

        // Assert that the property used for sorting is "name"
        assertEquals("name", response[0].property)
    }

    @Test
    @Throws(JSONException::class)
    fun testSortMultiDimensionalDataSetTestIntQS() {
        val requestModel = RequestJsonModel(data, "age", SortingOrder.DESCENDING, 0, Algorithm.QUICK_SORT)

        // Call the function and get the result
        val response: ArrayList<ResponseModel<JsonArray>> = sortingService!!.sortMultiDimensionalDataSet(requestModel)

        // Assert that the result is not null
        assertNotNull(response)

        // Assert that the result is sorted correctly
        assertEquals(35, response[0].sortedData[0].get(0).asJsonObject.get("age").toString().toInt())
        assertEquals(25, response[0].sortedData[0].get(response[0].sortedData[0].size() - 1).asJsonObject.get("age").toString().toInt())

        // Assert that the sorting algorithm used is quick sort
        assertEquals(Algorithm.QUICK_SORT, response[0].algorithm)

        // Assert that the number of iterations is 0
        assertEquals(0, response[0].iterationNumber)

        // Assert that the sorting order is descending
        assertEquals(SortingOrder.DESCENDING, response[0].sortingOrder)

        // Assert that the property used for sorting is "name"
        assertEquals("age", response[0].property)
    }
}