package pl.put.poznan.sorting_madness.logic.json_algorithms;


import com.google.gson.JsonArray;
import pl.put.poznan.sorting_madness.logic.algorithms.SortingOrder;

/**
 * This interface exposes methods to run the algorithm on json objects
 */
public interface JsonSortable {
     /**
      * Method that is used to run the algorithm on json data and measure execution time with a specified number of iterations
      * @param array array of json objects
      * @param attr attribute of the json object by which the algorithm will be executed on the list containing such objects
      * @param order soring order - ascending or descending
      * @return object containing sorted json array and execution time
      */
     SortedJsonDataResponse run(JsonArray array, String attr, SortingOrder order);

     /**
      * Method that is used to run the algorithm on json data and measure execution time
      * @param array array of json objects
      * @param attr attribute of the json object by which the algorithm will be executed on the list containing such objects
      * @param maxIterations maximum number of iterations of algorithm execution
      * @param order soring order - ascending or descending
      * @return object containing sorted json array and execution time
      */
     SortedJsonDataResponse run(JsonArray array, String attr, int maxIterations, SortingOrder order);
}
