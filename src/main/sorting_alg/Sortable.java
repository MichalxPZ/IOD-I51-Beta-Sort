package pl.put.poznan.sorting_alg;

import java.util.List;

public interface Sortable {
    <T extends Comparable<T>> List<T> run(List<T> array, SortingOrder order);

    <T extends Comparable<T>> List<T> run(List<T> array, int maxIterations, SortingOrder order);
}
