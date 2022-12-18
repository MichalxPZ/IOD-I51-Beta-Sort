package pl.put.poznan.sorting_madness.rest.model

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import pl.put.poznan.sorting_madness.logic.algorithms.Algorithm
import pl.put.poznan.sorting_madness.logic.algorithms.SortingOrder
import java.util.*

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
