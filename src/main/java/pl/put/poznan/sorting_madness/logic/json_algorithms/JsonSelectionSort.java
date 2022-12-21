package pl.put.poznan.sorting_madness.logic.json_algorithms;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import pl.put.poznan.sorting_madness.logic.algorithms.SortingOrder;

public class JsonSelectionSort implements JsonSortable {

    public long executionTime = 0;

    public JsonArray selectionSort(JsonArray array, String attr, SortingOrder order) {

        long startTime = System.nanoTime();

        for (int i = 0; i < array.size() - 1; i++) {

            int minIndex = i;
            for (int j = i + 1; j < array.size(); j++) {
                if (JSONComparator.compare((JsonObject) array.get(j), (JsonObject) array.get(minIndex), attr) < 0) {
                    minIndex = j;
                }
            }

            JsonObject temp = (JsonObject) array.get(i);
            array.set(i, array.get(minIndex));
            array.set(minIndex, temp);
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

    public JsonArray limitedSelectionSort(JsonArray array, String attr, SortingOrder order, int maxIterations) {
        long startTime = System.nanoTime();

        for (int i = 0; i < array.size() - 1 && i <= maxIterations; i++) {

            int minIndex = i;
            for (int j = i + 1; j < array.size(); j++) {
                if (JSONComparator.compare((JsonObject) array.get(j), (JsonObject) array.get(minIndex), attr) < 0) {
                    minIndex = j;
                }
            }

            JsonObject temp = (JsonObject) array.get(i);
            array.set(i, array.get(minIndex));
            array.set(minIndex, temp);
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
        JsonArray sortedData = selectionSort(array, attr, order);
        return new SortedJsonDataResponse(sortedData, executionTime);
    }

    @Override
    public SortedJsonDataResponse run(JsonArray array, String attr, int maxIterations, SortingOrder order) {
        JsonArray sortedData = limitedSelectionSort(array, attr, order, maxIterations);
        return new SortedJsonDataResponse(sortedData, executionTime);
    }
}