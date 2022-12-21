package pl.put.poznan.sorting_madness.logic.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class QuickSort implements Sortable {

    public long executionTime = 0;

    public void main(String[] args) {
        List<Integer> array = new ArrayList<>(Arrays.asList(5, 9, 3, 1, 2, 8, 4, 7, 6));

        List<Integer> sortedArray = quickSort(array, SortingOrder.ASCENDING).getSortedData();

        for (Integer integer : sortedArray) {
            System.out.print(integer + " ");
        }

        array = new ArrayList<>(Arrays.asList(5, 9, 3, 1, 2, 8, 4, 7, 6));

        List<Integer> limitedSortedArray = limitedQuickSort(array, 1, SortingOrder.ASCENDING).getSortedData();

        for (Integer integer : limitedSortedArray) {
            System.out.print(integer + " ");
        }
    }

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

    @Override
    public <T extends Comparable<T>> SortedDataResponse<T> run(List<T> array, SortingOrder order) {
        return quickSort(array, order);
    }

    @Override
    public <T extends Comparable<T>> SortedDataResponse<T> run(List<T> array, int maxIterations, SortingOrder order) {
        return limitedQuickSort(array, maxIterations, order);
    }
}