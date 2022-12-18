package pl.put.poznan.sorting_alg;

import org.json.JSONArray;
import org.json.JSONObject;

public class QuickSort implements Sortable {

    public static JSONArray quickSort(JSONArray array, String attr, SortingOrder order) {
        // Record the starting time of the algorithm
        long startTime = System.nanoTime();

        quick(array, 0, array.length() - 1, attr);
        // Record the ending time of the algorithm
        long endTime = System.nanoTime();
        // Print the execution time of the algorithm
        System.out.println("Total execution time: " + (endTime - startTime) + " nanoseconds");

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

    public static JSONArray quick(JSONArray array, int start, int end, String attr) {

        // If the array has length 1 or 0, it is already sorted
        if (end - start < 1) {
            return array;
        }

        // Choose a pivot element
        JSONObject pivot = (JSONObject) array.get(end);

        // Partition the array around the pivot element
        int partition = partition(array, start, end, pivot, attr);

        // Sort the left and right partitions
        quick(array, start, partition - 1, attr);
        quick(array, partition + 1, end, attr);

        return array;
    }

    public static JSONArray limitedQuick(JSONArray array, int start, int end, String attr, int maxIterations) {
        // If the array has length 1 or 0, it is already sorted
        if (end - start < 1) {
            return array;
        }

        // If we have reached the maximum number of iterations, return
        if (maxIterations == 0) {
            return array;
        }

        // Choose a pivot element
        JSONObject pivot = (JSONObject) array.get(end);

        // Partition the array around the pivot element
        int partition = partition(array, start, end, pivot, attr);

        // Decrement the number of iterations
        maxIterations--;

        // Sort the left and right partitions
        limitedQuick(array, start, partition - 1, attr, maxIterations);
        limitedQuick(array, partition + 1, end, attr, maxIterations);

        return array;
    }

    public static JSONArray limitedQuickSort(JSONArray array, String attr, SortingOrder order, int maxIteration) {
        array = limitedQuick(array, 0, array.length() - 1, attr, maxIteration);
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

    public static int partition(JSONArray array, int start, int end, JSONObject pivot, String attr) {
        // Keep track of the current position in the array
        int current = start;

        // Loop through the array, from the start to the end
        for (int i = start; i < end; i++) {
            // If the current element is less than the pivot element,
            // swap it with the element at the current position and
            // move the current position to the right
            if (JSONComparator.compare((JSONObject) array.get(i), (JSONObject) pivot, attr) < 0) {
                JSONObject temp = (JSONObject) array.get(i);
                array.put(i, array.get(current));
                array.put(current, temp);
                current++;
            }
        }

        // Swap the pivot element with the element at the current position
        array.put(end, array.get(current));
        array.put(current, pivot);

        // Return the current position
        return current;
    }

    @Override
    public JSONArray run(JSONArray array, String attr, SortingOrder order) {
        return quickSort(array, attr, order);
    }

    @Override
    public JSONArray run(JSONArray array, String attr, SortingOrder order, int maxIterations) {
        return limitedQuickSort(array, attr, order, maxIterations);
    }
}