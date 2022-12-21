package pl.put.poznan.sorting_madness.logic.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * The QuickSort class implements the {@link Sortable} interface and provides methods for
 * sorting a list of elements using the merge sort algorithm.
 */
public class QuickSort implements Sortable {

    public long executionTime = 0;

    /**
     * Sorts the given array in ascending or descending order using the QuickSort algorithm.
     * @param array the list to be sorted
     * @param order the desired order for the list (ascending or descending)
     * @return a {@link SortedDataResponse} object containing the sorted list and the execution time of the sorting algorithm
     */
    public <T extends Comparable<T>> SortedDataResponse<T> quickSort(List<T> array, SortingOrder order) {

        long startTime = System.nanoTime();

        quick(array, 0, array.size() - 1);

        long endTime = System.nanoTime();

        System.out.println("Total execution time: " + (endTime - startTime) + " nanoseconds");
        executionTime = endTime - startTime;
        if (SortingOrder.ASCENDING.equals(order)) {
            SortedDataResponse<T> sortedDataResponse = new SortedDataResponse<T>(array,executionTime);
            return sortedDataResponse;
        } else {
            final List<T> result = new ArrayList<>(array);
            Collections.reverse(result);
            SortedDataResponse<T> sortedDataResponse = new SortedDataResponse<T>(result, executionTime);
            return sortedDataResponse;
        }
    }

    /**
     * Recursive helper method for the QuickSort algorithm. Sorts the given list between the start and end indices.
     * @param array the list to be sorted
     * @param start the starting index for the sorting
     * @param end the ending index for the sorting
     * @return a {@link SortedDataResponse} object containing the sorted list and the execution time of the sorting algorithm
     */
    public <T extends Comparable<T>> SortedDataResponse<T> quick(List<T> array, int start, int end) {

        if (end - start < 1) {
            SortedDataResponse<T> sortedDataResponse = new SortedDataResponse<T>(array,executionTime);
            return sortedDataResponse;
        }

        T pivot = array.get(end);

        int partition = partition(array, start, end, pivot);

        quick(array, start, partition - 1);
        quick(array, partition + 1, end);


        SortedDataResponse<T> sortedDataResponse = new SortedDataResponse<T>(array,executionTime);
        return sortedDataResponse;

    }

    /**
     * Sorts the given array in ascending or descending order using the QuickSort algorithm.
     * @param array the list to be sorted
     * @param maxIterations the maximum number of iterations to be performed during the sorting process
     * @param order the desired order for the list (ascending or descending)
     * @return a {@link SortedDataResponse} object containing the sorted list and the execution time of the sorting algorithm
     */
    public <T extends Comparable<T>> SortedDataResponse<T> limitedQuick(List<T> array, int start, int end, int maxIterations, SortingOrder order) {

        if (end - start < 1) {
            SortedDataResponse<T> sortedDataResponse = new SortedDataResponse<T>(array,null);
            return sortedDataResponse;
        }

        if (maxIterations == 0) {
            SortedDataResponse<T> sortedDataResponse = new SortedDataResponse<T>(array,null);
            return sortedDataResponse;
        }

        T pivot = array.get(end);

        int partition = partition(array, start, end, pivot);

        maxIterations--;

        limitedQuick(array, start, partition - 1, maxIterations, order);
        limitedQuick(array, partition + 1, end, maxIterations, order);

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

    public <T extends Comparable<T>> SortedDataResponse<T> limitedQuickSort(List<T> array, int maxIteration, SortingOrder order) {
        long startTime = System.nanoTime();
        array = limitedQuick(array, 0, array.size() - 1, maxIteration, order).getSortedData();
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

    public static <T extends Comparable<T>> int partition(List<T> array, int start, int end, T pivot) {

        int current = start;

        for (int i = start; i < end; i++) {

            if (array.get(i).compareTo(pivot) < 0) {
                T temp = array.get(i);
                array.set(i, array.get(current));
                array.set(current, temp);
                current++;
            }
        }

        array.set(end, array.get(current));
        array.set(current, pivot);

        return current;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends Comparable<T>> SortedDataResponse<T> run(List<T> array, SortingOrder order) {
        return quickSort(array, order);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends Comparable<T>> SortedDataResponse<T> run(List<T> array, int maxIterations, SortingOrder order) {
        return limitedQuickSort(array, maxIterations, order);
    }
}