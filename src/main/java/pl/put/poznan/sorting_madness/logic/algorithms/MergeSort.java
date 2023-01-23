package pl.put.poznan.sorting_madness.logic.algorithms;

import java.util.*;

/**
 * The MergeSort class implements the {@link Sortable} interface and provides methods for
 * sorting a list of elements using the merge sort algorithm.
 * The merge sort algorithm works by dividing the input list into two halves,
 * sorting each half, and then merging the sorted halves back together.
 * The divide and conquer approach used by merge sort allows it to have a time
 * complexity of O(n * log(n)), making it more efficient than other sorting algorithms for large lists.
 * The class also provides a method for sorting the list with a maximum number of iterations,
 * allowing for the sorting process to be limited.
 */
public class MergeSort implements Sortable {

    public long executionTime = 0;

    /**
     Sorts the given list using the merge sort algorithm with a specified sorting order.
     @param array the list to be sorted
     @param order the desired sorting order (ascending or descending)
     @return a {@link SortedDataResponse} object containing the sorted list and the execution time of the sorting process
     */
    public <T extends Comparable<T>> SortedDataResponse<T> mergeSortTime(List<T> array, SortingOrder order) {

        long startTime = System.nanoTime();

        List<T> sortedArray = mergeSort(array, order).getSortedData();

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

    public <T extends Comparable<T>> SortedDataResponse<T> mergeSort(List<T> array, SortingOrder order) {

        long startTime = System.nanoTime();

        if (array.size() <= 1) {
            SortedDataResponse<T> sortedDataResponse = new SortedDataResponse<T>(array, null);
            return sortedDataResponse;
        }

        int middle = array.size() / 2;
        List<T> left = new ArrayList<T>((Collection<? extends T>) Arrays.asList(Arrays.copyOfRange(array.toArray(new Integer[0]), 0, middle)));
        List<T> right = new ArrayList<T>((Collection<? extends T>) Arrays.asList(Arrays.copyOfRange(array.toArray(new Integer[0]), middle, array.size())));

        List<T> newLeft = mergeSort(left, order).getSortedData();
        List<T> newRight = mergeSort(right, order).getSortedData();

        merge(newLeft, newRight, array);

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
     * Sorts the given list using the merge sort algorithm with a specified maximum number of iterations and sorting order.
     * @param array the list to be sorted
     * @param maxIteration the maximum number of iterations to be performed during the sorting process
     * @param order the desired sorting order (ascending or descending)
     * @return a {@link SortedDataResponse} object containing the sorted list and the execution time of the sorting process
     */
    public <T extends Comparable<T>> SortedDataResponse<T> limitedMergeSort(List<T> array, int maxIteration, SortingOrder order) {

        if (array.size() <= 1 || maxIteration == 0) {
            SortedDataResponse<T> sortedDataResponse = new SortedDataResponse<T>(array, executionTime);;
            return sortedDataResponse;
        }

        int middle = array.size() / 2;
        List<T> left = new ArrayList<T>((Collection<? extends T>) Arrays.asList(Arrays.copyOfRange(array.toArray(new Integer[0]), 0, middle)));
        List<T> right = new ArrayList<T>((Collection<? extends T>) Arrays.asList(Arrays.copyOfRange(array.toArray(new Integer[0]), middle, array.size())));

        long startTime = System.nanoTime();

        List<T> newLeft = limitedMergeSort(left, maxIteration - 1, order).getSortedData();
        List<T> newRight = limitedMergeSort(right, maxIteration - 1, order).getSortedData();

        merge(newLeft, newRight, array);

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

    public <T extends Comparable<T>> void merge(List<T> left, List<T> right, List<T> array) {
        int i = 0, j = 0, k = 0;

        while (i < left.size() && j < right.size()) {
            if (left.get(i).compareTo(right.get(j)) <= 0) {
                array.set(k, left.get(i));
                i++;
            } else {
                array.set(k, right.get(j));
                j++;
            }
            k++;
        }

        while (i < left.size()) {
            array.set(k, left.get(i));
            i++;
            k++;
        }

        while (j < right.size()) {
            array.set(k, right.get(j));
            j++;
            k++;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends Comparable<T>> SortedDataResponse<T> run(List<T> array, SortingOrder order) {
        return mergeSortTime(array, order);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends Comparable<T>> SortedDataResponse<T> run(List<T> array, int maxIterations, SortingOrder order) {
        return limitedMergeSort(array, maxIterations, order);
    }
}