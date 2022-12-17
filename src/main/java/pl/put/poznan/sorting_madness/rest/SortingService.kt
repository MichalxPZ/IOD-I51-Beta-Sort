package pl.put.poznan.sorting_madness.rest

import org.json.JSONArray
import org.json.JSONObject
import org.springframework.stereotype.Service
import pl.put.poznan.sorting_madness.rest.model.Algorithm
import pl.put.poznan.sorting_madness.rest.model.RequestModel
import pl.put.poznan.sorting_madness.rest.model.ResponseModel
import pl.put.poznan.sorting_madness.rest.model.SortingOrder

@Service
class SortingService {

    fun sortOneDimensionalDataSetString(
        requestModel: RequestModel<String>
    ): ResponseModel<String> {
        return if (requestModel.algorithm == null) {
            runAllAlgorithms<String>(
                numOfIterations = requestModel.iterationNumber,
                property = requestModel.property,
                data = requestModel.data,
                order = requestModel.sortingOrder
            )
        } else {
            runAlgorithm<String>(
                numOfIterations = requestModel.iterationNumber,
                property = requestModel.property,
                data = requestModel.data,
                algorithm = requestModel.algorithm,
                order = requestModel.sortingOrder
            )
        }
    }
    fun sortOneDimensionalDataSetInt(
        requestModel: RequestModel<Int>
    ): ResponseModel<Int> {
        return if (requestModel.algorithm == null) {
            runAllAlgorithms<Int>(
                numOfIterations = requestModel.iterationNumber,
                property = requestModel.property,
                data = requestModel.data,
                order = requestModel.sortingOrder
            )
        } else {
            runAlgorithm<Int>(
                numOfIterations = requestModel.iterationNumber,
                property = requestModel.property,
                data = requestModel.data,
                algorithm = requestModel.algorithm,
                order = requestModel.sortingOrder
            )
        }
    }
    fun sortOneDimensionalDataSetFloat(
        requestModel: RequestModel<Float>
    ): ResponseModel<Float> {
        return if (requestModel.algorithm == null) {
            runAllAlgorithms<Float>(
                numOfIterations = requestModel.iterationNumber,
                property = requestModel.property,
                data = requestModel.data,
                order = requestModel.sortingOrder
            )
        } else {
            runAlgorithm<Float>(
                numOfIterations = requestModel.iterationNumber,
                property = requestModel.property,
                data = requestModel.data,
                algorithm = requestModel.algorithm,
                order = requestModel.sortingOrder
            )
        }
    }
    fun sortMultiDimensionalDataSet(
        requestModel: RequestModel<JSONObject>
    ) : ResponseModel<JSONObject> {
        return if (requestModel.algorithm == null) {
            runAllAlgorithms<JSONObject>(
                numOfIterations = requestModel.iterationNumber,
                property = requestModel.property,
                data = requestModel.data,
                order = requestModel.sortingOrder
            )
        } else {
            runAlgorithm<JSONObject>(
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
    ): ResponseModel<T> {

        return ResponseModel(
            sortedData = ArrayList<T>(),
            property = property,
            sortingOrder = order ?: SortingOrder.ASCENDING,
            iterationNumber = numOfIterations ?: 0,
            algorithm = algorithm,
            time = null
        )
    }

    private fun <T> runAllAlgorithms(
        numOfIterations: Int?,
        order: SortingOrder?,
        property: String?,
        data: ArrayList<T>
    ): ResponseModel<T> {

        return ResponseModel<T>(
            sortedData = ArrayList<T>(),
            property = property,
            sortingOrder = order ?: SortingOrder.ASCENDING,
            iterationNumber = numOfIterations ?: 0,
            algorithm = null,
            time = null
        )
    }
}
