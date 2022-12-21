package pl.put.poznan.sorting_madness.logic.algorithms;

import lombok.Data;

import java.util.List;
import java.util.Optional;

/**
 * A class that represents a response containing the sorted data and an optional execution time.
 */
@Data public class SortedDataResponse<T> {
    /** The sorted data. */
    public List<T> sortedData;
    /** The optional execution time. */
    public Optional<Long> time;

    /**
     * Creates a new instance of the {@code SortedDataResponse} class with the given sorted data and elapsed time.
     *
     * @param array the sorted data
     * @param elapsedTime the elapsed time
     */
    public SortedDataResponse(List<T> array, Long elapsedTime) {
        this.sortedData = array;
        this.time = Optional.ofNullable(elapsedTime);
    }
}
