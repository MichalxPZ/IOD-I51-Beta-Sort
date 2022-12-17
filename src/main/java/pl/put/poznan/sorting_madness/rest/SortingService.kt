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
                data = requestModel.data
            )
        } else {
            runAlgorithm<String>(
                numOfIterations = requestModel.iterationNumber,
                property = requestModel.property,
                data = requestModel.data,
                algorithm = requestModel.algorithm
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
                data = requestModel.data
            )
        } else {
            runAlgorithm<Int>(
                numOfIterations = requestModel.iterationNumber,
                property = requestModel.property,
                data = requestModel.data,
                algorithm = requestModel.algorithm
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
                data = requestModel.data
            )
        } else {
            runAlgorithm<Float>(
                numOfIterations = requestModel.iterationNumber,
                property = requestModel.property,
                data = requestModel.data,
                algorithm = requestModel.algorithm
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
                data = requestModel.data
            )
        } else {
            runAlgorithm<JSONObject>(
                numOfIterations = requestModel.iterationNumber,
                property = requestModel.property,
                data = requestModel.data,
                algorithm = requestModel.algorithm
            )
        }
    }

    private fun <T> runAlgorithm(
        algorithm: Algorithm,
        numOfIterations: Int?,
        order: SortingOrder = SortingOrder.ASCENDING,
        property: String?,
        data: ArrayList<T> ): ResponseModel<T> {

        return ResponseModel<T>(
            sortedData = ArrayList(),
            property = property,
            sortingOrder = order,
            iterationNumber = 2,
            algorithm = algorithm,
            time = null
        )
    }

    private fun <T> runAllAlgorithms(
        numOfIterations: Int?,
        order: SortingOrder = SortingOrder.ASCENDING,
        property: String?,
        data: ArrayList<T>
    ): ResponseModel<T> {

        return ResponseModel<T>(
            sortedData = ArrayList(),
            property = property,
            sortingOrder = order,
            iterationNumber = 2,
            algorithm = null,
            time = null
        )
    }
}
