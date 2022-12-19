package pl.put.poznan.sorting_madness.logic.json_algorithms;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import pl.put.poznan.sorting_madness.logic.algorithms.SortingOrder;

public class JsonInsertionSort implements JsonSortable {

    public long executionTime = 0;

    public JsonArray insertionSort(JsonArray array, String attr, SortingOrder order) {
        // Record the starting time of the algorithm
        long startTime = System.nanoTime();

        // Loop through the array
        for (int i = 1; i < array.size(); i++) {
            // Save the current element
            JsonObject current = (JsonObject) array.get(i);

            // Shift all elements that are greater than the current element
            // to the right, until we find an element that is less than or
            // equal to the current element
            int j = i - 1;
            while (j >= 0 &&
                    JSONComparator.compare((JsonObject) array.get(j), current, attr) > 0) {
                array.set(j + 1, array.get(j));
                j--;
            }

            // Insert the current element in the correct position
            array.set(j + 1, current);
        }

        // Record the ending time of the algorithm
        long endTime = System.nanoTime();

        // Print the execution time of the algorithm
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

    public JsonArray limitedInsertionSort(JsonArray array, String attr, SortingOrder order, int maxIterations) {
        long startTime = System.nanoTime();
        // Loop through the array
        for (int i = 1; i < array.size() && i <= maxIterations; i++) {
            // Save the current element
            JsonObject current = (JsonObject) array.get(i);

            // Shift all elements that are greater than the current element
            // to the right, until we find an element that is less than or
            // equal to the current element
            int j = i - 1;
            while (j >= 0 &&
                    JSONComparator.compare((JsonObject) array.get(j), current, attr) > 0) {
                array.set(j + 1, array.get(j));
                j--;
            }

            // Insert the current element in the correct position
            array.set(j + 1, current);
        }
        long endTime = System.nanoTime();
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

    @Override
    public SortedJsonDataResponse run(JsonArray array, String attr, SortingOrder order) {
        JsonArray sortedData = insertionSort(array, attr, order);
        return new SortedJsonDataResponse(sortedData, executionTime);
    }

    @Override
    public SortedJsonDataResponse run(JsonArray array, String attr, int maxIterations, SortingOrder order) {
        JsonArray sortedData = limitedInsertionSort(array, attr, order, maxIterations);
        return new SortedJsonDataResponse(sortedData, executionTime);
    }

}