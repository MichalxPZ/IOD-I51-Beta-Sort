package pl.put.poznan.sorting_alg;

import org.json.JSONObject;

public class JSONComparator {

    public static int compare(JSONObject o1, JSONObject o2, String attr) {
        String v1 = (String) o1.get(attr);
        String v2 = (String) o2.get(attr);
        return v1.compareTo(v2);
    }
}
