package pl.put.poznan.sorting_madness.logic.algorithms;

import lombok.Data;

import java.util.List;
import java.util.Optional;

@Data public class SortedDataResponse<T> {
    public List<T> sortedData;
    public Optional<Long> time;

    public SortedDataResponse(List<T> array, Long elapsedTime) {
        this.sortedData = array;
        this.time = Optional.ofNullable(elapsedTime);
    }
}
