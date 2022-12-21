package pl.put.poznan.sorting_madness.logic.json_algorithms;


import com.google.gson.JsonArray;
import pl.put.poznan.sorting_madness.logic.algorithms.SortingOrder;

public interface JsonSortable {
     SortedJsonDataResponse run(JsonArray array, String attr, SortingOrder order);

     SortedJsonDataResponse run(JsonArray array, String attr, int maxIterations, SortingOrder order);
}
