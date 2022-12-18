package pl.put.poznan.sorting_alg;

import org.json.JSONArray;
import org.json.JSONObject;

public class HeapSort implements Sortable {

    public static JSONArray heapSort(JSONArray array, String attr, SortingOrder order) {
        // Record the starting time of the algorithm
        long startTime = System.nanoTime();

        // Build the heap
        for (int i = array.length() / 2 - 1; i >= 0; i--) {
            heapify(array, attr, array.length(), i);
        }

        // Extract the elements from the heap
        for (int i = array.length() - 1; i >= 0; i--) {
            // Move the root of the heap to the end of the array
            JSONObject temp = (JSONObject) array.get(0);
            array.put(0, array.get(i));
            array.put(i, temp);

            // Rebuild the heap
            heapify(array, attr, i, 0);
        }

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

    public static JSONArray limitedHeapSort(JSONArray array, String attr, SortingOrder order, int maxIterations) {
        // Record the starting time of the algorithm
        long startTime = System.nanoTime();

        // Build the heap
        for (int i = array.length() / 2 - 1; i >= 0; i--) {
            heapify(array, attr, array.length(), i);
        }

        // Extract the elements from the heap
        for (int i = array.length() - 1; i >= 0; i--) {
            // Move the root of the heap to the end of the array
            JSONObject temp = (JSONObject) array.get(0);
            array.put(0, array.get(i));
            array.put(i, temp);

            // Rebuild the heap
            heapify(array, attr, i, 0);

            maxIterations--;
            if (maxIterations == 0) {
                break;
            }
        }

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

    public static void heapify(JSONArray array, String attr, int size, int i) {
        // Calculate the indices of the left and right children of the current node
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        // Keep track of the largest element in the heap
        int largest = i;

        // If the left child is larger than the current node, update the largest element
        if (left < size &&
                JSONComparator.compare((JSONObject) array.get(left), (JSONObject) array.get(largest), attr) > 0) {
            largest = left;
        }

        // If the right child is larger than the current node or the left child, update the largest element
        if (right < size &&
                JSONComparator.compare((JSONObject) array.get(right), (JSONObject) array.get(largest), attr) > 0) {
            largest = right;
        }

        // If the largest element is not the current node, swap it with the current node
        // and continue heapifying the affected sub-heap
        if (largest != i) {
            JSONObject temp = (JSONObject) array.get(i);
            array.put(i, array.get(largest));
            array.put(largest, temp);
            heapify(array, attr, size, largest);
        }
    }

    @Override
    public JSONArray run(JSONArray array, String attr, SortingOrder order) {
        return heapSort(array, attr, order);
    }

    @Override
    public JSONArray run(JSONArray array, String attr, SortingOrder order, int maxIterations) {
        return limitedHeapSort(array, attr, order, maxIterations);
    }
}