package pl.put.poznan.sorting_alg;

import java.util.*;

public class MergeSort implements Sortable {
    public static void main(String[] args) {
        // The array to sort
        List<Integer> array = new ArrayList<>(Arrays.asList(5, 9, 3, 1, 2, 8, 4, 7, 6));

        // Sort the array
        List<Integer> sortedArray = mergeSortTime(array, SortingOrder.ASCENDING);

        // Print the sorted array
        for (Integer integer : sortedArray) {
            System.out.print(integer + " ");
        }

        System.out.println();

        // The array to sort
        array = new ArrayList<>(Arrays.asList(5, 9, 3, 1, 2, 8, 4, 7, 6));

        // Sort the array
        List<Integer> limitedSortedArray = limitedMergeSort(array, 2, SortingOrder.ASCENDING);

        // Print the sorted array
        for (Integer integer : limitedSortedArray) {
            System.out.print(integer + " ");
        }
    }

    public static <T extends Comparable<T>> List<T> mergeSortTime(List<T> array, SortingOrder order) {
        // Record the starting time of the algorithm
        long startTime = System.nanoTime();

        List<T> sortedArray = mergeSort(array, order);

        // Record the ending time of the algorithm
        long endTime = System.nanoTime();

        // Print the execution time of the algorithm
        System.out.println("Total execution time: " + (endTime - startTime) + " nanoseconds");

        if (SortingOrder.ASCENDING.equals(order)) {
            return sortedArray;
        } else {
            final List<T> result = new ArrayList<>(sortedArray);
            Collections.reverse(result);
            return result;
        }
    }

    public static <T extends Comparable<T>> List<T> mergeSort(List<T> array, SortingOrder order) {
        // If the array has length 1 or 0, it is already sorted
        if (array.size() <= 1) {
            return array;
        }

        // Split the array into two halves
        int middle = array.size() / 2;
        List<T> left = new ArrayList<T>((Collection<? extends T>) Arrays.asList(Arrays.copyOfRange(array.toArray(new Integer[0]), 0, middle)));
        List<T> right = new ArrayList<T>((Collection<? extends T>) Arrays.asList(Arrays.copyOfRange(array.toArray(new Integer[0]), middle, array.size())));

        // Sort the left and right halves
        List<T> newLeft = mergeSort(left, order);
        List<T> newRight = mergeSort(right, order);

        // Merge the sorted left and right halves into the original array
        merge(newLeft, newRight, array);

        if (SortingOrder.ASCENDING.equals(order)) {
            return array;
        } else {
            final List<T> result = new ArrayList<>(array);
            Collections.reverse(result);
            return result;
        }
    }

    public static <T extends Comparable<T>> List<T> limitedMergeSort(List<T> array, int maxIteration, SortingOrder order) {
        // If the array has length 1 or 0, it is already sorted
        if (array.size() <= 1 || maxIteration == 0) {
            return array;
        }

        // Split the array into two halves
        int middle = array.size() / 2;
        List<T> left = new ArrayList<T>((Collection<? extends T>) Arrays.asList(Arrays.copyOfRange(array.toArray(new Integer[0]), 0, middle)));
        List<T> right = new ArrayList<T>((Collection<? extends T>) Arrays.asList(Arrays.copyOfRange(array.toArray(new Integer[0]), middle, array.size())));

        // Sort the left and right halves
        List<T> newLeft = limitedMergeSort(left, maxIteration - 1, order);
        List<T> newRight = limitedMergeSort(right, maxIteration - 1, order);

        // Merge the sorted left and right halves into the original array
        merge(newLeft, newRight, array);

        if (SortingOrder.ASCENDING.equals(order)) {
            return array;
        } else {
            final List<T> result = new ArrayList<>(array);
            Collections.reverse(result);
            return result;
        }
    }

    public static <T extends Comparable<T>> void merge(List<T> left, List<T> right, List<T> array) {
        // Keep track of the current index in the left, right, and original arrays
        int i = 0, j = 0, k = 0;

        // Loop until one of the subarrays is exhausted
        while (i < left.size() && j < right.size()) {
            // If the current element in the left subarray is less than
            // the current element in the right subarray, add it to
            // the original array and move to the next element in the left subarray
            if (left.get(i).compareTo(right.get(j)) <= 0) {
                array.set(k, left.get(i));
                i++;
            } else {
                // Otherwise, add the current element in the right subarray
                // to the original array and move to the next element in the right subarray
                array.set(k, right.get(j));
                j++;
            }
            k++;
        }

        // Add the remaining elements in the left subarray (if any) to the original array
        while (i < left.size()) {
            array.set(k, left.get(i));
            i++;
            k++;
        }

        // Add the remaining elements in the right subarray (if any) to the original array
        while (j < right.size()) {
            array.set(k, right.get(j));
            j++;
            k++;
        }
    }

    @Override
    public <T extends Comparable<T>> List<T> run(List<T> array, SortingOrder order) {
        return mergeSortTime(array, order);
    }

    @Override
    public <T extends Comparable<T>> List<T> run(List<T> array, int maxIterations, SortingOrder order) {
        return limitedMergeSort(array, maxIterations, order);
    }
}