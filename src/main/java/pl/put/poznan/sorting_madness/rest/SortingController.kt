package pl.put.poznan.sorting_madness.rest

import org.json.JSONObject
import org.json.JSONString
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import pl.put.poznan.sorting_madness.rest.model.RequestModel
import pl.put.poznan.sorting_madness.rest.model.ResponseModel

@RestController
class SortingController(
    private val sortingService: SortingService
) {

    private val logger: Logger = LoggerFactory.getLogger(SortingController::class.java)

    @PostMapping("/api/sort/onedimension/string")
    private fun sortOneDimensionalDataSetString(
        @RequestBody requestModel: RequestModel<String>
    ): ResponseModel<String> {
        logger.debug("\nSorting provided data set:" + "${requestModel.data}\n"
                + "with algorithm: " + requestModel.algorithm + "\n" +
                "num of iterations: " + requestModel.iterationNumber + "\n" +
                "sorting order: " + requestModel.sortingOrder + "\n"
        )
        return sortingService.sortOneDimensionalDataSetString(requestModel)
    }
    @PostMapping("/api/sort/onedimension/int")
    private fun sortOneDimensionalDataSetInt(
        @RequestBody requestModel: RequestModel<Int>
    ): ResponseModel<Int> {
        logger.debug("\nSorting provided data set:" + "${requestModel.data}\n"
                + "with algorithm: " + requestModel.algorithm + "\n" +
                "num of iterations: " + requestModel.iterationNumber + "\n" +
                "sorting order: " + requestModel.sortingOrder + "\n"
        )
        return sortingService.sortOneDimensionalDataSetInt(requestModel)
    }
    @PostMapping("/api/sort/onedimension/float")
    private fun sortOneDimensionalDataSetFloat(
        @RequestBody requestModel: RequestModel<Float>
    ): ResponseModel<Float> {
        logger.debug("\nSorting provided data set: " + "${requestModel.data}\n"
                + "with algorithm: " + requestModel.algorithm + "\n" +
                "num of iterations: " + requestModel.iterationNumber + "\n" +
                "sorting order: " + requestModel.sortingOrder + "\n"
        )
        return sortOneDimensionalDataSetFloat(requestModel)
    }
    @PostMapping("/api/sort/multiDimension")
    private fun sortMultiDimensionalDataSet(
        @RequestBody requestModel: RequestModel<JSONObject>
    ) : ResponseModel<JSONObject> {
        logger.debug("\nSorting provided data set:" + "${requestModel.data}\n"
                + "with algorithm: " + requestModel.algorithm + "\n" +
                "num of iterations: " + requestModel.iterationNumber + "\n" +
                "sorting order: " + requestModel.sortingOrder + "\n" +
                 "property: " + requestModel.property
        )
        return sortingService.sortMultiDimensionalDataSet(requestModel)
    }
}