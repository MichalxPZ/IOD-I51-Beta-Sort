package pl.put.poznan.sorting_madness.rest

import com.google.gson.JsonObject
import org.springframework.stereotype.Service
import pl.put.poznan.sorting_madness.logic.algorithms.*
import pl.put.poznan.sorting_madness.rest.model.*
import java.util.*

@Service
class SortingService {

    fun sortOneDimensionalDataSetString(
        requestModel: RequestModel<String>
    ): ArrayList<ResponseModel<String>> {
        return callAlgorithms(requestModel)
    }
    fun sortOneDimensionalDataSetInt(
        requestModel: RequestModel<Int>
    ): ArrayList<ResponseModel<Int>> {
        return callAlgorithms(requestModel)
    }
    fun sortOneDimensionalDataSetFloat(
        requestModel: RequestModel<Float>
    ): ArrayList<ResponseModel<Float>> {
        return callAlgorithms(requestModel)

    }
    fun sortMultiDimensionalDataSet(
        requestModel: RequestModel<JsonObject>
    ) : ArrayList<ResponseModel<JsonObject>> {
        return callAlgorithms(requestModel)
    }

    private fun <T> callAlgorithms(requestModel: RequestModel<T>): ArrayList<ResponseModel<T>> {
        return if (requestModel.algorithm == null) {
            runAllAlgorithms(
                numOfIterations = requestModel.iterationNumber,
                property = requestModel.property,
                data = requestModel.data,
                order = requestModel.sortingOrder
            )
        } else {
            runAlgorithm(
                numOfIterations = requestModel.iterationNumber,
                property = requestModel.property,
                data = requestModel.data,
                algorithm = requestModel.algorithm,
                order = requestModel.sortingOrder
            )
        }
    }

    private fun <T> runAlgorithm(
        algorithm: Algorithm,
        numOfIterations: Int?,
        order: SortingOrder?,
        property: String?,
        data: ArrayList<T>
    ): ArrayList<ResponseModel<T>> {
        val sortable: Sortable = resolveAlgorithm(algorithm)
        val arrayList = ArrayList<ResponseModel<T>>()
        val sortedDataResponse: SortedDataResponse<T>
        if (numOfIterations != null && numOfIterations > 0) {
            sortedDataResponse = sortable.run(data, numOfIterations, order)
        } else {
            sortedDataResponse = sortable.run(data, order)
        }

        val sortedArray = ArrayList<T>()
        sortedDataResponse.sortedData.forEach { sortedArray.add(it) }
        arrayList.add(ResponseModel(
            sortedData = sortedArray,
            property = property,
            sortingOrder = order ?: SortingOrder.ASCENDING,
            iterationNumber = numOfIterations ?: 0,
            algorithm = algorithm,
            time = sortedDataResponse.time.toString() + "nanoseconds"
         ))
        return arrayList
    }

    private fun <T> runAllAlgorithms(
        numOfIterations: Int?,
        order: SortingOrder?,
        property: String?,
        data: ArrayList<T>
    ): ArrayList<ResponseModel<T>> {
        val arrayList = ArrayList<ResponseModel<T>>()
        for (algo in Algorithm.values()) {
            arrayList.add(
                runAlgorithm(
                    algorithm = algo,
                    numOfIterations = numOfIterations,
                    order = order,
                    property = property,
                    data = data
                )[0]
            )
        }
        return arrayList
    }

    private fun resolveAlgorithm(algorithm: Algorithm): Sortable {
        return when(algorithm) {
            Algorithm.BUBBLE_SORT -> BubbleSort()
            Algorithm.HEAP_SORT -> HeapSort()
            Algorithm.INSERTION_SORT -> InsertionSort()
            Algorithm.MERGE_SORT -> MergeSort()
            Algorithm.QUICK_SORT -> QuickSort()
            Algorithm.SELECTION_SORT -> SelectionSort()
        }
    }

}
