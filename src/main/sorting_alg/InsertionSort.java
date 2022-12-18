package pl.put.poznan.sorting_alg;

import org.json.JSONArray;
import org.json.JSONObject;

public class InsertionSort implements Sortable {

    public static JSONArray insertionSort(JSONArray array, String attr, SortingOrder order) {
        // Record the starting time of the algorithm
        long startTime = System.nanoTime();

        // Loop through the array
        for (int i = 1; i < array.length(); i++) {
            // Save the current element
            JSONObject current = (JSONObject) array.get(i);

            // Shift all elements that are greater than the current element
            // to the right, until we find an element that is less than or
            // equal to the current element
            int j = i - 1;
            while (j >= 0 &&
                    JSONComparator.compare((JSONObject) array.get(j), current, attr) > 0) {
                array.put(j + 1, array.get(j));
                j--;
            }

            // Insert the current element in the correct position
            array.put(j + 1, current);
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

    public static JSONArray limitedInsertionSort(JSONArray array, String attr, SortingOrder order, int maxIterations) {
        // Loop through the array
        for (int i = 1; i < array.length() && i <= maxIterations; i++) {
            // Save the current element
            JSONObject current = (JSONObject) array.get(i);

            // Shift all elements that are greater than the current element
            // to the right, until we find an element that is less than or
            // equal to the current element
            int j = i - 1;
            while (j >= 0 &&
                    JSONComparator.compare((JSONObject) array.get(j), current, attr) > 0) {
                array.put(j + 1, array.get(j));
                j--;
            }

            // Insert the current element in the correct position
            array.put(j + 1, current);
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
        return insertionSort(array, attr, order);
    }

    @Override
    public JSONArray run(JSONArray array, String attr, SortingOrder order, int maxIterations) {
        return limitedInsertionSort(array, attr, order, maxIterations);
    }
}