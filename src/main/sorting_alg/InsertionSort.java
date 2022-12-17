package pl.put.poznan.sorting_alg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class InsertionSort {

    public static void main(String[] args) {
        // The array to sort
        List<Integer> array = new ArrayList<>(Arrays.asList(5, 9, 3, 1, 2, 8, 4, 7, 6));

        // Sort the array
        List<Integer> sortedArray = insertionSort(array, SortingOrder.ASC);

        // Print the sorted array
        for (Integer integer : sortedArray) {
            System.out.print(integer + " ");
        }

        System.out.println();

        // The array to sort
        array = new ArrayList<>(Arrays.asList(5, 9, 3, 1, 2, 8, 4, 7, 6));

        // Sort the array
        List<Integer> limitedSortedArray = limitedInsertionSort(array, 4, SortingOrder.ASC);

        // Print the limited sorted array
        for (Integer integer : limitedSortedArray) {
            System.out.print(integer + " ");
        }
    }

    public static <T extends Comparable<T>> List<T> insertionSort(List<T> array, SortingOrder order) {
        // Record the starting time of the algorithm
        long startTime = System.nanoTime();

        // Loop through the array
        for (int i = 1; i < array.size(); i++) {
            // Save the current element
            T current = array.get(i);

            // Shift all elements that are greater than the current element
            // to the right, until we find an element that is less than or
            // equal to the current element
            int j = i - 1;
            while (j >= 0 && array.get(j).compareTo(current) > 0) {
                array.set(j + 1, array.get(j));
                j--;
            }

            // Insert the current element in the correct position
            array.set(j + 1, current);
        }

        // Record the ending time of the algorithm
        long endTime = System.nanoTime();

        // Print the execution time of the algorithm
        System.out.println("Total execution time: " + (endTime - startTime) + " nanoseconds");

        // Return the sorted array
        if (SortingOrder.ASC.equals(order)) {
            return array;
        } else {
            final List<T> result = new ArrayList<>(array);
            Collections.reverse(result);
            return result;
        }
    }

    public static <T extends Comparable<T>> List<T> limitedInsertionSort(List<T> array, int maxIterations, SortingOrder order) {
        // Loop through the array
        for (int i = 1; i < array.size() && i <= maxIterations; i++) {
            // Save the current element
            T current = array.get(i);

            // Shift all elements that are greater than the current element
            // to the right, until we find an element that is less than or
            // equal to the current element
            int j = i - 1;
            while (j >= 0 && array.get(j).compareTo(current) > 0) {
                array.set(j + 1, array.get(j));
                j--;
            }

            // Insert the current element in the correct position
            array.set(j + 1, current);
        }

        // Return the sorted array
        if (SortingOrder.ASC.equals(order)) {
            return array;
        } else {
            final List<T> result = new ArrayList<>(array);
            Collections.reverse(result);
            return result;
        }
    }
}