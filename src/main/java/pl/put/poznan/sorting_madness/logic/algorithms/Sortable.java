package pl.put.poznan.sorting_madness.logic.algorithms;


import java.util.List;

public interface Sortable {
    <T extends Comparable<T>> SortedDataResponse<T> run(List<T> array, SortingOrder order);

    <T extends Comparable<T>> SortedDataResponse<T> run(List<T> array, int maxIterations, SortingOrder order);
}
