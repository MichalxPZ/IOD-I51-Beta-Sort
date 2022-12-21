package pl.put.poznan.sorting_madness.rest.model

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import pl.put.poznan.sorting_madness.logic.algorithms.Algorithm
import pl.put.poznan.sorting_madness.logic.algorithms.SortingOrder
import java.util.*

/**
 * A class representing a request for sorting a JSON array based on a specific property.
 * @param sortedData The list of sorted elements.
 * @param property The property to sort the array by.
 * @param sortingOrder The order in which to sort the array (ascending or descending).
 * @param iterationNumber The number of iterations to perform. This is optional and is used in certain algorithms that have a limited number of iterations.
 * @param algorithm The algorithm to use for sorting the array.
 * @param time The time taken to sort the list.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
data class ResponseModel<T>(
    val sortedData: ArrayList<T>,
    val property: String?,
    val sortingOrder: SortingOrder,
    val iterationNumber: Int,
    val algorithm: Algorithm?,
    val time: String?
)