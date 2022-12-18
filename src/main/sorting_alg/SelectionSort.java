package pl.put.poznan.sorting_alg;

import org.json.JSONArray;
import org.json.JSONObject;

public class SelectionSort implements Sortable {

    public static JSONArray selectionSort(JSONArray array, String attr, SortingOrder order) {
        // Record the starting time of the algorithm
        long startTime = System.nanoTime();
        // Loop through the array
        for (int i = 0; i < array.length() - 1; i++) {
            // Find the minimum element in the unsorted part of the array
            int minIndex = i;
            for (int j = i + 1; j < array.length(); j++) {
                if (JSONComparator.compare((JSONObject) array.get(j), (JSONObject) array.get(minIndex), attr) < 0){
                    minIndex = j;
                }
            }

            // Swap the minimum element with the first element in the unsorted part of the array
            JSONObject temp = (JSONObject) array.get(i);
            array.put(i, array.get(minIndex));
            array.put(minIndex, temp);
        }
        // Record the ending time of the algorithm
        long endTime = System.nanoTime();
        // Print the execution time of the algorithm
        System.out.println("Total execution time: " + (endTime - startTime) + " nanoseconds");

        // Return the sorted array
        if (SortingOrder.ASCENDING.equals(order)) {
            return array;
        } else {
            JSONArray toReturn = new JSONArray();
            for (int i = array.length() - 1; i >= 0; i--) {
                toReturn.put(array.get(i));
            }
            return toReturn;
        }
    }

    public static JSONArray limitedSelectionSort(JSONArray array, String attr, SortingOrder order, int maxIterations) {
        // Loop through the array
        for (int i = 0; i < array.length() - 1 && i <= maxIterations; i++) {
            // Find the minimum element in the unsorted part of the array
            int minIndex = i;
            for (int j = i + 1; j < array.length(); j++) {
                if (JSONComparator.compare((JSONObject) array.get(j), (JSONObject) array.get(minIndex), attr) < 0) {
                    minIndex = j;
                }
            }

            // Swap the minimum element with the first element in the unsorted part of the array
            JSONObject temp = (JSONObject) array.get(i);
            array.put(i, array.get(minIndex));
            array.put(minIndex, temp);
        }

        // Return the sorted array
        if (SortingOrder.ASCENDING.equals(order)) {
            return array;
        } else {
            JSONArray toReturn = new JSONArray();
            for (int i = array.length() - 1; i >= 0; i--) {
                toReturn.put(array.get(i));
            }
            return toReturn;
        }
    }

    @Override
    public JSONArray run(JSONArray array, String attr, SortingOrder order) {
        return selectionSort(array, attr, order);
    }

    @Override
    public JSONArray run(JSONArray array, String attr, SortingOrder order, int maxIterations) {
        return limitedSelectionSort(array, attr, order, maxIterations);
    }
}