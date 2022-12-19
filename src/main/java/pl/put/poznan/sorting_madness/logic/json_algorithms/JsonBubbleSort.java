package pl.put.poznan.sorting_madness.logic.json_algorithms;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import pl.put.poznan.sorting_madness.logic.algorithms.SortingOrder;

public class JsonBubbleSort implements JsonSortable {

    public long executionTime;

    public JsonArray bubbleSort(JsonArray array, String attr, SortingOrder order) {
        executionTime = 0;
        // Measure the start time
        long startTime = System.nanoTime();

        // Keep track of whether the array is sorted
        boolean sorted = false;

        // Repeat until the array is sorted
        while (!sorted) {
            // Assume the array is sorted
            sorted = true;

            // Loop through the array
            for (int i = 0; i < array.size() - 1; i++) {
                // If two adjacent elements are not in order, swap them
                // and set the `sorted` flag to `false`
                if (SortingOrder.ASCENDING.equals(order) &&
                        JSONComparator.compare((JsonObject) array.get(i), (JsonObject) array.get(i + 1), attr) > 0) {
                    JsonObject temp = (JsonObject) array.get(i);
                    array.set(i, array.get(i + 1));
                    array.set(i + 1, temp);
                    sorted = false;
                } else if (SortingOrder.DESCENDING.equals(order) &&
                        JSONComparator.compare((JsonObject) array.get(i), (JsonObject) array.get(i + 1), attr) < 0) {
                    JsonObject temp = (JsonObject) array.get(i);
                    array.set(i, array.get(i + 1));
                    array.set(i + 1, temp);
                    sorted = false;
                }
            }
        }

        // Measure the end time
        long endTime = System.nanoTime();

        // Calculate the elapsed time in nanoseconds
        long elapsedTime = endTime - startTime;

        // Print the elapsed time
        System.out.println("Elapsed time: " + elapsedTime + " nanoseconds");
        executionTime = elapsedTime;
        // Return the sorted array
        return array;
    }

    public JsonArray limitedBubbleSort(JsonArray array, String attr, SortingOrder order, int maxIterations) {
        executionTime = 0;
        // Keep track of whether the array is sorted
        boolean sorted = false;
        // Keep track of the number of iterations
        int iterationCount = 0;
        // Measure the start time
        long startTime = System.nanoTime();
        // Repeat until the array is sorted or the maximum number of iterations has been reached
        while (!sorted && iterationCount < maxIterations) {
            // Assume the array is sorted
            sorted = true;

            // Loop through the array
            for (int i = 0; i < array.size() - 1; i++) {
                // If two adjacent elements are not in order, swap them
                // and set the `sorted` flag to `false`
                if (SortingOrder.ASCENDING.equals(order) &&
                        JSONComparator.compare((JsonObject) array.get(i), (JsonObject) array.get(i + 1), attr) > 0) {
                    JsonObject temp = (JsonObject) array.get(i);
                    array.set(i, array.get(i + 1));
                    array.set(i + 1, temp);
                    sorted = false;
                } else if (SortingOrder.DESCENDING.equals(order) &&
                        JSONComparator.compare((JsonObject) array.get(i), (JsonObject) array.get(i + 1), attr) < 0) {
                    JsonObject temp = (JsonObject) array.get(i);
                    array.set(i, array.get(i + 1));
                    array.set(i + 1, temp);
                    sorted = false;
                }
            }

            // Increment the iteration count
            iterationCount++;
        }
        // Measure the end time
        long endTime = System.nanoTime();

        // Calculate the elapsed time in nanoseconds
        long elapsedTime = endTime - startTime;
        executionTime = elapsedTime;
        // Return the sorted array
        return array;
    }

    @Override
    public SortedJsonDataResponse run(JsonArray array, String attr, SortingOrder order) {
        JsonArray sortedData = bubbleSort(array, attr, order);
        return new SortedJsonDataResponse(sortedData, executionTime);
    }

    @Override
    public SortedJsonDataResponse run(JsonArray array, String attr, int maxIterations, SortingOrder order) {
        JsonArray sortedData = limitedBubbleSort(array, attr, order, maxIterations);
        return new SortedJsonDataResponse(sortedData, executionTime);
    }

}