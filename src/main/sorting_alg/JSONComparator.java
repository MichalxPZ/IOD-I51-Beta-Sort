package pl.put.poznan.sorting_alg;

import org.json.JSONObject;

public class JSONComparator {

    public static int compare(JSONObject o1, JSONObject o2, String attr) {
        String v1 = (String) ((JSONObject) o1.get("attributes")).get(attr);
        String v2 = (String) ((JSONObject) o2.get("attributes")).get(attr);
        return v1.compareTo(v2);
    }
}
