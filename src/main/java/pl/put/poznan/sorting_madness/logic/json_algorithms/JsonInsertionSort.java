package pl.put.poznan.sorting_madness.logic.json_algorithms;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import pl.put.poznan.sorting_madness.logic.algorithms.SortingOrder;

public class JsonInsertionSort implements JsonSortable {

    public long executionTime = 0;

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