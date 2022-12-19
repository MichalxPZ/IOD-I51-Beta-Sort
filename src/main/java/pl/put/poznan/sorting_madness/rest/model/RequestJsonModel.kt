package pl.put.poznan.sorting_madness.rest.model

import com.google.gson.JsonArray
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import pl.put.poznan.sorting_madness.logic.algorithms.Algorithm
import pl.put.poznan.sorting_madness.logic.algorithms.SortingOrder

@Data
@NoArgsConstructor
@AllArgsConstructor
data class RequestJsonModel(
    val data: JsonArray,
    val property: String?,
    val sortingOrder: SortingOrder?,
    val iterationNumber: Int?,
    val algorithm: Algorithm?
)
