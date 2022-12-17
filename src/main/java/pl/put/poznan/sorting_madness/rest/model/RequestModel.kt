package pl.put.poznan.sorting_madness.rest.model

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import org.json.JSONArray

@Data
@NoArgsConstructor
@AllArgsConstructor
data class RequestModel<T>(
    val data: ArrayList<T>,
    val property: String?,
    val sortingOrder: SortingOrder?,
    val iterationNumber: Int?,
    val algorithm: Algorithm?
)

enum class SortingOrder {
    ASCENDING, DESCENDING
}

enum class Algorithm {
    //todo add algorithms when implemented
    SORT
}
