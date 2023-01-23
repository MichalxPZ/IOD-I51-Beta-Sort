package pl.put.poznan.sorting_madness.logic.json_algorithms;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import pl.put.poznan.sorting_madness.logic.algorithms.Sortable;
import pl.put.poznan.sorting_madness.logic.algorithms.SortedDataResponse;
import pl.put.poznan.sorting_madness.logic.algorithms.SortingOrder;

/**
 * The InsertionSort class implements the {@link JsonSortable} interface and provides a method for sorting
 * a list of elements using the Insertion Sort algorithm.
 * The Insertion Sort algorithm works by iterating through the elements of the list and inserting
 * each element in its correct position in the sorted sub-list. The algorithm starts with the
 * second element in the list and compares it with the first element. If the second element is
 * smaller, it is swapped with the first element. The algorithm then moves on to the third element
 * and compares it with the first two elements. If the third element is smaller, it is swapped
 * with the second element. This process is repeated until the element is in its correct position
 * in the sorted sub-list. The algorithm then moves on to the next element and repeats the process
 * until all elements have been sorted.
 */
public class JsonInsertionSort implements JsonSortable {

    public long executionTime = 0;

    /**
     * Sorts the specified list of elements in ascending order using the Insertion Sort algorithm.
     *
     * @param array the list of elements to be sorted
     * @param order the order in which the elements should be sorted (ascending or descending)
     * @return a {@link SortedJsonDataResponse} object containing the sorted list and the time taken to sort it
     */
    public JsonArray insertionSort(JsonArray array, String attr, SortingOrder order) {

        long startTime = System.nanoTime();

        for (int i = 1; i < array.size(); i++) {
            // Save the current element
            JsonObject current = (JsonObject) array.get(i);


            int j = i - 1;
            while (j >= 0 &&
                    JSONComparator.compare((JsonObject) array.get(j), current, attr) > 0) {
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
            return array;
        } else {
            JsonArray toReturn = new JsonArray();
            for (int i = array.size() - 1; i >= 0; i--) {
                toReturn.add(array.get(i));
            }
            return toReturn;
        }
    }

    /**
     * Sorts the specified list of elements in ascending order using the Insertion Sort algorithm,
     * up to a maximum number of iterations.
     *
     * @param array the list of elements to be sorted
     * @param maxIterations the maximum number of iterations to perform
     * @param order the order in which the elements should be sorted (ascending or descending)
     * @return a {@link SortedJsonDataResponse} object containing the sorted list and the time taken to sort it
     */
    public JsonArray limitedInsertionSort(JsonArray array, String attr, SortingOrder order, int maxIterations) {
        long startTime = System.nanoTime();

        for (int i = 1; i < array.size() && i <= maxIterations; i++) {

            JsonObject current = (JsonObject) array.get(i);

            int j = i - 1;
            while (j >= 0 &&
                    JSONComparator.compare((JsonObject) array.get(j), current, attr) > 0) {
                array.set(j + 1, array.get(j));
                j--;
            }

            array.set(j + 1, current);
        }
        long endTime = System.nanoTime();
        executionTime = endTime - startTime;

        if (SortingOrder.ASCENDING.equals(order)) {
            return array;
        } else {
            JsonArray toReturn = new JsonArray();
            for (int i = array.size() - 1; i >= 0; i--) {
                toReturn.add(array.get(i));
            }
            return toReturn;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SortedJsonDataResponse run(JsonArray array, String attr, SortingOrder order) {
        JsonArray sortedData = insertionSort(array, attr, order);
        return new SortedJsonDataResponse(sortedData, executionTime);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SortedJsonDataResponse run(JsonArray array, String attr, int maxIterations, SortingOrder order) {
        JsonArray sortedData = limitedInsertionSort(array, attr, order, maxIterations);
        return new SortedJsonDataResponse(sortedData, executionTime);
    }

}