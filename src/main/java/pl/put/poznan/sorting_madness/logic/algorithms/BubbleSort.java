package pl.put.poznan.sorting_madness.logic.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BubbleSort implements Sortable {

    public long executionTime = 0;

    public void main(String[] args) {
        // The array to sort
        List<Integer> array = new ArrayList<>(Arrays.asList(5, 9, 3, 1, 2, 8, 4, 7, 6));

        // Sort the array
        List<Integer> sortedArray = bubbleSort(array, SortingOrder.ASCENDING).getSortedData();

        // Print the sorted array
        for (Integer integer : sortedArray) {
            System.out.print(integer + " ");
        }

        System.out.println();

        // The array to sort
        array = new ArrayList<>(Arrays.asList(5, 9, 3, 1, 2, 8, 4, 7, 6));

        // Sort the array
        List<Integer> limitedSortedArray = limitedBubbleSort(array, 0, SortingOrder.ASCENDING).getSortedData();

        // Print the limited sorted array
        for (Integer integer : limitedSortedArray) {
            System.out.print(integer + " ");
        }
    }

    public <T extends Comparable<T>> SortedDataResponse<T> bubbleSort(List<T> array, SortingOrder order) {
        // Measure the start time
        long startTime = System.nanoTime();

        // Keep track of whether the array is sorted
        boolean sorted = false;

        // Repeat until the array is sorted
        while (!sorted) {
            // Assume the array is sorted
            sorted = true;

            // Loop through the array
            for (int i = 0; i < array.size() - 1; i++) {
                // If two adjacent elements are not in order, swap them
                // and set the `sorted` flag to `false`
                if (array.get(i).compareTo(array.get(i + 1)) > 0) {
                    T temp = array.get(i);
                    array.set(i, array.get(i + 1));
                    array.set(i + 1, temp);
                    sorted = false;
                }
            }
        }

        // Measure the end time
        long endTime = System.nanoTime();

        // Calculate the elapsed time in nanoseconds
        long elapsedTime = endTime - startTime;

        // Print the elapsed time
        System.out.println("Elapsed time: " + elapsedTime + " nanoseconds");
        executionTime = elapsedTime;
        // Return the sorted array
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

    public <T extends Comparable<T>> SortedDataResponse<T> limitedBubbleSort(List<T> array, int maxIterations, SortingOrder order) {
        // Keep track of whether the array is sorted

        boolean sorted = false;
        // Keep track of the number of iterations
        int iterationCount = 0;

        long startTime = System.nanoTime();
        // Repeat until the array is sorted or the maximum number of iterations has been reached
        while (!sorted && iterationCount < maxIterations) {
            // Assume the array is sorted
            sorted = true;

            // Loop through the array
            for (int i = 0; i < array.size() - 1; i++) {
                // If two adjacent elements are not in order, swap them
                // and set the `sorted` flag to `false`
                if (array.get(i).compareTo(array.get(i + 1)) > 0) {
                    T temp = array.get(i);
                    array.set(i, array.get(i + 1));
                    array.set(i + 1, temp);
                    sorted = false;
                }
            }

            // Increment the iteration count
            iterationCount++;
        }
        long endTime = System.nanoTime();
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

    @Override
    public <T extends Comparable<T>> SortedDataResponse<T> run(List<T> array, SortingOrder order) {
        return bubbleSort(array, order);
    }

    @Override
    public <T extends Comparable<T>> SortedDataResponse<T> run(List<T> array, int maxIterations, SortingOrder order) {
        return limitedBubbleSort(array, maxIterations, order);
    }
}