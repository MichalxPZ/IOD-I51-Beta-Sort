package pl.put.poznan.sorting_madness.rest

import com.google.gson.JsonArray
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import pl.put.poznan.sorting_madness.rest.model.RequestJsonModel
import pl.put.poznan.sorting_madness.rest.model.RequestModel
import pl.put.poznan.sorting_madness.rest.model.ResponseModel
import java.util.*


@RestController
class SortingController(
    private val sortingService: SortingService
) {

    private val logger: Logger = LoggerFactory.getLogger(SortingController::class.java)

    @PostMapping("/api/sort/onedimension/string")
    private fun sortOneDimensionalDataSetString(
        @RequestBody requestModel: RequestModel<String>
    ): ArrayList<ResponseModel<String>> {
        logRequestDetails(requestModel)
        return sortingService.sortOneDimensionalDataSetString(requestModel)
    }

    @PostMapping("/api/sort/onedimension/int")
    private fun sortOneDimensionalDataSetInt(
        @RequestBody requestModel: RequestModel<Int>
    ): ArrayList<ResponseModel<Int>> {
        logRequestDetails(requestModel)
        return sortingService.sortOneDimensionalDataSetInt(requestModel)
    }

    @PostMapping("/api/sort/onedimension/float")
    private fun sortOneDimensionalDataSetFloat(
        @RequestBody requestModel: RequestModel<Float>
    ): ArrayList<ResponseModel<Float>> {
        logRequestDetails(requestModel)
        return sortingService.sortOneDimensionalDataSetFloat(requestModel)
    }

    @PostMapping("/api/sort/multiDimension")
    private fun sortMultiDimensionalDataSet(
        @RequestBody requestModel: RequestJsonModel
    ): ArrayList<ResponseModel<JsonArray>> {
        logJsonRequestDetails(requestModel)
        return sortingService.sortMultiDimensionalDataSet(requestModel)
    }

    private fun <T> logRequestDetails(requestModel: RequestModel<T>) {
        logger.debug(
            "\nSorting provided data set:" + "${requestModel.data}\n"
                    + "with algorithm: " + requestModel.algorithm + "\n" +
                    "num of iterations: " + requestModel.iterationNumber + "\n" +
                    "sorting order: " + requestModel.sortingOrder + "\n"
        )
    }

    private fun logJsonRequestDetails(requestModel: RequestJsonModel) {
        logger.debug(
            "\nSorting provided data set:" + "${requestModel.data}\n"
                    + "with algorithm: " + requestModel.algorithm + "\n" +
                    "num of iterations: " + requestModel.iterationNumber + "\n" +
                    "sorting order: " + requestModel.sortingOrder + "\n"
        )
    }
}