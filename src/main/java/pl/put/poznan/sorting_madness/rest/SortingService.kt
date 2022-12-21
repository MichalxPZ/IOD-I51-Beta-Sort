package pl.put.poznan.sorting_madness.rest

import com.google.gson.JsonArray
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import pl.put.poznan.sorting_madness.logic.algorithms.*
import pl.put.poznan.sorting_madness.logic.json_algorithms.*
import pl.put.poznan.sorting_madness.rest.model.RequestJsonModel
import pl.put.poznan.sorting_madness.rest.model.RequestModel
import pl.put.poznan.sorting_madness.rest.model.ResponseModel

@Service
class SortingService {

    private val logger: Logger = LoggerFactory.getLogger(SortingService::class.java)

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
        requestModel: RequestJsonModel
    ): ArrayList<ResponseModel<JsonArray>> {
        return callAlgorithmsOnJsonObjects(requestModel)
    }

    private fun <T : Comparable<T>> callAlgorithms(requestModel: RequestModel<T>): ArrayList<ResponseModel<T>> {
        return if (requestModel.algorithm == null) {
            logStartOfAlgorithmExecution<T>(isJson = false, allAlgorithms = true, requestModel = requestModel)
            runAllAlgorithms(
                numOfIterations = requestModel.iterationNumber,
                property = requestModel.property,
                data = requestModel.data,
                order = requestModel.sortingOrder
            ).apply {
                logFinishOfAlgorithmExecution(
                    isJson = false,
                    allAlgorithms = true,
                    responseModel = if (this.isNotEmpty()) this[0] else null
                )
            }
        } else {
            logStartOfAlgorithmExecution<T>(isJson = false, allAlgorithms = false, requestModel = requestModel)
            runAlgorithm(
                numOfIterations = requestModel.iterationNumber,
                property = requestModel.property,
                data = requestModel.data,
                algorithm = requestModel.algorithm,
                order = requestModel.sortingOrder
            ).apply {
                logFinishOfAlgorithmExecution(
                    isJson = false,
                    allAlgorithms = false,
                    responseModel = if (this.isNotEmpty()) this[0] else null
                )
            }
        }
    }

    private fun callAlgorithmsOnJsonObjects(requestModel: RequestJsonModel): ArrayList<ResponseModel<JsonArray>> {
        return if (requestModel.algorithm == null) {
            logStartOfAlgorithmExecution<JsonArray>(
                isJson = true,
                allAlgorithms = true,
                requestJsonModel = requestModel
            )
            runAllAlgorithmsOnJsonObjects(
                numOfIterations = requestModel.iterationNumber,
                property = requestModel.property,
                data = requestModel.data,
                order = requestModel.sortingOrder
            ).apply {
                logFinishOfAlgorithmExecution(
                    isJson = true,
                    allAlgorithms = true,
                    responseModel = if (this.isNotEmpty()) this[0] else null
                )
            }

        } else {
            logStartOfAlgorithmExecution<JsonArray>(
                isJson = true,
                allAlgorithms = false,
                requestJsonModel = requestModel
            )
            runAlgorithmOnJsonObject(
                numOfIterations = requestModel.iterationNumber,
                property = requestModel.property,
                data = requestModel.data,
                algorithm = requestModel.algorithm,
                order = requestModel.sortingOrder
            ).apply {
                logFinishOfAlgorithmExecution(
                    isJson = true,
                    allAlgorithms = false,
                    responseModel = if (this.isNotEmpty()) this[0] else null
                )
            }
        }
    }

    private fun <T : Comparable<T>> runAlgorithm(
        algorithm: Algorithm,
        numOfIterations: Int?,
        order: SortingOrder?,
        property: String?,
        data: ArrayList<T>
    ): ArrayList<ResponseModel<T>> {
        val sortable: Sortable = resolveAlgorithm(algorithm)
        val arrayList = ArrayList<ResponseModel<T>>()
        val sortedDataResponse: SortedDataResponse<T> = if (numOfIterations != null && numOfIterations > 0) {
            sortable.run(data, numOfIterations, order)
        } else {
            sortable.run(data, order)
        }

        val sortedArray = ArrayList<T>()
        sortedDataResponse.sortedData.forEach { sortedArray.add(it) }

        arrayList.add(
            ResponseModel(
                sortedData = sortedArray,
                property = property,
                sortingOrder = order ?: SortingOrder.ASCENDING,
                iterationNumber = numOfIterations ?: 0,
                algorithm = algorithm,
                time = sortedDataResponse.time.get().toString() + " ns"
            )
        )
        return arrayList
    }

    private fun <T : Comparable<T>> runAllAlgorithms(
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

    private fun runAllAlgorithmsOnJsonObjects(
        numOfIterations: Int?,
        order: SortingOrder?,
        property: String?,
        data: JsonArray
    ): ArrayList<ResponseModel<JsonArray>> {
        val arrayList = ArrayList<ResponseModel<JsonArray>>()
        for (algo in Algorithm.values()) {
            arrayList.add(
                runAlgorithmOnJsonObject(
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


    private fun runAlgorithmOnJsonObject(
        algorithm: Algorithm,
        numOfIterations: Int?,
        order: SortingOrder?,
        property: String?,
        data: JsonArray
    ): ArrayList<ResponseModel<JsonArray>> {
        val sortable: JsonSortable = resolveJsonAlgorithm(algorithm)
        val sortedDataResponse: SortedJsonDataResponse = if (numOfIterations != null && numOfIterations > 0) {
            sortable.run(data, property, numOfIterations, order)
        } else {
            sortable.run(data, property, order)
        }

        val sortedArray: ArrayList<JsonArray> = ArrayList<JsonArray>().apply {
            add(sortedDataResponse.sortedData)
        }

        val responseArray: ArrayList<ResponseModel<JsonArray>> = java.util.ArrayList<ResponseModel<JsonArray>>().apply {
            add(
                ResponseModel(
                    sortedData = sortedArray,
                    property = property,
                    sortingOrder = order ?: SortingOrder.ASCENDING,
                    iterationNumber = numOfIterations ?: 0,
                    algorithm = algorithm,
                    time = sortedDataResponse.time.get().toString() + " ns"
                )
            )
        }

        return responseArray
    }

    private fun resolveAlgorithm(algorithm: Algorithm): Sortable {
        return when (algorithm) {
            Algorithm.BUBBLE_SORT -> BubbleSort()
            Algorithm.HEAP_SORT -> HeapSort()
            Algorithm.INSERTION_SORT -> InsertionSort()
            Algorithm.MERGE_SORT -> MergeSort()
            Algorithm.QUICK_SORT -> QuickSort()
            Algorithm.SELECTION_SORT -> SelectionSort()
        }
    }

    private fun resolveJsonAlgorithm(algorithm: Algorithm): JsonSortable {
        return when (algorithm) {
            Algorithm.BUBBLE_SORT -> JsonBubbleSort()
            Algorithm.HEAP_SORT -> JsonHeapSort()
            Algorithm.INSERTION_SORT -> JsonInsertionSort()
            Algorithm.MERGE_SORT -> JsonMergeSort()
            Algorithm.QUICK_SORT -> JsonQuickSort()
            Algorithm.SELECTION_SORT -> JsonSelectionSort()
        }
    }

    private fun <T> logStartOfAlgorithmExecution(
        isJson: Boolean,
        allAlgorithms: Boolean,
        requestJsonModel: RequestJsonModel? = null,
        requestModel: RequestModel<T>? = null
    ) {
        if (isJson) {
            if (allAlgorithms) logger.info("Start sorting json data: ${requestJsonModel?.data} with all algorithms")
            else logger.info("Start sorting json data: ${requestJsonModel?.data} using algorithm: ${requestJsonModel?.algorithm?.name}")
        } else {
            if (allAlgorithms) logger.info("Start sorting data: ${requestJsonModel?.data} with all algorithms")
            else logger.info("Start sorting data: ${requestModel?.data} using algorithm: ${requestModel?.algorithm?.name}")
        }
    }

    private fun <T> logFinishOfAlgorithmExecution(
        isJson: Boolean,
        allAlgorithms: Boolean,
        responseModel: ResponseModel<T>? = null,
    ) {
        if (isJson) {
            if (allAlgorithms) {
                logger.info("Finish sorting json data: ${responseModel?.sortedData} with all algorithms and with order ${responseModel?.sortingOrder} and ${responseModel?.iterationNumber} iterations")
                responseModel?.sortedData?.forEach { _ ->
                    logger.info("Results using algorithm ${responseModel.algorithm?.name}: ${responseModel.sortedData}, time: ${responseModel.time} ns")
                }
            } else {
                logger.info("Finish sorting json data: ${responseModel?.sortedData} using algorithm: ${responseModel?.algorithm?.name} with order ${responseModel?.sortingOrder} and ${responseModel?.iterationNumber} iterations")
                logger.info("Results using algorithm ${responseModel?.algorithm?.name}: ${responseModel?.sortedData}, time: ${responseModel?.time} ns")

            }
        } else {
            if (allAlgorithms) {
                logger.info("Finish sorting data: ${responseModel?.sortedData} with all algorithms and with order ${responseModel?.sortingOrder} and ${responseModel?.iterationNumber} iterations")
                responseModel?.sortedData?.forEach { _ ->
                    logger.info("Results using algorithm ${responseModel.algorithm?.name}: ${responseModel.sortedData}, time: ${responseModel.time} ns")
                }
            } else {
                logger.info("Finish sorting data: ${responseModel?.sortedData} using algorithm: ${responseModel?.algorithm?.name} with order ${responseModel?.sortingOrder} and ${responseModel?.iterationNumber} iterations")
                logger.info("Results using algorithm ${responseModel?.algorithm?.name}: ${responseModel?.sortedData}, time: ${responseModel?.time} ns")
            }
        }
    }
}