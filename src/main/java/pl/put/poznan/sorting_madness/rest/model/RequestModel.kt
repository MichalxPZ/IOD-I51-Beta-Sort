package pl.put.poznan.sorting_madness.rest.model

import lombok.AllArgsConstructor
import lombok.Data
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import pl.put.poznan.sorting_madness.logic.algorithms.Algorithm
import pl.put.poznan.sorting_madness.logic.algorithms.SortingOrder
import java.util.*

/**
 * This class represents a request for sorting a list of elements.
 * @param data The list of elements to be sorted.
 * @param property The property of the elements that should be used for sorting.
 * @param sortingOrder The order in which the elements should be sorted.
 * @param iterationNumber The maximum number of iterations to run the algorithm.
 * @param algorithm The algorithm to use for sorting.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
data class RequestModel<T>(
    val data: ArrayList<T>,
    val property: String?,
    val sortingOrder: SortingOrder?,
    val iterationNumber: Int?,
    val algorithm: Algorithm?
)