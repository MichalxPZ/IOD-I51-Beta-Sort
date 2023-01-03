package pl.put.poznan.sorting_madness.logic.json_algorithms;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import pl.put.poznan.sorting_madness.logic.algorithms.SortingOrder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JsonQuickSortTest {

    JsonArray jsonArray, sortedAscJsonArray, sortedDescJsonArray;

    JsonQuickSort jsonQuickSort;

    JsonObject obj1, obj2, obj3, obj4;

    String attributeName = "name";

    @Mock
    SortedJsonDataResponse sortedJsonDataResponse;

    @Spy
    JsonQuickSort spyJsonQuickSort;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);

        obj1 = new JsonObject();
        obj2 = new JsonObject();
        obj3 = new JsonObject();
        obj4 = new JsonObject();
        initJsonObjects();

        jsonArray = new JsonArray();
        getArrayWithValues();
        sortedAscJsonArray = new JsonArray();
        getSortedAscArrayByName();
        sortedDescJsonArray = new JsonArray();
        getSortedDescArrayByName();

        jsonQuickSort = new JsonQuickSort();

    }

    void getArrayWithValues() {
        jsonArray.add(obj1);
        jsonArray.add(obj2);
        jsonArray.add(obj3);
        jsonArray.add(obj4);
    }

    void getSortedAscArrayByName() {
        sortedAscJsonArray.add(obj3);
        sortedAscJsonArray.add(obj1);
        sortedAscJsonArray.add(obj4);
        sortedAscJsonArray.add(obj2);
    }

    void getSortedDescArrayByName() {
        sortedDescJsonArray.add(obj2);
        sortedDescJsonArray.add(obj4);
        sortedDescJsonArray.add(obj1);
        sortedDescJsonArray.add(obj3);
    }

    void initJsonObjects() {
        obj1.addProperty("name", "Betty");
        obj1.addProperty("surname", "Jameson");

        obj2.addProperty("name", "Nell");
        obj2.addProperty("surname", "Roman");

        obj3.addProperty("name", "Aiden");
        obj3.addProperty("surname", "Valenzuela");

        obj4.addProperty("name", "Maryam");
        obj4.addProperty("surname", "Gallegos");
    }

    @Test
    @DisplayName("Test quick sort on array containing json objects with indicated ascending sort order")
    void testQuickSortAscending() {
        JsonArray result = jsonQuickSort.quickSort(jsonArray, attributeName, SortingOrder.ASCENDING);

        assertArrayEquals(result.asList().toArray(), sortedAscJsonArray.asList().toArray());
    }

    @Test
    @DisplayName("Test quick sort on array containing json objects with indicated descending sort order")
    void testQuickSortDescending() {
        JsonArray result = jsonQuickSort.quickSort(jsonArray, attributeName, SortingOrder.DESCENDING);

        assertArrayEquals(result.asList().toArray(), sortedDescJsonArray.asList().toArray());
    }

    @Test
    @DisplayName("Test quick sort on array containing json objects with indicated max number of iterations")
    void testLimitedQuickSort() {

        JsonArray result = spyJsonQuickSort.limitedQuickSort(jsonArray, attributeName, SortingOrder.ASCENDING, 3);

        verify(spyJsonQuickSort, atLeast(1)).limitedQuick(any(JsonArray.class), anyInt(), anyInt(), anyString(), anyInt());
        assertArrayEquals(result.asList().toArray(), sortedAscJsonArray.asList().toArray());
    }

    @Test
    @DisplayName("Test 'run' quick sort on array containing json objects")
    void testRunQuickSort() {

        when(sortedJsonDataResponse.getSortedData()).thenReturn(sortedAscJsonArray);
        when(spyJsonQuickSort.quickSort(jsonArray, attributeName, SortingOrder.ASCENDING)).thenReturn(sortedAscJsonArray);

        SortedJsonDataResponse result = spyJsonQuickSort.run(jsonArray, attributeName, SortingOrder.ASCENDING);

        verify(spyJsonQuickSort, atLeast(1)).quickSort(jsonArray, attributeName, SortingOrder.ASCENDING);
        assertEquals(sortedJsonDataResponse.getSortedData(), result.sortedData);
    }

    @Test
    @DisplayName("Test 'run' quick sort on array containing json objects with indicated max number of iterations")
    void testRunLimitedQuickSort() {
        int iterationNumber = 3;

        when(sortedJsonDataResponse.getSortedData()).thenReturn(sortedAscJsonArray);
        when(spyJsonQuickSort.limitedQuickSort(jsonArray, attributeName, SortingOrder.ASCENDING, iterationNumber)).thenReturn(sortedAscJsonArray);

        SortedJsonDataResponse result = spyJsonQuickSort.run(jsonArray, attributeName, iterationNumber, SortingOrder.ASCENDING);

        verify(spyJsonQuickSort, atLeast(1)).limitedQuickSort(jsonArray, attributeName, SortingOrder.ASCENDING, iterationNumber);
        assertEquals(sortedJsonDataResponse.getSortedData(), result.sortedData);
    }
}