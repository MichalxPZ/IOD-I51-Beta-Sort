package pl.put.poznan.sorting_madness.logic.json_algorithms;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import pl.put.poznan.sorting_madness.logic.algorithms.SortingOrder;

public class JsonQuickSort implements JsonSortable {

    public long executionTime = 0;

    public JsonArray quickSort(JsonArray array, String attr, SortingOrder order) {

        long startTime = System.nanoTime();

        quick(array, 0, array.size() - 1, attr);

        long endTime = System.nanoTime();

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

        if (end - start < 1) {
            return array;
        }

        JsonObject pivot = (JsonObject) array.get(end);

        int partition = partition(array, start, end, pivot, attr);

        quick(array, start, partition - 1, attr);
        quick(array, partition + 1, end, attr);

        long endTime = System.nanoTime();
        executionTime = endTime - startTime;
        return array;
    }

    public JsonArray limitedQuick(JsonArray array, int start, int end, String attr, int maxIterations) {
        if (end - start < 1) {
            return array;
        }

        if (maxIterations == 0) {
            return array;
        }

        JsonObject pivot = (JsonObject) array.get(end);

        int partition = partition(array, start, end, pivot, attr);

        maxIterations--;

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

        int current = start;

        for (int i = start; i < end; i++) {

            if (JSONComparator.compare((JsonObject) array.get(i), (JsonObject) pivot, attr) < 0) {
                JsonObject temp = (JsonObject) array.get(i);
                array.set(i, array.get(current));
                array.set(current, temp);
                current++;
            }
        }

        array.set(end, array.get(current));
        array.set(current, pivot);

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