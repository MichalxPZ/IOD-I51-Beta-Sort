package pl.put.poznan.sorting_madness.logic.json_algorithms;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import pl.put.poznan.sorting_madness.logic.algorithms.SortingOrder;

public class JsonMergeSort implements JsonSortable {

    public long executionTime = 0;

    public JsonArray mergeSortTime(JsonArray array, String attr, SortingOrder order) {
        // Record the starting time of the algorithm
        long startTime = System.nanoTime();

        JsonArray sortedArray = mergeSort(array, attr);

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

    public JsonArray mergeSort(JsonArray array, String attr) {

        if (array.size() <= 1) {
            return array;
        }

        int middle = array.size() / 2;
        JsonArray left = new JsonArray();
        JsonArray right = new JsonArray();
        for (int i = 0; i < middle; i++) {
            left.add(array.get(i));
        }
        for (int i = middle; i < array.size(); i++) {
            right.add(array.get(i));
        }

        JsonArray newLeft = mergeSort(left, attr);
        JsonArray newRight = mergeSort(right, attr);

        merge(newLeft, newRight, array, attr);

        return array;
    }

    public JsonArray limitedMergeSort(JsonArray array, String attr, SortingOrder order, int maxIteration) {
        if (array.size() <= 1 || maxIteration == 0) {
            return array;
        }

        int middle = array.size() / 2;
        JsonArray left = new JsonArray();
        JsonArray right = new JsonArray();
        for (int i = 0; i < middle; i++) {
            left.add(array.get(i));
        }
        for (int i = middle; i < array.size(); i++) {
            right.add(array.get(i));
        }

        long startTime = System.nanoTime();

        JsonArray newLeft = limitedMergeSort(left, attr, order, maxIteration - 1);
        JsonArray newRight = limitedMergeSort(right, attr, order, maxIteration - 1);

        merge(newLeft, newRight, array, attr);

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

    public void merge(JsonArray left, JsonArray right, JsonArray array, String attr) {

        int i = 0, j = 0, k = 0;

        while (i < left.size() && j < right.size()) {

            if (JSONComparator.compare((JsonObject) left.get(i), (JsonObject) right.get(j), attr) <= 0) {
                array.set(k, left.get(i));
                i++;
            } else {
                array.set(k, right.get(j));
                j++;
            }
            k++;
        }

        while (i < left.size()) {
            array.set(k, left.get(i));
            i++;
            k++;
        }

        while (j < right.size()) {
            array.set(k, right.get(j));
            j++;
            k++;
        }
    }

    @Override
    public SortedJsonDataResponse run(JsonArray array, String attr, SortingOrder order) {
        JsonArray sortedData = mergeSortTime(array, attr, order);
        return new SortedJsonDataResponse(sortedData, executionTime);
    }

    @Override
    public SortedJsonDataResponse run(JsonArray array, String attr, int maxIterations, SortingOrder order) {
        JsonArray sortedData = limitedMergeSort(array, attr, order, maxIterations);
        return new SortedJsonDataResponse(sortedData, executionTime);
    }

}