package pl.put.poznan.sorting_madness.logic.algorithms

import java.util.ArrayList

data class SortedDataResponse<T>(
    val sortedData: MutableList<T>,
    val time: Long?
)