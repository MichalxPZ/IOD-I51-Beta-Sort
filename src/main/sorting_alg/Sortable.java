package pl.put.poznan.sorting_alg;

import org.json.JSONArray;

public interface Sortable {
    JSONArray run(JSONArray array, String attr, SortingOrder order);

    JSONArray run(JSONArray array, String attr, SortingOrder order, int maxIterations);
}
