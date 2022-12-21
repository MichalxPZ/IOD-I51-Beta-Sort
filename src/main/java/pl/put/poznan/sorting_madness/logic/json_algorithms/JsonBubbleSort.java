package pl.put.poznan.sorting_madness.logic.json_algorithms;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import pl.put.poznan.sorting_madness.logic.algorithms.SortingOrder;

public class JsonBubbleSort implements JsonSortable {

    public long executionTime;

    public JsonArray bubbleSort(JsonArray array, String attr, SortingOrder order) {
        executionTime = 0;

        long startTime = System.nanoTime();

        boolean sorted = false;

        while (!sorted) {

            sorted = true;

            for (int i = 0; i < array.size() - 1; i++) {

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

        long endTime = System.nanoTime();

        long elapsedTime = endTime - startTime;

        System.out.println("Elapsed time: " + elapsedTime + " nanoseconds");
        executionTime = elapsedTime;
        return array;
    }

    public JsonArray limitedBubbleSort(JsonArray array, String attr, SortingOrder order, int maxIterations) {
        executionTime = 0;

        boolean sorted = false;

        int iterationCount = 0;

        long startTime = System.nanoTime();

        while (!sorted && iterationCount < maxIterations) {

            sorted = true;


            for (int i = 0; i < array.size() - 1; i++) {

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


            iterationCount++;
        }

        long endTime = System.nanoTime();


        long elapsedTime = endTime - startTime;
        executionTime = elapsedTime;

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