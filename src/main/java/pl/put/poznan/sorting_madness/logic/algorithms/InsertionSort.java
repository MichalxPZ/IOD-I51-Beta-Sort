package pl.put.poznan.sorting_madness.logic.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class InsertionSort implements Sortable {

    public long executionTime = 0;

    public void main(String[] args) {

        List<Integer> array = new ArrayList<>(Arrays.asList(5, 9, 3, 1, 2, 8, 4, 7, 6));

        List<Integer> sortedArray = insertionSort(array, SortingOrder.ASCENDING).getSortedData();

        for (Integer integer : sortedArray) {
            System.out.print(integer + " ");
        }

        array = new ArrayList<>(Arrays.asList(5, 9, 3, 1, 2, 8, 4, 7, 6));

        List<Integer> limitedSortedArray = limitedInsertionSort(array, 4, SortingOrder.ASCENDING).getSortedData();

        for (Integer integer : limitedSortedArray) {
            System.out.print(integer + " ");
        }
    }

    public <T extends Comparable<T>> SortedDataResponse<T> insertionSort(List<T> array, SortingOrder order) {

        long startTime = System.nanoTime();

        for (int i = 1; i < array.size(); i++) {

            T current = array.get(i);

            int j = i - 1;
            while (j >= 0 && array.get(j).compareTo(current) > 0) {
                array.set(j + 1, array.get(j));
                j--;
            }

            array.set(j + 1, current);
        }

        long endTime = System.nanoTime();

        System.out.println("Total execution time: " + (endTime - startTime) + " nanoseconds");
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

    public <T extends Comparable<T>> SortedDataResponse<T> limitedInsertionSort(List<T> array, int maxIterations, SortingOrder order) {
        long startTime = System.nanoTime();

        for (int i = 1; i < array.size() && i <= maxIterations; i++) {

            T current = array.get(i);

            int j = i - 1;
            while (j >= 0 && array.get(j).compareTo(current) > 0) {
                array.set(j + 1, array.get(j));
                j--;
            }

            array.set(j + 1, current);
        }

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

    @Override
    public <T extends Comparable<T>> SortedDataResponse<T> run(List<T> array, SortingOrder order) {
        return insertionSort(array, order);
    }

    @Override
    public <T extends Comparable<T>> SortedDataResponse<T> run(List<T> array, int maxIterations, SortingOrder order) {
        return limitedInsertionSort(array, maxIterations, order);
    }
}