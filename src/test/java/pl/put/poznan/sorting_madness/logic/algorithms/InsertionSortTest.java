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

    @Mock
    List<String> listOfStrings;

    @Mock
    List<String> sortedListOfStrings;

    @Mock
    List<Integer> listOfIntegers;

    @Mock
    List<Integer> sortedlistOfIntegers;

    @Mock
    SortedDataResponse<String> sortedDataResponseString;

    @Mock
    SortedDataResponse<Integer> sortedDataResponseNumber;

    @Spy
    InsertionSort insertionSort;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Test insertion sort on list of string values")
    void insertionSortOnString() {
        listOfStrings.addAll(List.of("Kot", "Pies", "Słoń", "Borsuk"));
        sortedListOfStrings.addAll(List.of("Borsuk", "Kot", "Pies", "Słoń"));
        when(sortedDataResponseString.getSortedData()).thenReturn(sortedListOfStrings);
        InsertionSort insertionSort = new InsertionSort();
        SortedDataResponse<String> dataResponse = insertionSort.insertionSort(listOfStrings, SortingOrder.ASCENDING);
        assertArrayEquals(dataResponse.sortedData.toArray(), sortedListOfStrings.toArray());
    }

    @Test
    @DisplayName("Test insertion sort on list of int values")
    void insertionSortOnNumber() {
        listOfIntegers.addAll(List.of(55, 12, 44, 2));
        sortedlistOfIntegers.addAll(List.of(2, 12, 44, 55));
        when(sortedDataResponseNumber.getSortedData()).thenReturn(sortedlistOfIntegers);
        InsertionSort insertionSort = new InsertionSort();
        SortedDataResponse<Integer> dataResponse = insertionSort.insertionSort(listOfIntegers, SortingOrder.ASCENDING);
        assertArrayEquals(dataResponse.sortedData.toArray(), sortedlistOfIntegers.toArray());
    }


    @Test
    @DisplayName("Test `run` on list of string values with indicated max number of iterations")
    void testRunLimitedInsertionSort() {
        int iterationNumber = 3;
        List<String> myList = new ArrayList<>();
        when(sortedDataResponseString.getSortedData()).thenReturn(myList);
        when(insertionSort.limitedInsertionSort(myList, iterationNumber, SortingOrder.ASCENDING)).thenReturn(sortedDataResponseString);
        insertionSort.run(myList, iterationNumber, SortingOrder.ASCENDING);
        verify(insertionSort, times(1)).limitedInsertionSort(myList, iterationNumber, SortingOrder.ASCENDING);
        assertEquals(sortedDataResponseString, insertionSort.run(myList, iterationNumber, SortingOrder.ASCENDING));
    }

    @Test
    @DisplayName("Test `run` on list of string values with indicated ascending sort order")
    void testRunInsertionSortOnAscending() {
        List<String> myList = new ArrayList<>();
        when(sortedDataResponseString.getSortedData()).thenReturn(myList);
        when(insertionSort.insertionSort(myList, SortingOrder.ASCENDING)).thenReturn(sortedDataResponseString);
        insertionSort.run(myList, SortingOrder.ASCENDING);
        verify(insertionSort, times(1)).insertionSort(myList, SortingOrder.ASCENDING);
        assertEquals(sortedDataResponseString, insertionSort.run(myList, SortingOrder.ASCENDING));
    }

    @Test
    @DisplayName("Test `run` on list of string values with indicated descending sort order")
    void testRunInsertionSortOnDescending() {
        List<String> myList = new ArrayList<>();
        when(sortedDataResponseString.getSortedData()).thenReturn(myList);
        when(insertionSort.insertionSort(myList, SortingOrder.DESCENDING)).thenReturn(sortedDataResponseString);
        insertionSort.run(myList, SortingOrder.DESCENDING);
        verify(insertionSort, times(1)).insertionSort(myList, SortingOrder.DESCENDING);
        assertEquals(sortedDataResponseString, insertionSort.run(myList, SortingOrder.DESCENDING));
    }
}