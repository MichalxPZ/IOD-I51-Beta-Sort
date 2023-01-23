package pl.put.poznan.sorting_madness.logic.json_algorithms;

import com.google.gson.JsonArray;

import com.google.gson.JsonObject;
import pl.put.poznan.sorting_madness.logic.algorithms.Sortable;
import pl.put.poznan.sorting_madness.logic.algorithms.SortedDataResponse;
import pl.put.poznan.sorting_madness.logic.algorithms.SortingOrder;

/**
 * A class that implements the {@link JsonSortable} interface and provides heap sort algorithm.
 * The heap sort algorithm creates a binary heap from the given list of elements, and then removes the largest element from the heap
 * and places it at the end of the list until the list is sorted.
 */
public class JsonHeapSort implements JsonSortable {

    public long executionTime = 0;

    /**
     * Sorts the given list of elements in ascending or descending order, using the heap sort algorithm.
     *
     * @param array the list of elements to be sorted
     * @param order the desired order for the sorted list (ascending or descending)
     * @return a {@link SortedJsonDataResponse} object containing the sorted list of elements and the execution time of the algorithm
     */
    public JsonArray heapSort(JsonArray array, String attr, SortingOrder order) {

        long startTime = System.nanoTime();


        for (int i = array.size() / 2 - 1; i >= 0; i--) {
            heapify(array, attr, array.size(), i);
        }


        for (int i = array.size() - 1; i >= 0; i--) {

            JsonObject temp = (JsonObject) array.get(0);
            array.set(0, array.get(i));
            array.set(i, temp);


            heapify(array, attr, i, 0);
        }


        long endTime = System.nanoTime();

        System.out.println("Total execution time: " + (endTime - startTime) + " nanoseconds");
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
     * Sorts the given list of elements in ascending or descending order, using the heap sort algorithm.
     * The algorithm will only iterate through the list a maximum number of times, as specified by the `maxIterations` parameter.
     *
     * @param array the list of elements to be sorted
     * @param maxIterations the maximum number of iterations the algorithm should perform
     * @param order the desired order for the sorted list (ascending or descending)
     * @return a {@link SortedJsonDataResponse} object containing the sorted list of elements and the execution time of the algorithm
     */
    public JsonArray limitedHeapSort(JsonArray array, String attr, SortingOrder order, int maxIterations) {

        long startTime = System.nanoTime();


        for (int i = array.size() / 2 - 1; i >= 0; i--) {
            heapify(array, attr, array.size(), i);
        }

        for (int i = array.size() - 1; i >= 0; i--) {

            JsonObject temp = (JsonObject) array.get(0);
            array.set(0, array.get(i));
            array.set(i, temp);

            heapify(array, attr, i, 0);

            maxIterations--;
            if (maxIterations == 0) {
                break;
            }
        }


        long endTime = System.nanoTime();

        System.out.println("Total execution time: " + (endTime - startTime) + " nanoseconds");
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
     * Helper method for the heap sort algorithm. Rebuilds the binary heap with the given root node.
     *
     * @param array the list of elements being sorted
     * @param size the size of the heap
     * @param i the index of the root node
     */
    public void heapify(JsonArray array, String attr, int size, int i) {

        int left = 2 * i + 1;
        int right = 2 * i + 2;


        int largest = i;

        if (left < size &&
                JSONComparator.compare((JsonObject) array.get(left), (JsonObject) array.get(largest), attr) > 0) {
            largest = left;
        }

        if (right < size &&
                JSONComparator.compare((JsonObject) array.get(right), (JsonObject) array.get(largest), attr) > 0) {
            largest = right;
        }

        if (largest != i) {
            JsonObject temp = (JsonObject) array.get(i);
            array.set(i, array.get(largest));
            array.set(largest, temp);
            heapify(array, attr, size, largest);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SortedJsonDataResponse run(JsonArray array, String attr, SortingOrder order) {
        JsonArray sortedData = heapSort(array, attr, order);
        return new SortedJsonDataResponse(sortedData, executionTime);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SortedJsonDataResponse run(JsonArray array, String attr, int maxIterations, SortingOrder order) {
        JsonArray sortedData = limitedHeapSort(array, attr, order, maxIterations);
        return new SortedJsonDataResponse(sortedData, executionTime);
    }

}