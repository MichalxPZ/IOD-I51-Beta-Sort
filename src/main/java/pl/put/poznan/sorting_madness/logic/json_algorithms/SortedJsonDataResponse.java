package pl.put.poznan.sorting_madness.logic.json_algorithms;

import com.google.gson.JsonArray;
import lombok.Data;

import java.util.Optional;

@Data public class SortedJsonDataResponse {
    public JsonArray sortedData;
    public Optional<Long> time;

    public SortedJsonDataResponse(JsonArray array, Long elapsedTime) {
        this.sortedData = array;
        this.time = Optional.ofNullable(elapsedTime);
    }
}
