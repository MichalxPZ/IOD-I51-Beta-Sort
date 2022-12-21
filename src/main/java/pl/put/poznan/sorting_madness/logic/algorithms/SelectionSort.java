package pl.put.poznan.sorting_madness.logic.algorithms;

import pl.put.poznan.sorting_madness.logic.json_algorithms.JsonSortable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * SelectionSort is a class that implements the {@link Sortable} interface and provides a method for
 * sorting a list using the Selection Sort algorithm.
 */
public class SelectionSort implements Sortable {

    /**
     * variable indicating the execution time of the algorithm in [ns]
     */
    public long executionTime = 0;

    /**
     * Sorts the specified list of elements in ascending order using the Selection Sort algorithm.
     *
     * @param array the list of elements to be sorted
     * @param order the order in which the elements should be sorted (ascending or descending)
     * @return a {@link SortedDataResponse} object containing the sorted list and the time taken to sort it
     */
    public <T extends Comparable<T>> SortedDataResponse<T> selectionSort(List<T> array, SortingOrder order) {

        long startTime = System.nanoTime();

        for (int i = 0; i < array.size() - 1; i++) {

            int minIndex = i;
            for (int j = i + 1; j < array.size(); j++) {
                if (array.get(j).compareTo(array.get(minIndex)) < 0) {
                    minIndex = j;
                }
            }

            T temp = array.get(i);
            array.set(i, array.get(minIndex));
            array.set(minIndex, temp);
        }

        long endTime = System.nanoTime();

        System.out.println("Total execution time: " + (endTime - startTime) + " nanoseconds");
        executionTime = endTime - startTime;

        if (SortingOrder.ASCENDING.equals(order)) {
            SortedDataResponse<T> sortedDataResponse = new SortedDataResponse<T>(array, executionTime);
            return sortedDataResponse;
        } else {
            final List<T> result = new ArrayList<>(array);
            Collections.reverse(result);
            SortedDataResponse<T> sortedDataResponse = new SortedDataResponse<T>(result, executionTime);
            return sortedDataResponse;
        }
    }

/**
     * Sorts the specified list of elements in ascending order using the Selection Sort algorithm,
     * up to a maximum number of iterations.
     *
     * @param array the list of elements to be sorted
     * @param maxIterations the maximum number of iterations to perform
     * @param order the order in which the elements should be sorted (ascending or descending)
     * @return a {@link SortedDataResponse} object containing the sorted list and the time taken to sort it
     */    public <T extends Comparable<T>> SortedDataResponse<T> limitedSelectionSort(List<T> array, int maxIterations, SortingOrder order) {
        long startTime = System.nanoTime();

        for (int i = 0; i < array.size() - 1 && i <= maxIterations; i++) {

            int minIndex = i;
            for (int j = i + 1; j < array.size(); j++) {
                if (array.get(j).compareTo(array.get(minIndex)) < 0) {
                    minIndex = j;
                }
            }

            T temp = array.get(i);
            array.set(i, array.get(minIndex));
            array.set(minIndex, temp);
        }

        long endTime = System.nanoTime();
        executionTime = endTime - startTime;

        if (SortingOrder.ASCENDING.equals(order)) {
            SortedDataResponse<T> sortedDataResponse = new SortedDataResponse<T>(array, executionTime);
            return sortedDataResponse;
        } else {
            final List<T> result = new ArrayList<>(array);
            Collections.reverse(result);
            SortedDataResponse<T> sortedDataResponse = new SortedDataResponse<T>(result, executionTime);
            return sortedDataResponse;
        }
    }

     /**
      * {@inheritDoc}
     */
    @Override
    public <T extends Comparable<T>> SortedDataResponse<T> run(List<T> array, SortingOrder order) {
        return selectionSort(array, order);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends Comparable<T>> SortedDataResponse<T> run(List<T> array, int maxIterations, SortingOrder order) {
        return limitedSelectionSort(array, maxIterations, order);
    }
}