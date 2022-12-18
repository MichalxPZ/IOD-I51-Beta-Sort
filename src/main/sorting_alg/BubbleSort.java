package pl.put.poznan.sorting_alg;

import org.json.JSONArray;
import org.json.JSONObject;

public class BubbleSort implements Sortable {

    public static JSONArray bubbleSort(JSONArray array, String attr, SortingOrder order) {
        // Measure the start time
        long startTime = System.nanoTime();

        // Keep track of whether the array is sorted
        boolean sorted = false;

        // Repeat until the array is sorted
        while (!sorted) {
            // Assume the array is sorted
            sorted = true;

            // Loop through the array
            for (int i = 0; i < array.length(); i++) {
                // If two adjacent elements are not in order, swap them
                // and set the `sorted` flag to `false`
                if (SortingOrder.ASCENDING.equals(order) &&
                        JSONComparator.compare((JSONObject) array.get(i), (JSONObject) array.get(i + 1), attr) > 0) {
                    JSONObject temp = (JSONObject) array.get(i);
                    array.put(i, array.get(i + 1));
                    array.put(i + 1, temp);
                    sorted = false;
                } else if (SortingOrder.DESCENDING.equals(order) &&
                        JSONComparator.compare((JSONObject) array.get(i), (JSONObject) array.get(i + 1), attr) < 0) {
                    JSONObject temp = (JSONObject) array.get(i);
                    array.put(i, array.get(i + 1));
                    array.put(i + 1, temp);
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

        // Return the sorted array
        return array;
    }

    public static JSONArray limitedBubbleSort(JSONArray array, String attr, SortingOrder order, int maxIterations) {
        // Keep track of whether the array is sorted
        boolean sorted = false;
        // Keep track of the number of iterations
        int iterationCount = 0;

        // Repeat until the array is sorted or the maximum number of iterations has been reached
        while (!sorted && iterationCount < maxIterations) {
            // Assume the array is sorted
            sorted = true;

            // Loop through the array
            for (int i = 0; i < array.length(); i++) {
                // If two adjacent elements are not in order, swap them
                // and set the `sorted` flag to `false`
                if (SortingOrder.ASCENDING.equals(order) &&
                        JSONComparator.compare((JSONObject) array.get(i), (JSONObject) array.get(i + 1), attr) > 0) {
                    JSONObject temp = (JSONObject) array.get(i);
                    array.put(i, array.get(i + 1));
                    array.put(i + 1, temp);
                    sorted = false;
                } else if (SortingOrder.DESCENDING.equals(order) &&
                        JSONComparator.compare((JSONObject) array.get(i), (JSONObject) array.get(i + 1), attr) < 0) {
                    JSONObject temp = (JSONObject) array.get(i);
                    array.put(i, array.get(i + 1));
                    array.put(i + 1, temp);
                    sorted = false;
                }
            }

            // Increment the iteration count
            iterationCount++;
        }

        // Return the sorted array
        return array;
    }

    @Override
    public JSONArray run(JSONArray array, String attr, SortingOrder order) {
        return bubbleSort(array, attr, order);
    }

    @Override
    public JSONArray run(JSONArray array, String attr, SortingOrder order, int maxIterations) {
        return limitedBubbleSort(array, attr, order, maxIterations);
    }
}