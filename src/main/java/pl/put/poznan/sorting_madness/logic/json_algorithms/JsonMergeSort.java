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

        // Record the ending time of the algorithm
        long endTime = System.nanoTime();

        // Print the execution time of the algorithm
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
        // If the array has length 1 or 0, it is already sorted
        if (array.size() <= 1) {
            return array;
        }

        // Split the array into two halves
        int middle = array.size() / 2;
        JsonArray left = new JsonArray();
        JsonArray right = new JsonArray();
        for (int i = 0; i < middle; i++) {
            left.add(array.get(i));
        }
        for (int i = middle; i < array.size(); i++) {
            right.add(array.get(i));
        }

        // Sort the left and right halves
        JsonArray newLeft = mergeSort(left, attr);
        JsonArray newRight = mergeSort(right, attr);

        // Merge the sorted left and right halves into the original array
        merge(newLeft, newRight, array, attr);

        return array;
    }

    public JsonArray limitedMergeSort(JsonArray array, String attr, SortingOrder order, int maxIteration) {
        // If the array has length 1 or 0, it is already sorted
        if (array.size() <= 1 || maxIteration == 0) {
            return array;
        }

        // Split the array into two halves
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

        // Sort the left and right halves
        JsonArray newLeft = limitedMergeSort(left, attr, order, maxIteration - 1);
        JsonArray newRight = limitedMergeSort(right, attr, order, maxIteration - 1);

        // Merge the sorted left and right halves into the original array
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
        // Keep track of the current index in the left, right, and original arrays
        int i = 0, j = 0, k = 0;

        // Loop until one of the subarrays is exhausted
        while (i < left.size() && j < right.size()) {
            // If the current element in the left subarray is less than
            // the current element in the right subarray, add it to
            // the original array and move to the next element in the left subarray
            if (JSONComparator.compare((JsonObject) left.get(i), (JsonObject) right.get(j), attr) <= 0) {
                array.set(k, left.get(i));
                i++;
            } else {
                // Otherwise, add the current element in the right subarray
                // to the original array and move to the next element in the right subarray
                array.set(k, right.get(j));
                j++;
            }
            k++;
        }

        // Add the remaining elements in the left subarray (if any) to the original array
        while (i < left.size()) {
            array.set(k, left.get(i));
            i++;
            k++;
        }

        // Add the remaining elements in the right subarray (if any) to the original array
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