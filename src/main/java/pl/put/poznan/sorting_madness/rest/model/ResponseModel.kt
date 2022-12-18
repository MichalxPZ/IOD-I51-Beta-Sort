package pl.put.poznan.sorting_madness.rest.model

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import java.time.LocalDateTime
import java.util.*

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