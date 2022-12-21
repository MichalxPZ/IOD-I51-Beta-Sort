package pl.put.poznan.sorting_madness.logic.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class QuickSort implements Sortable {

    public long executionTime = 0;

    public void main(String[] args) {
        // The array to sort
        List<Integer> array = new ArrayList<>(Arrays.asList(5, 9, 3, 1, 2, 8, 4, 7, 6));

        // Sort the array
        List<Integer> sortedArray = quickSort(array, SortingOrder.ASCENDING).getSortedData();

        // Print the sorted array
        for (Integer integer : sortedArray) {
            System.out.print(integer + " ");
        }

        System.out.println();

        // The array to sort
        array = new ArrayList<>(Arrays.asList(5, 9, 3, 1, 2, 8, 4, 7, 6));

        // Sort the array
        List<Integer> limitedSortedArray = limitedQuickSort(array, 1, SortingOrder.ASCENDING).getSortedData();

        // Print the limited sorted array
        for (Integer integer : limitedSortedArray) {
            System.out.print(integer + " ");
        }
    }

    public <T extends Comparable<T>> SortedDataResponse<T> quickSort(List<T> array, SortingOrder order) {
        // Record the starting time of the algorithm
        long startTime = System.nanoTime();

        quick(array, 0, array.size() - 1);
        // Record the ending time of the algorithm
        long endTime = System.nanoTime();
        // Print the execution time of the algorithm
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

    public <T extends Comparable<T>> SortedDataResponse<T> quick(List<T> array, int start, int end) {

        // If the array has length 1 or 0, it is already sorted
        if (end - start < 1) {
            SortedDataResponse<T> sortedDataResponse = new SortedDataResponse<T>(array,executionTime);
            return sortedDataResponse;
        }

        // Choose a pivot element
        T pivot = array.get(end);

        // Partition the array around the pivot element
        int partition = partition(array, start, end, pivot);

        // Sort the left and right partitions
        quick(array, start, partition - 1);
        quick(array, partition + 1, end);


        SortedDataResponse<T> sortedDataResponse = new SortedDataResponse<T>(array,executionTime);
        return sortedDataResponse;

    }

    public <T extends Comparable<T>> SortedDataResponse<T> limitedQuick(List<T> array, int start, int end, int maxIterations, SortingOrder order) {
        // If the array has length 1 or 0, it is already sorted
        if (end - start < 1) {
            SortedDataResponse<T> sortedDataResponse = new SortedDataResponse<T>(array,null);
            return sortedDataResponse;
        }

        // If we have reached the maximum number of iterations, return
        if (maxIterations == 0) {
            SortedDataResponse<T> sortedDataResponse = new SortedDataResponse<T>(array,null);
            return sortedDataResponse;
        }

        // Choose a pivot element
        T pivot = array.get(end);

        // Partition the array around the pivot element
        int partition = partition(array, start, end, pivot);

        // Decrement the number of iterations
        maxIterations--;

        // Sort the left and right partitions
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
        // Keep track of the current position in the array
        int current = start;

        // Loop through the array, from the start to the end
        for (int i = start; i < end; i++) {
            // If the current element is less than the pivot element,
            // swap it with the element at the current position and
            // move the current position to the right
            if (array.get(i).compareTo(pivot) < 0) {
                T temp = array.get(i);
                array.set(i, array.get(current));
                array.set(current, temp);
                current++;
            }
        }

        // Swap the pivot element with the element at the current position
        array.set(end, array.get(current));
        array.set(current, pivot);

        // Return the current position
        return current;
    }

    @Override
    public <T extends Comparable<T>> SortedDataResponse<T> run(List<T> array, SortingOrder order) {
        return quickSort(array, order);
    }

    @Override
    public <T extends Comparable<T>> SortedDataResponse<T> run(List<T> array, int maxIterations, SortingOrder order) {
        return limitedQuickSort(array, maxIterations, order);
    }
}