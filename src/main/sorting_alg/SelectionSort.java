package pl.put.poznan.sorting_alg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SelectionSort implements Sortable {

    public static void main(String[] args) {
        // The array to sort
        List<Integer> array = new ArrayList<>(Arrays.asList(5, 9, 3, 1, 2, 8, 4, 7, 6));

        // Sort the array
        List<Integer> sortedArray = selectionSort(array, SortingOrder.ASCENDING);

        // Print the sorted array
        for (Integer integer : sortedArray) {
            System.out.print(integer + " ");
        }

        System.out.println();

        // The array to sort
        array = new ArrayList<>(Arrays.asList(5, 9, 3, 1, 2, 8, 4, 7, 6));

        // Sort the array
        List<Integer> limitedSortedArray = limitedSelectionSort(array, 3, SortingOrder.ASCENDING);

        // Print the sorted array
        for (Integer integer : limitedSortedArray) {
            System.out.print(integer + " ");
        }
    }

    public static <T extends Comparable<T>> List<T> selectionSort(List<T> array, SortingOrder order) {
        // Record the starting time of the algorithm
        long startTime = System.nanoTime();
        // Loop through the array
        for (int i = 0; i < array.size() - 1; i++) {
            // Find the minimum element in the unsorted part of the array
            int minIndex = i;
            for (int j = i + 1; j < array.size(); j++) {
                if (array.get(j).compareTo(array.get(minIndex)) < 0) {
                    minIndex = j;
                }
            }

            // Swap the minimum element with the first element in the unsorted part of the array
            T temp = array.get(i);
            array.set(i, array.get(minIndex));
            array.set(minIndex, temp);
        }
        // Record the ending time of the algorithm
        long endTime = System.nanoTime();
        // Print the execution time of the algorithm
        System.out.println("Total execution time: " + (endTime - startTime) + " nanoseconds");

        // Return the sorted array
        if (SortingOrder.ASCENDING.equals(order)) {
            return array;
        } else {
            final List<T> result = new ArrayList<>(array);
            Collections.reverse(result);
            return result;
        }
    }

    public static <T extends Comparable<T>> List<T> limitedSelectionSort(List<T> array, int maxIterations, SortingOrder order) {
        // Loop through the array
        for (int i = 0; i < array.size() - 1 && i <= maxIterations; i++) {
            // Find the minimum element in the unsorted part of the array
            int minIndex = i;
            for (int j = i + 1; j < array.size(); j++) {
                if (array.get(j).compareTo(array.get(minIndex)) < 0) {
                    minIndex = j;
                }
            }

            // Swap the minimum element with the first element in the unsorted part of the array
            T temp = array.get(i);
            array.set(i, array.get(minIndex));
            array.set(minIndex, temp);
        }

        // Return the sorted array
        if (SortingOrder.ASCENDING.equals(order)) {
            return array;
        } else {
            final List<T> result = new ArrayList<>(array);
            Collections.reverse(result);
            return result;
        }
    }

    @Override
    public <T extends Comparable<T>> List<T> run(List<T> array, SortingOrder order) {
        return selectionSort(array, order);
    }

    @Override
    public <T extends Comparable<T>> List<T> run(List<T> array, int maxIterations, SortingOrder order) {
        return limitedSelectionSort(array, maxIterations, order);
    }
}