package pl.put.poznan.sorting_madness.logic.algorithms;

import java.util.*;

public class MergeSort implements Sortable {

    public long executionTime = 0;

    public void main(String[] args) {

        List<Integer> array = new ArrayList<>(Arrays.asList(5, 9, 3, 1, 2, 8, 4, 7, 6));

        List<Integer> sortedArray = mergeSortTime(array, SortingOrder.ASCENDING).getSortedData();

        for (Integer integer : sortedArray) {
            System.out.print(integer + " ");
        }

        array = new ArrayList<>(Arrays.asList(5, 9, 3, 1, 2, 8, 4, 7, 6));

        List<Integer> limitedSortedArray = limitedMergeSort(array, 2, SortingOrder.ASCENDING).getSortedData();

        for (Integer integer : limitedSortedArray) {
            System.out.print(integer + " ");
        }
    }

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

    @Override
    public <T extends Comparable<T>> SortedDataResponse<T> run(List<T> array, SortingOrder order) {
        return mergeSortTime(array, order);
    }

    @Override
    public <T extends Comparable<T>> SortedDataResponse<T> run(List<T> array, int maxIterations, SortingOrder order) {
        return limitedMergeSort(array, maxIterations, order);
    }
}