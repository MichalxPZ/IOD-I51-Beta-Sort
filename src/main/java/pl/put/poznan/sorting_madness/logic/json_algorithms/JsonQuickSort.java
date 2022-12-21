package pl.put.poznan.sorting_madness.logic.json_algorithms;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import pl.put.poznan.sorting_madness.logic.algorithms.SortingOrder;

public class JsonQuickSort implements JsonSortable {

    public long executionTime = 0;

    public JsonArray quickSort(JsonArray array, String attr, SortingOrder order) {
        // Record the starting time of the algorithm
        long startTime = System.nanoTime();

        quick(array, 0, array.size() - 1, attr);
        // Record the ending time of the algorithm
        long endTime = System.nanoTime();
        // Print the execution time of the algorithm
        System.out.println("Total execution time: " + (endTime - startTime) + " nanoseconds");

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

    public JsonArray quick(JsonArray array, int start, int end, String attr) {

        long startTime = System.nanoTime();

        // If the array has length 1 or 0, it is already sorted
        if (end - start < 1) {
            return array;
        }

        // Choose a pivot element
        JsonObject pivot = (JsonObject) array.get(end);

        // Partition the array around the pivot element
        int partition = partition(array, start, end, pivot, attr);

        // Sort the left and right partitions
        quick(array, start, partition - 1, attr);
        quick(array, partition + 1, end, attr);

        long endTime = System.nanoTime();
        executionTime = endTime - startTime;
        return array;
    }

    public JsonArray limitedQuick(JsonArray array, int start, int end, String attr, int maxIterations) {
        // If the array has length 1 or 0, it is already sorted
        if (end - start < 1) {
            return array;
        }

        // If we have reached the maximum number of iterations, return
        if (maxIterations == 0) {
            return array;
        }

        // Choose a pivot element
        JsonObject pivot = (JsonObject) array.get(end);

        // Partition the array around the pivot element
        int partition = partition(array, start, end, pivot, attr);

        // Decrement the number of iterations
        maxIterations--;

        // Sort the left and right partitions
        limitedQuick(array, start, partition - 1, attr, maxIterations);
        limitedQuick(array, partition + 1, end, attr, maxIterations);

        return array;
    }

    public JsonArray limitedQuickSort(JsonArray array, String attr, SortingOrder order, int maxIteration) {
        long startTime = System.nanoTime();
        array = limitedQuick(array, 0, array.size() - 1, attr, maxIteration);
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

    public int partition(JsonArray array, int start, int end, JsonObject pivot, String attr) {
        // Keep track of the current position in the array
        int current = start;

        // Loop through the array, from the start to the end
        for (int i = start; i < end; i++) {
            // If the current element is less than the pivot element,
            // swap it with the element at the current position and
            // move the current position to the right
            if (JSONComparator.compare((JsonObject) array.get(i), (JsonObject) pivot, attr) < 0) {
                JsonObject temp = (JsonObject) array.get(i);
                array.set(i, array.get(current));
                array.set(current, temp);
                current++;
            }
        }

        // Swap the pivot element with the element at the current position
        array.set(end, array.get(current));
        array.set(current, pivot);

        // Return the current position
        return current;
    }

    @Override
    public SortedJsonDataResponse run(JsonArray array, String attr, SortingOrder order) {
        JsonArray sortedData = quickSort(array, attr, order);
        return new SortedJsonDataResponse(sortedData, executionTime);
    }

    @Override
    public SortedJsonDataResponse run(JsonArray array, String attr, int maxIterations, SortingOrder order) {
        JsonArray sortedData = limitedQuickSort(array, attr, order, maxIterations);
        return new SortedJsonDataResponse(sortedData, executionTime);
    }

}