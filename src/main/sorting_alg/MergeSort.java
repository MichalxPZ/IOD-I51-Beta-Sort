package pl.put.poznan.sorting_alg;


import org.json.JSONArray;
import org.json.JSONObject;

public class MergeSort implements Sortable {

    public static JSONArray mergeSortTime(JSONArray array, String attr, SortingOrder order) {
        // Record the starting time of the algorithm
        long startTime = System.nanoTime();

        sortedArray = mergeSort(array, attr);

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

    public static JSONArray mergeSort(JSONArray array, String attr) {
        // If the array has length 1 or 0, it is already sorted
        if (array.length() <= 1) {
            return array;
        }

        // Split the array into two halves
        int middle = array.length() / 2;
        JSONArray left = new JSONArray();
        JSONArray right = new JSONArray();
        for (int i = 0; i < middle; i++) {
            left.put(array.get(i));
        }
        for (int i = middle; i < array.length(); i++) {
            right.put(array.get(i));
        }

        // Sort the left and right halves
        JSONArray newLeft = mergeSort(left, attr);
        JSONArray newRight = mergeSort(right, attr);

        // Merge the sorted left and right halves into the original array
        merge(newLeft, newRight, array, attr);

        return array;
    }

    public static JSONArray limitedMergeSort(JSONArray array, String attr, SortingOrder order, int maxIteration) {
        // If the array has length 1 or 0, it is already sorted
        if (array.length() <= 1 || maxIteration == 0) {
            return array;
        }

        // Split the array into two halves
        int middle = array.length() / 2;
        JSONArray left = new JSONArray();
        JSONArray right = new JSONArray();
        for (int i = 0; i < middle; i++) {
            left.put(array.get(i));
        }
        for (int i = middle; i < array.length(); i++) {
            right.put(array.get(i));
        }

        // Sort the left and right halves
        JSONArray newLeft = limitedMergeSort(left, attr, order, maxIteration - 1);
        JSONArray newRight = limitedMergeSort(right, attr, order, maxIteration - 1);

        // Merge the sorted left and right halves into the original array
        merge(newLeft, newRight, array, attr);

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

    public static void merge(JSONArray left, JSONArray right, JSONArray array, String attr) {
        // Keep track of the current index in the left, right, and original arrays
        int i = 0, j = 0, k = 0;

        // Loop until one of the subarrays is exhausted
        while (i < left.length() && j < right.length()) {
            // If the current element in the left subarray is less than
            // the current element in the right subarray, add it to
            // the original array and move to the next element in the left subarray
            if (JSONComparator.compare((JSONObject) left.get(i), (JSONObject) right.get(j), attr) <= 0) {
                array.put(k, left.get(i));
                i++;
            } else {
                // Otherwise, add the current element in the right subarray
                // to the original array and move to the next element in the right subarray
                array.put(k, right.get(j));
                j++;
            }
            k++;
        }

        // Add the remaining elements in the left subarray (if any) to the original array
        while (i < left.length()) {
            array.put(k, left.get(i));
            i++;
            k++;
        }

        // Add the remaining elements in the right subarray (if any) to the original array
        while (j < right.length()) {
            array.put(k, right.get(j));
            j++;
            k++;
        }
    }

    @Override
    public JSONArray run(JSONArray array, String attr, SortingOrder order) {
        return mergeSortTime(array, attr, order);
    }

    @Override
    public JSONArray run(JSONArray array, String attr, SortingOrder order, int maxIterations) {
        return limitedMergeSort(array, attr, order, maxIterations);
    }
}