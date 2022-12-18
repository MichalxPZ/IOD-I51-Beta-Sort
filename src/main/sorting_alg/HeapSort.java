package pl.put.poznan.sorting_alg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HeapSort implements Sortable {
    public static void main(String[] args) {
        // The array to sort
        List<Integer> array = new ArrayList<>(Arrays.asList(5, 9, 3, 1, 2, 8, 4, 7, 6));

        // Sort the array
        List<Integer> sortedArray = heapSort(array, SortingOrder.ASCENDING);

        // Print the sorted array
        for (Integer integer : sortedArray) {
            System.out.print(integer + " ");
        }

        System.out.println();

        // The array to sort
        array = new ArrayList<>(Arrays.asList(5, 9, 3, 1, 2, 8, 4, 7, 6));

        // Sort the array
        List<Integer> limitedSortedArray = limitedHeapSort(array, 3, SortingOrder.ASCENDING);

        // Print the limited sorted array
        for (Integer integer : limitedSortedArray) {
            System.out.print(integer + " ");
        }
    }

    public static <T extends Comparable<T>> List<T> heapSort(List<T> array, SortingOrder order) {
        // Record the starting time of the algorithm
        long startTime = System.nanoTime();

        // Build the heap
        for (int i = array.size() / 2 - 1; i >= 0; i--) {
            heapify(array, array.size(), i);
        }

        // Extract the elements from the heap
        for (int i = array.size() - 1; i >= 0; i--) {
            // Move the root of the heap to the end of the array
            T temp = array.get(0);
            array.set(0, array.get(i));
            array.set(i, temp);

            // Rebuild the heap
            heapify(array, i, 0);
        }

        // Record the ending time of the algorithm
        long endTime = System.nanoTime();
        // Print the execution time of the algorithm
        System.out.println("Total execution time: " + (endTime - startTime) + " nanoseconds");

        if (SortingOrder.ASCENDING.equals(order)) {
            return array;
        } else {
            final List<T> result = new ArrayList<>(array);
            Collections.reverse(result);
            return result;
        }
    }

    public static <T extends Comparable<T>> List<T> limitedHeapSort(List<T> array, int maxIterations, SortingOrder order) {
        // Record the starting time of the algorithm
        long startTime = System.nanoTime();

        // Build the heap
        for (int i = array.size() / 2 - 1; i >= 0; i--) {
            heapify(array, array.size(), i);
        }

        // Extract the elements from the heap
        for (int i = array.size() - 1; i >= 0; i--) {
            // Move the root of the heap to the end of the array
            T temp = array.get(0);
            array.set(0, array.get(i));
            array.set(i, temp);

            // Rebuild the heap
            heapify(array, i, 0);

            maxIterations--;
            if (maxIterations == 0) {
                break;
            }
        }

        // Record the ending time of the algorithm
        long endTime = System.nanoTime();
        // Print the execution time of the algorithm
        System.out.println("Total execution time: " + (endTime - startTime) + " nanoseconds");

        if (SortingOrder.ASCENDING.equals(order)) {
            return array;
        } else {
            final List<T> result = new ArrayList<>(array);
            Collections.reverse(result);
            return result;
        }
    }

    public static <T extends Comparable<T>> void heapify(List<T> array, int size, int i) {
        // Calculate the indices of the left and right children of the current node
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        // Keep track of the largest element in the heap
        int largest = i;

        // If the left child is larger than the current node, update the largest element
        if (left < size && array.get(left).compareTo(array.get(largest)) > 0) {
            largest = left;
        }

        // If the right child is larger than the current node or the left child, update the largest element
        if (right < size && array.get(right).compareTo(array.get(largest)) > 0) {
            largest = right;
        }

        // If the largest element is not the current node, swap it with the current node
        // and continue heapifying the affected sub-heap
        if (largest != i) {
            T temp = array.get(i);
            array.set(i, array.get(largest));
            array.set(largest, temp);
            heapify(array, size, largest);
        }
    }

    @Override
    public <T extends Comparable<T>> List<T> run(List<T> array, SortingOrder order) {
        return heapSort(array, order);
    }

    @Override
    public <T extends Comparable<T>> List<T> run(List<T> array, int maxIterations, SortingOrder order) {
        return limitedHeapSort(array, maxIterations, order);
    }
}