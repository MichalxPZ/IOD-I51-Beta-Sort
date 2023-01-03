package pl.put.poznan.sorting_madness.logic.json_algorithms;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import pl.put.poznan.sorting_madness.logic.algorithms.SortingOrder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JsonQuickSortTest {

    JsonArray jsonArray, sortedJsonArray;

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
        sortedJsonArray = new JsonArray();
        getSortedArrayByName();
        jsonQuickSort = new JsonQuickSort();

    }

    void getArrayWithValues() {
        jsonArray.add(obj1);
        jsonArray.add(obj2);
        jsonArray.add(obj3);
        jsonArray.add(obj4);
    }

    void getSortedArrayByName() {
        sortedJsonArray.add(obj3);
        sortedJsonArray.add(obj1);
        sortedJsonArray.add(obj4);
        sortedJsonArray.add(obj2);
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
    void testQuickSort() {
        JsonArray result = jsonQuickSort.quickSort(jsonArray, attributeName, SortingOrder.ASCENDING);

        assertArrayEquals(result.asList().toArray(), sortedJsonArray.asList().toArray());
    }

    @Test
    void testLimitedQuickSort() {

        JsonArray result = spyJsonQuickSort.limitedQuickSort(jsonArray, attributeName, SortingOrder.ASCENDING, 3);

        verify(spyJsonQuickSort, atLeast(1)).limitedQuick(any(JsonArray.class), anyInt(), anyInt(), anyString(), anyInt());
        assertArrayEquals(result.asList().toArray(), sortedJsonArray.asList().toArray());
    }

    @Test
    void testRunQuickSort() {

        when(sortedJsonDataResponse.getSortedData()).thenReturn(sortedJsonArray);
        when(spyJsonQuickSort.quickSort(jsonArray, attributeName, SortingOrder.ASCENDING)).thenReturn(sortedJsonArray);

        SortedJsonDataResponse result = spyJsonQuickSort.run(jsonArray, attributeName, SortingOrder.ASCENDING);

        verify(spyJsonQuickSort, atLeast(1)).quickSort(jsonArray, attributeName, SortingOrder.ASCENDING);
        assertEquals(sortedJsonDataResponse.getSortedData(), result.sortedData);
    }

    @Test
    void testRunLimitedQuickSort() {
        int iterationNumber = 3;

        when(sortedJsonDataResponse.getSortedData()).thenReturn(sortedJsonArray);
        when(spyJsonQuickSort.limitedQuickSort(jsonArray, attributeName, SortingOrder.ASCENDING, iterationNumber)).thenReturn(sortedJsonArray);

        SortedJsonDataResponse result = spyJsonQuickSort.run(jsonArray, attributeName, iterationNumber, SortingOrder.ASCENDING);

        verify(spyJsonQuickSort, atLeast(1)).limitedQuickSort(jsonArray, attributeName, SortingOrder.ASCENDING, iterationNumber);
        assertEquals(sortedJsonDataResponse.getSortedData(), result.sortedData);
    }
}