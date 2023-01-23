package pl.put.poznan.sorting_madness.logic.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * The InsertionSort class implements the {@link Sortable} interface and provides a method for sorting
 * a list of elements using the Insertion Sort algorithm.
 * The Insertion Sort algorithm works by iterating through the elements of the list and inserting
 * each element in its correct position in the sorted sub-list. The algorithm starts with the
 * second element in the list and compares it with the first element. If the second element is
 * smaller, it is swapped with the first element. The algorithm then moves on to the third element
 * and compares it with the first two elements. If the third element is smaller, it is swapped
 * with the second element. This process is repeated until the element is in its correct position
 * in the sorted sub-list. The algorithm then moves on to the next element and repeats the process
 * until all elements have been sorted.
 */
public class InsertionSort implements Sortable {

    /**
     * The execution time of the heap sort algorithm, in nanoseconds.
     */
    public long executionTime = 0;

    /**
     * Sorts the specified list of elements in ascending order using the Insertion Sort algorithm.
     *
     * @param array the list of elements to be sorted
     * @param order the order in which the elements should be sorted (ascending or descending)
     * @return a {@link SortedDataResponse} object containing the sorted list and the time taken to sort it
     */
    public <T extends Comparable<T>> SortedDataResponse<T> insertionSort(List<T> array, SortingOrder order) {

        long startTime = System.nanoTime();

        for (int i = 1; i < array.size(); i++) {

            T current = array.get(i);

            int j = i - 1;
            while (j >= 0 && array.get(j).compareTo(current) > 0) {
                array.set(j + 1, array.get(j));
                j--;
            }

            array.set(j + 1, current);
        }

        long endTime = System.nanoTime();

        System.out.println("Total execution time: " + (endTime - startTime) + " nanoseconds");
        executionTime = endTime - startTime;
        // Return the sorted array
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
     * Sorts the specified list of elements in ascending order using the Insertion Sort algorithm,
     * up to a maximum number of iterations.
     *
     * @param array the list of elements to be sorted
     * @param maxIterations the maximum number of iterations to perform
     * @param order the order in which the elements should be sorted (ascending or descending)
     * @return a {@link SortedDataResponse} object containing the sorted list and the time taken to sort it
     */
    public <T extends Comparable<T>> SortedDataResponse<T> limitedInsertionSort(List<T> array, int maxIterations, SortingOrder order) {

        long startTime = System.nanoTime();

        for (int i = 1; i < array.size() && i <= maxIterations; i++) {

            T current = array.get(i);

            int j = i - 1;
            while (j >= 0 && array.get(j).compareTo(current) > 0) {
                array.set(j + 1, array.get(j));
                j--;
            }

            array.set(j + 1, current);
        }

        long endTime = System.nanoTime();
        executionTime = endTime - startTime;

        if (SortingOrder.ASCENDING.equals(order)) {
            SortedDataResponse<T> sortedDataResponse = new SortedDataResponse<T>(array, executionTime);;
            return sortedDataResponse;
        } else {
            final List<T> result = new ArrayList<>(array);
            Collections.reverse(result);
            SortedDataResponse<T> sortedDataResponse = new SortedDataResponse<T>(result, executionTime);;
            return sortedDataResponse;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends Comparable<T>> SortedDataResponse<T> run(List<T> array, SortingOrder order) {
        return insertionSort(array, order);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends Comparable<T>> SortedDataResponse<T> run(List<T> array, int maxIterations, SortingOrder order) {
        return limitedInsertionSort(array, maxIterations, order);
    }
}