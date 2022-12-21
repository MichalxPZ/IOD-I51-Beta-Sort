package pl.put.poznan.sorting_madness.logic.algorithms;


import java.util.List;

/**
 * An interface for sorting algorithms.
 */
public interface Sortable {

    /**
     * Sorts the given list of elements in ascending or descending order, using the implemented sorting algorithm.
     *
     * @param array the list of elements to be sorted
     * @param order the desired order for the sorted list (ascending or descending)
     * @return a {@link SortedDataResponse} object containing the sorted list of elements and the execution time of the algorithm
     */
    <T extends Comparable<T>> SortedDataResponse<T> run(List<T> array, SortingOrder order);

    /**
     * Sorts the given list of elements in ascending or descending order, using the implemented sorting algorithm.
     * The algorithm will only iterate through the list a maximum number of times, as specified by the `maxIterations` parameter.
     *
     * @param array the list of elements to be sorted
     * @param maxIterations the maximum number of iterations the algorithm should perform
     * @param order the desired order for the sorted list (ascending or descending)
     * @return a {@link SortedDataResponse} object containing the sorted list of elements and the execution time of the algorithm
     */
    <T extends Comparable<T>> SortedDataResponse<T> run(List<T> array, int maxIterations, SortingOrder order);
}
