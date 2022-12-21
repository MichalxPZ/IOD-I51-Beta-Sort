package pl.put.poznan.sorting_madness.logic.json_algorithms;

import com.google.gson.JsonArray;

import com.google.gson.JsonObject;
import pl.put.poznan.sorting_madness.logic.algorithms.SortingOrder;

public class JsonHeapSort implements JsonSortable {

    public long executionTime = 0;

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