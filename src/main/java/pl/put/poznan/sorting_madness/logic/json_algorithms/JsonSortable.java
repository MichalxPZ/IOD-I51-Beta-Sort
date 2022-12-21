package pl.put.poznan.sorting_madness.logic.json_algorithms;


import com.google.gson.JsonArray;
import pl.put.poznan.sorting_madness.logic.algorithms.SortingOrder;

/**
 * This interface represents a class that can sort a given JSON array using a specified attribute and order.
 */public interface JsonSortable {
  /**
      * Sorts the given JSON array using the specified attribute and order.
      * @param array the JSON array to be sorted
      * @param attr the attribute to be used for sorting
      * @param order the desired order of the sorted array (ascending or descending)
      * @return a {@link SortedJsonDataResponse} object containing the sorted array and the execution time (in nanoseconds)
      */     SortedJsonDataResponse run(JsonArray array, String attr, SortingOrder order);

    /**
      * Performs a limited sort on the given JSON array using the specified attribute and order.
      * Only the first {@code maxIterations} elements of the array are sorted.
      * @param array the JSON array to be sorted
      * @param attr the attribute to be used for sorting
      * @param maxIterations the maximum number of iterations to perform during the sort
      * @param order the desired order of the sorted array (ascending or descending)
      * @return a {@link SortedJsonDataResponse} object containing the sorted array and the execution time (in nanoseconds)
      */     SortedJsonDataResponse run(JsonArray array, String attr, int maxIterations, SortingOrder order);
}
