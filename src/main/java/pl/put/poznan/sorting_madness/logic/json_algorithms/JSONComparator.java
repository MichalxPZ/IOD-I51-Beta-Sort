package pl.put.poznan.sorting_madness.logic.json_algorithms;

import com.google.gson.JsonObject;

public class JSONComparator {

    public static int compare(JsonObject o1, JsonObject o2, String attr) {
        String v1 = (String) ((JsonObject) o1).get(attr).toString();
        String v2 = (String) ((JsonObject) o2).get(attr).toString();
        return v1.compareTo(v2);
    }
}
