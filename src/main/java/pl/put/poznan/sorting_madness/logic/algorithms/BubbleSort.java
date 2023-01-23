package pl.put.poznan.sorting_madness.logic.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * A class that implements the {@link Sortable} interface and provides bubble sort algorithm.
 * The bubble sort algorithm iterates through the list of elements and compares adjacent elements,
 * swapping them if they are in the wrong order. The algorithm repeats this process until the list is sorted.
 */
public class BubbleSort implements Sortable {

    /**
     * The execution time of the bubble sort algorithm, in nanoseconds.
     */

    public long executionTime = 0;

    /**
     * Sorts the given list of elements in ascending or descending order, using the bubble sort algorithm.
     *
     * @param array the list of elements to be sorted
     * @param order the desired order for the sorted list (ascending or descending)
     * @return a {@link SortedDataResponse} object containing the sorted list of elements and the execution time of the algorithm
     */
    public <T extends Comparable<T>> SortedDataResponse<T> bubbleSort(List<T> array, SortingOrder order) {
        long startTime = System.nanoTime();

        boolean sorted = false;

        while (!sorted) {

            sorted = true;

            for (int i = 0; i < array.size() - 1; i++) {
                if (array.get(i).compareTo(array.get(i + 1)) > 0) {
                    T temp = array.get(i);
                    array.set(i, array.get(i + 1));
                    array.set(i + 1, temp);
                    sorted = false;
                }
            }
        }

        long endTime = System.nanoTime();

        long elapsedTime = endTime - startTime;

        System.out.println("Elapsed time: " + elapsedTime + " nanoseconds");
        executionTime = elapsedTime;
        if (SortingOrder.ASCENDING.equals(order)) {
            SortedDataResponse<T> sortedDataResponse = new SortedDataResponse<T>(array, elapsedTime);
            return sortedDataResponse;
        } else {
            final List<T> result = new ArrayList<>(array);
            Collections.reverse(result);
            SortedDataResponse<T> sortedDataResponse = new SortedDataResponse<T>(result, elapsedTime);
            return sortedDataResponse;
        }
    }

    /**
     * Sorts the given list of elements in ascending or descending order, using the bubble sort algorithm.
     * The algorithm will only iterate through the list a maximum number of times, as specified by the `maxIterations` parameter.
     *
     * @param array the list of elements to be sorted
     * @param maxIterations the maximum number of iterations the algorithm should perform
     * @param order the desired order for the sorted list (ascending or descending)
     * @return a {@link SortedDataResponse} object containing the sorted list of elements and the execution time of the algorithm
     */
    public <T extends Comparable<T>> SortedDataResponse<T> limitedBubbleSort(List<T> array, int maxIterations, SortingOrder order) {

        boolean sorted = false;

        int iterationCount = 0;

        long startTime = System.nanoTime();

        while (!sorted && iterationCount < maxIterations) {

            sorted = true;

            for (int i = 0; i < array.size() - 1; i++) {
                if (array.get(i).compareTo(array.get(i + 1)) > 0) {
                    T temp = array.get(i);
                    array.set(i, array.get(i + 1));
                    array.set(i + 1, temp);
                    sorted = false;
                }
            }

            iterationCount++;
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
        return bubbleSort(array, order);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends Comparable<T>> SortedDataResponse<T> run(List<T> array, int maxIterations, SortingOrder order) {
        return limitedBubbleSort(array, maxIterations, order);
    }
}