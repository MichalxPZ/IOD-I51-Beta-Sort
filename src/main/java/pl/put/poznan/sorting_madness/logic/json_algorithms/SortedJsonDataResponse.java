package pl.put.poznan.sorting_madness.logic.json_algorithms;

import com.google.gson.JsonArray;
import lombok.Data;

import java.util.Optional;

/**
 * A class that represents a response containing the sorted data and an optional execution time.
 */
@Data public class SortedJsonDataResponse {
    /** The sorted data. */
    public JsonArray sortedData;
    /** The optional execution time. */
    public Optional<Long> time;

    /**
     * Creates a new instance of the {@code SortedJsonDataResponse} class with the given sorted data and elapsed time.
     *
     * @param array the sorted data
     * @param elapsedTime the elapsed time
     */
    public SortedJsonDataResponse(JsonArray array, Long elapsedTime) {
        this.sortedData = array;
        this.time = Optional.ofNullable(elapsedTime);
    }
}
