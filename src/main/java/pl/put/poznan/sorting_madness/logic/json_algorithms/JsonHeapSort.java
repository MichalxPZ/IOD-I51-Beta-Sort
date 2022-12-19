package pl.put.poznan.sorting_madness.logic.json_algorithms;

import com.google.gson.JsonArray;

import com.google.gson.JsonObject;
import pl.put.poznan.sorting_madness.logic.algorithms.SortingOrder;

public class JsonHeapSort implements JsonSortable {

    public long executionTime = 0;

    public JsonArray heapSort(JsonArray array, String attr, SortingOrder order) {
        // Record the starting time of the algorithm
        long startTime = System.nanoTime();

        // Build the heap
        for (int i = array.size() / 2 - 1; i >= 0; i--) {
            heapify(array, attr, array.size(), i);
        }

        // Extract the elements from the heap
        for (int i = array.size() - 1; i >= 0; i--) {
            // Move the root of the heap to the end of the array
            JsonObject temp = (JsonObject) array.get(0);
            array.set(0, array.get(i));
            array.set(i, temp);

            // Rebuild the heap
            heapify(array, attr, i, 0);
        }

        // Record the ending time of the algorithm
        long endTime = System.nanoTime();
        // Print the execution time of the algorithm
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

    public JsonArray limitedHeapSort(JsonArray array, String attr, SortingOrder order, int maxIterations) {
        // Record the starting time of the algorithm
        long startTime = System.nanoTime();

        // Build the heap
        for (int i = array.size() / 2 - 1; i >= 0; i--) {
            heapify(array, attr, array.size(), i);
        }

        // Extract the elements from the heap
        for (int i = array.size() - 1; i >= 0; i--) {
            // Move the root of the heap to the end of the array
            JsonObject temp = (JsonObject) array.get(0);
            array.set(0, array.get(i));
            array.set(i, temp);

            // Rebuild the heap
            heapify(array, attr, i, 0);

            maxIterations--;
            if (maxIterations == 0) {
                break;
            }
        }

        // Record the ending time of the algorithm
        long endTime = System.nanoTime();
        // Print the execution time of the algorithm
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

    public void heapify(JsonArray array, String attr, int size, int i) {
        // Calculate the indices of the left and right children of the current node
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        // Keep track of the largest element in the heap
        int largest = i;

        // If the left child is larger than the current node, update the largest element
        if (left < size &&
                JSONComparator.compare((JsonObject) array.get(left), (JsonObject) array.get(largest), attr) > 0) {
            largest = left;
        }

        // If the right child is larger than the current node or the left child, update the largest element
        if (right < size &&
                JSONComparator.compare((JsonObject) array.get(right), (JsonObject) array.get(largest), attr) > 0) {
            largest = right;
        }

        // If the largest element is not the current node, swap it with the current node
        // and continue heapifying the affected sub-heap
        if (largest != i) {
            JsonObject temp = (JsonObject) array.get(i);
            array.set(i, array.get(largest));
            array.set(largest, temp);
            heapify(array, attr, size, largest);
        }
    }

    @Override
    public SortedJsonDataResponse run(JsonArray array, String attr, SortingOrder order) {
        JsonArray sortedData = heapSort(array, attr, order);
        return new SortedJsonDataResponse(sortedData, executionTime);
    }

    @Override
    public SortedJsonDataResponse run(JsonArray array, String attr, int maxIterations, SortingOrder order) {
        JsonArray sortedData = limitedHeapSort(array, attr, order, maxIterations);
        return new SortedJsonDataResponse(sortedData, executionTime);
    }

}