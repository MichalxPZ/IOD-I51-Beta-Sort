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
 * Class handles SortingService methods that must be exposed as a REST API,
 * and @RequestMapping will supply Spring with the base path for each of the underlying REST API methods.
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
     *  POST method that handles the request with unsorted list of data of String type
     *  @param request model containing list of unsorted data of String type
     *  @return list of sorted data of String type and execution time with additional information such as performed algorithm
     */
    @PostMapping("/api/sort/onedimension/string")
    private fun sortOneDimensionalDataSetString(
        @RequestBody requestModel: RequestModel<String>
    ): ArrayList<ResponseModel<String>> {
        logRequestDetails(requestModel)
        return sortingService.sortOneDimensionalDataSetString(requestModel)
    }

    /**
     *  POST method that handles the request with unsorted list of data of Int type
     *  @param request model containing list of unsorted data of Int type
     *  @return list of sorted data of Int type and execution time with additional information such as performed algorithm
     */
    @PostMapping("/api/sort/onedimension/int")
    private fun sortOneDimensionalDataSetInt(
        @RequestBody requestModel: RequestModel<Int>
    ): ArrayList<ResponseModel<Int>> {
        logRequestDetails(requestModel)
        return sortingService.sortOneDimensionalDataSetInt(requestModel)
    }

    /**
     *  POST method that handles the request with unsorted list of data of Float type
     *  @param request model containing list of unsorted data of Float type
     *  @return list of sorted data of Float type and execution time with additional information such as performed algorithm
     */
    @PostMapping("/api/sort/onedimension/float")
    private fun sortOneDimensionalDataSetFloat(
        @RequestBody requestModel: RequestModel<Float>
    ): ArrayList<ResponseModel<Float>> {
        logRequestDetails(requestModel)
        return sortingService.sortOneDimensionalDataSetFloat(requestModel)
    }

    /**
     *  POST method that handles the request with unsorted json array
     *  @param request model containing list of unsorted json objects
     *  @return list of sorted json objects and execution time with additional information such as performed algorithm
     */
    @PostMapping("/api/sort/multiDimension")
    private fun sortMultiDimensionalDataSet(
        @RequestBody requestModel: RequestJsonModel
    ): ArrayList<ResponseModel<JsonArray>> {
        logJsonRequestDetails(requestModel)
        return sortingService.sortMultiDimensionalDataSet(requestModel)
    }


    /**
     *  Method logs data from request model of primitive type such as String, Int, Float
     *  @param request model containing list of unsorted data, performed sorting algorithm, sorting order and number of iterations
     */
    private fun <T> logRequestDetails(requestModel: RequestModel<T>) {
        logger.debug(
            "\nSorting provided data set:" + "${requestModel.data}\n"
                    + "with algorithm: " + requestModel.algorithm + "\n" +
                    "num of iterations: " + requestModel.iterationNumber + "\n" +
                    "sorting order: " + requestModel.sortingOrder + "\n"
        )
    }

    /**
     *  Method logs data from request model of json objects
     *  @param request model containing list of unsorted json objects, performed sorting algorithm, sorting order and number of iterations
     */
    private fun logJsonRequestDetails(requestModel: RequestJsonModel) {
        logger.debug(
            "\nSorting provided data set:" + "${requestModel.data}\n"
                    + "with algorithm: " + requestModel.algorithm + "\n" +
                    "num of iterations: " + requestModel.iterationNumber + "\n" +
                    "sorting order: " + requestModel.sortingOrder + "\n"
        )
    }
}