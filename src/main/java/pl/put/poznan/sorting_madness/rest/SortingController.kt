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

/**
 * This class is a controller for handling sorting requests and returning sorted data.
 * @param sortingService the sorting service instance that will be used to sort the data.
 */
@RestController
class SortingController(
    private val sortingService: SortingService
) {

    /**
     * Instance if logger for logging execution at DEBUG and INFO levels
     */
    private val logger: Logger = LoggerFactory.getLogger(SortingController::class.java)

  /**
     * Endpoint for sorting one-dimensional string data.
     * @param requestModel the request model containing the data to be sorted, the sorting algorithm to be used,
     * the number of iterations, the sorting order and the property to be sorted by.
     * @return a list of response models, containing the sorted data, the property used for sorting, the sorting order,
     * the number of iterations, the sorting algorithm used and the time it took to sort the data.
     */    @PostMapping("/api/sort/onedimension/string")
    private fun sortOneDimensionalDataSetString(
        @RequestBody requestModel: RequestModel<String>
    ): ArrayList<ResponseModel<String>> {
        logRequestDetails(requestModel)
        return sortingService.sortOneDimensionalDataSetString(requestModel)
    }

 /**
     * Endpoint for sorting one-dimensional integer data.
     * @param requestModel the request model containing the data to be sorted, the sorting algorithm to be used,
     * the number of iterations, the sorting order and the property to be sorted by.
     * @return a list of response models, containing the sorted data, the property used for sorting, the sorting order,
     * the number of iterations, the sorting algorithm used and the time it took to sort the data.
     */    @PostMapping("/api/sort/onedimension/int")
    private fun sortOneDimensionalDataSetInt(
        @RequestBody requestModel: RequestModel<Int>
    ): ArrayList<ResponseModel<Int>> {
        logRequestDetails(requestModel)
        return sortingService.sortOneDimensionalDataSetInt(requestModel)
    }

   /**
     * Endpoint for sorting one-dimensional float data.
     * @param requestModel the request model containing the data to be sorted, the sorting algorithm to be used,
     * the number of iterations, the sorting order and the property to be sorted by.
     * @return a list of response models, containing the sorted data, the property used for sorting, the sorting order,
     * the number of iterations, the sorting algorithm used and the time it took to sort the data.
     */    @PostMapping("/api/sort/onedimension/float")
    private fun sortOneDimensionalDataSetFloat(
        @RequestBody requestModel: RequestModel<Float>
    ): ArrayList<ResponseModel<Float>> {
        logRequestDetails(requestModel)
        return sortingService.sortOneDimensionalDataSetFloat(requestModel)
    }

   /**
     * Endpoint for sorting multi-dimensional data.
     * @param requestModel the request model containing the data to be sorted, the sorting algorithm to be used,
     * the number of iterations, the sorting order and the property to be sorted by.
     * @return a list of response models, containing the sorted data, the property used for sorting, the sorting order,
     * the number of iterations, the sorting algorithm used and the time it took to sort the data.
     */    @PostMapping("/api/sort/multiDimension")
    private fun sortMultiDimensionalDataSet(
        @RequestBody requestModel: RequestJsonModel
    ): ArrayList<ResponseModel<JsonArray>> {
        logJsonRequestDetails(requestModel)
        return sortingService.sortMultiDimensionalDataSet(requestModel)
    }

    /**
     * Logs the details of a given request model.
     * @param requestModel the request model whose details will be logged
     */    private fun <T> logRequestDetails(requestModel: RequestModel<T>) {
        logger.debug(
            "\nSorting provided data set:" + "${requestModel.data}\n"
                    + "with algorithm: " + requestModel.algorithm + "\n" +
                    "num of iterations: " + requestModel.iterationNumber + "\n" +
                    "sorting order: " + requestModel.sortingOrder + "\n"
        )
    }

  /**
     * Logs the details of a JSON request model.
     * @param requestModel the request model to log
     */    private fun logJsonRequestDetails(requestModel: RequestJsonModel) {
        logger.debug(
            "\nSorting provided data set:" + "${requestModel.data}\n"
                    + "with algorithm: " + requestModel.algorithm + "\n" +
                    "num of iterations: " + requestModel.iterationNumber + "\n" +
                    "sorting order: " + requestModel.sortingOrder + "\n"
        )
    }
}