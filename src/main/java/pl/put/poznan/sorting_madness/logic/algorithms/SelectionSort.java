package pl.put.poznan.sorting_madness.logic.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
/**
 * This class contains methods to perform the Sort by Selection algorithm on data of primitive type
 */
public class SelectionSort implements Sortable {

    /**
     * variable indicating the execution time of the algorithm in [ns]
     */
    public long executionTime = 0;

    public void main(String[] args) {

        List<Integer> array = new ArrayList<>(Arrays.asList(5, 9, 3, 1, 2, 8, 4, 7, 6));

        List<Integer> sortedArray = selectionSort(array, SortingOrder.ASCENDING).getSortedData();

        for (Integer integer : sortedArray) {
            System.out.print(integer + " ");
        }

        array = new ArrayList<>(Arrays.asList(5, 9, 3, 1, 2, 8, 4, 7, 6));

        List<Integer> limitedSortedArray = limitedSelectionSort(array, 3, SortingOrder.ASCENDING).getSortedData();

        for (Integer integer : limitedSortedArray) {
            System.out.print(integer + " ");
        }
    }

    /**
     * This method performs Sort by Selection algorithm on data of primitive types
     * @param array array of data of primitive type such as String, Int or Float
     * @param order soring order - ascending or descending
     * @param <T> type od data eg String
     * @return object of SortedDataResponse containing sorted data and algorithm execution time
     */
    public <T extends Comparable<T>> SortedDataResponse<T> selectionSort(List<T> array, SortingOrder order) {

        long startTime = System.nanoTime();

        for (int i = 0; i < array.size() - 1; i++) {

            int minIndex = i;
            for (int j = i + 1; j < array.size(); j++) {
                if (array.get(j).compareTo(array.get(minIndex)) < 0) {
                    minIndex = j;
                }
            }

            T temp = array.get(i);
            array.set(i, array.get(minIndex));
            array.set(minIndex, temp);
        }

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

    /**
     * This method performs Sort by Selection algorithm on data of primitive types with indicated maximum number of iterations
     * @param array array of data of primitive type such as String, Int or Float
     * @param maxIterations maximum number of iterations of algorithm execution
     * @param order soring order - ascending or descending
     * @param <T> type od data eg String
     * @return object of SortedDataResponse containing sorted data and algorithm execution time
     */
    public <T extends Comparable<T>> SortedDataResponse<T> limitedSelectionSort(List<T> array, int maxIterations, SortingOrder order) {
        long startTime = System.nanoTime();

        for (int i = 0; i < array.size() - 1 && i <= maxIterations; i++) {

            int minIndex = i;
            for (int j = i + 1; j < array.size(); j++) {
                if (array.get(j).compareTo(array.get(minIndex)) < 0) {
                    minIndex = j;
                }
            }

            T temp = array.get(i);
            array.set(i, array.get(minIndex));
            array.set(minIndex, temp);
        }

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

    /**
     * M Method overridden by the Sortable interface that performs run operation
     * of Sort by Selection algorithm on data of primitive types
     * @param array array of data of primitive type such as String, Int or Float
     * @param order soring order - ascending or descending
     * @param <T> type od data eg String
     * @return object of SortedDataResponse containing sorted data and algorithm execution time
     */
    @Override
    public <T extends Comparable<T>> SortedDataResponse<T> run(List<T> array, SortingOrder order) {
        return selectionSort(array, order);
    }

    /**
     * Method overridden by the Sortable interface that performs run operation
     * of Sort by Selection algorithm on data of primitive types with indicated maximum number of iterations
     * @param array array of data of primitive type such as String, Int or Float
     * @param maxIterations maximum number of iterations of algorithm execution
     * @param order soring order - ascending or descending
     * @param <T> type od data eg String
     * @return object of SortedDataResponse containing sorted data and algorithm execution time
     */
    @Override
    public <T extends Comparable<T>> SortedDataResponse<T> run(List<T> array, int maxIterations, SortingOrder order) {
        return limitedSelectionSort(array, maxIterations, order);
    }
}