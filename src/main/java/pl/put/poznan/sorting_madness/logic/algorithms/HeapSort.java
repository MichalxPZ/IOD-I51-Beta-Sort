package pl.put.poznan.sorting_madness.logic.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * A class that implements the {@link Sortable} interface and provides heap sort algorithm.
 * The heap sort algorithm creates a binary heap from the given list of elements, and then removes the largest element from the heap
 * and places it at the end of the list until the list is sorted.
 */
public class HeapSort implements Sortable {

    /**
     * The execution time of the heap sort algorithm, in nanoseconds.
     */
    public long executionTime = 0;

    /**
     * Sorts the given list of elements in ascending or descending order, using the heap sort algorithm.
     *
     * @param array the list of elements to be sorted
     * @param order the desired order for the sorted list (ascending or descending)
     * @return a {@link SortedDataResponse} object containing the sorted list of elements and the execution time of the algorithm
     */
    public <T extends Comparable<T>> SortedDataResponse<T> heapSort(List<T> array, SortingOrder order) {

        long startTime = System.nanoTime();

        for (int i = array.size() / 2 - 1; i >= 0; i--) {
            heapify(array, array.size(), i);
        }

        for (int i = array.size() - 1; i >= 0; i--) {
            T temp = array.get(0);
            array.set(0, array.get(i));
            array.set(i, temp);

            heapify(array, i, 0);
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
     * Sorts the given list of elements in ascending or descending order, using the heap sort algorithm.
     * The algorithm will only iterate through the list a maximum number of times, as specified by the `maxIterations` parameter.
     *
     * @param array the list of elements to be sorted
     * @param maxIterations the maximum number of iterations the algorithm should perform
     * @param order the desired order for the sorted list (ascending or descending)
     * @return a {@link SortedDataResponse} object containing the sorted list of elements and the execution time of the algorithm
     */
    public <T extends Comparable<T>> SortedDataResponse<T> limitedHeapSort(List<T> array, int maxIterations, SortingOrder order) {

        long startTime = System.nanoTime();

        for (int i = array.size() / 2 - 1; i >= 0; i--) {
            heapify(array, array.size(), i);
        }

        for (int i = array.size() - 1; i >= 0; i--) {
            T temp = array.get(0);
            array.set(0, array.get(i));
            array.set(i, temp);

            heapify(array, i, 0);

            maxIterations--;
            if (maxIterations == 0) {
                break;
            }
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
     * Helper method for the heap sort algorithm. Rebuilds the binary heap with the given root node.
     *
     * @param array the list of elements being sorted
     * @param size the size of the heap
     * @param i the index of the root node
     */
    public <T extends Comparable<T>> void heapify(List<T> array, int size, int i) {

        int left = 2 * i + 1;
        int right = 2 * i + 2;

        int largest = i;

        if (left < size && array.get(left).compareTo(array.get(largest)) > 0) {
            largest = left;
        }

        if (right < size && array.get(right).compareTo(array.get(largest)) > 0) {
            largest = right;
        }

        if (largest != i) {
            T temp = array.get(i);
            array.set(i, array.get(largest));
            array.set(largest, temp);
            heapify(array, size, largest);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends Comparable<T>> SortedDataResponse<T> run(List<T> array, SortingOrder order) {
        return heapSort(array, order);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends Comparable<T>> SortedDataResponse<T> run(List<T> array, int maxIterations, SortingOrder order) {
        return limitedHeapSort(array, maxIterations, order);
    }
}