package pl.put.poznan.sorting_madness.logic.algorithms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InsertionSortTest {

    List<String> listOfStrings, sortedListOfStrings, emptyList;

    List<Integer> listOfIntegers, sortedlistOfIntegers;

    InsertionSort insertionSortClass;

    @Mock
    SortedDataResponse<String> sortedDataResponseString;

    @Mock
    SortedDataResponse<Integer> sortedDataResponseNumber;

    @Spy
    InsertionSort insertionSort;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        listOfStrings = new ArrayList<>();
        sortedListOfStrings = new ArrayList<>();
        listOfIntegers = new ArrayList<>();
        sortedlistOfIntegers = new ArrayList<>();
        emptyList = new ArrayList<>();
        insertionSortClass = new InsertionSort();
    }

    @Test
    @DisplayName("Test insertion sort on list of string values")
    void insertionSortOnString() {
        listOfStrings.addAll(List.of("Kot", "Pies", "Słoń", "Borsuk"));
        sortedListOfStrings.addAll(List.of("Borsuk", "Kot", "Pies", "Słoń"));

        SortedDataResponse<String> dataResponse = insertionSortClass.insertionSort(listOfStrings, SortingOrder.ASCENDING);

        assertArrayEquals(dataResponse.sortedData.toArray(), sortedListOfStrings.toArray());
    }

    @Test
    @DisplayName("Test insertion sort on list of int values")
    void insertionSortOnNumber() {
        listOfIntegers.addAll(List.of(55, 12, 44, 2));
        sortedlistOfIntegers.addAll(List.of(2, 12, 44, 55));
        when(sortedDataResponseNumber.getSortedData()).thenReturn(sortedlistOfIntegers);

        SortedDataResponse<Integer> dataResponse = insertionSortClass.insertionSort(listOfIntegers, SortingOrder.ASCENDING);

        assertArrayEquals(dataResponse.sortedData.toArray(), sortedlistOfIntegers.toArray());
    }


    @Test
    @DisplayName("Test `run` on list of string values with indicated max number of iterations")
    void testRunLimitedInsertionSort() {
        int iterationNumber = 3;
        when(sortedDataResponseString.getSortedData()).thenReturn(emptyList);
        when(insertionSort.limitedInsertionSort(emptyList, iterationNumber, SortingOrder.ASCENDING)).thenReturn(sortedDataResponseString);

        insertionSort.run(emptyList, iterationNumber, SortingOrder.ASCENDING);

        verify(insertionSort, times(1)).limitedInsertionSort(emptyList, iterationNumber, SortingOrder.ASCENDING);
        assertEquals(sortedDataResponseString, insertionSort.run(emptyList, iterationNumber, SortingOrder.ASCENDING));
    }

    @Test
    @DisplayName("Test `run` on list of string values with indicated ascending sort order")
    void testRunInsertionSortOnAscending() {
        when(sortedDataResponseString.getSortedData()).thenReturn(emptyList);
        when(insertionSort.insertionSort(emptyList, SortingOrder.ASCENDING)).thenReturn(sortedDataResponseString);

        insertionSort.run(emptyList, SortingOrder.ASCENDING);

        verify(insertionSort, times(1)).insertionSort(emptyList, SortingOrder.ASCENDING);
        assertEquals(sortedDataResponseString, insertionSort.run(emptyList, SortingOrder.ASCENDING));
    }

    @Test
    @DisplayName("Test `run` on list of string values with indicated descending sort order")
    void testRunInsertionSortOnDescending() {
        when(sortedDataResponseString.getSortedData()).thenReturn(emptyList);
        when(insertionSort.insertionSort(emptyList, SortingOrder.DESCENDING)).thenReturn(sortedDataResponseString);

        insertionSort.run(emptyList, SortingOrder.DESCENDING);

        verify(insertionSort, times(1)).insertionSort(emptyList, SortingOrder.DESCENDING);
        assertEquals(sortedDataResponseString, insertionSort.run(emptyList, SortingOrder.DESCENDING));
    }
}