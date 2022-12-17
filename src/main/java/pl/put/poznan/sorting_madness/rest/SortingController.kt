package pl.put.poznan.sorting_madness.rest

import org.json.JSONObject
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import pl.put.poznan.sorting_madness.rest.model.RequestModel
import pl.put.poznan.sorting_madness.rest.model.ResponseModel

@RestController
class SortingController(
    private val sortingService: SortingService
) {

    @GetMapping("/api/sort/onedimension/string")
    private fun sortOneDimensionalDataSetString(
        @RequestBody requestModel: RequestModel<String>
    ): ResponseModel<String> {
        return sortingService.sortOneDimensionalDataSetString(requestModel)
    }
    @GetMapping("/api/sort/onedimension/int")
    private fun sortOneDimensionalDataSetInt(
        @RequestBody requestModel: RequestModel<Int>
    ): ResponseModel<Int> {
        return sortingService.sortOneDimensionalDataSetInt(requestModel)
    }
    @GetMapping("/api/sort/onedimension/float")
    private fun sortOneDimensionalDataSetFloat(
        @RequestBody requestModel: RequestModel<Float>
    ): ResponseModel<Float> {
        return sortOneDimensionalDataSetFloat(requestModel)
    }
    @GetMapping("/api/sort/multiDimension")
    private fun sortMultiDimensionalDataSet(
        @RequestBody requestModel: RequestModel<JSONObject>
    ) : ResponseModel<JSONObject> {
        return sortingService.sortMultiDimensionalDataSet(requestModel)
    }
}