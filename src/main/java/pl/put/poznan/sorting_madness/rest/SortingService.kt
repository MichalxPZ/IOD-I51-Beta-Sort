package pl.put.poznan.sorting_madness.rest

import com.google.gson.JsonObject
import org.springframework.stereotype.Service
import pl.put.poznan.sorting_madness.rest.model.*
import java.util.*

@Service
class SortingService {

    fun sortOneDimensionalDataSetString(
        requestModel: RequestModel<String>
    ): ResponseModel<String> {
        return callAlgorithms(requestModel)
    }
    fun sortOneDimensionalDataSetInt(
        requestModel: RequestModel<Int>
    ): ResponseModel<Int> {
        return callAlgorithms(requestModel)
    }
    fun sortOneDimensionalDataSetFloat(
        requestModel: RequestModel<Float>
    ): ResponseModel<Float> {
        return callAlgorithms(requestModel)

    }
    fun sortMultiDimensionalDataSet(
        requestModel: RequestModel<JsonObject>
    ) : ResponseModel<JsonObject> {
        return callAlgorithms(requestModel)
    }

    private fun <T> callAlgorithms(requestModel: RequestModel<T>): ResponseModel<T> {
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
    ): ResponseModel<T> {
        return ResponseModel(
            sortedData = data,
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
        return ResponseModel(
            sortedData = data,
            property = property,
            sortingOrder = order ?: SortingOrder.ASCENDING,
            iterationNumber = numOfIterations ?: 0,
            algorithm = null,
            time = null
        )
    }

}
