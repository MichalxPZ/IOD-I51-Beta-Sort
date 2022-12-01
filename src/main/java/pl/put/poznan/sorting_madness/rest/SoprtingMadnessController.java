package pl.put.poznan.sorting_madness.rest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;


@RestController
@RequestMapping("/{text}")
public class SoprtingMadnessController {

    private static final Logger logger = LoggerFactory.getLogger(pl.put.poznan.sorting_madness.rest.SoprtingMadnessController.class);

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public String get(@PathVariable String text,
                              @RequestParam(value="transforms", defaultValue="upper,escape") String[] transforms) {

        // log the parameters
        logger.debug(text);
        logger.debug(Arrays.toString(transforms));

        // perform the transformation, you should run your logic here, below is just a silly example
        pl.put.poznan.sorting_madness.logic.SortingMadness transformer = new pl.put.poznan.sorting_madness.logic.SortingMadness(transforms);
        return transformer.transform(text);
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public String post(@PathVariable String text,
                      @RequestBody String[] transforms) {

        // log the parameters
        logger.debug(text);
        logger.debug(Arrays.toString(transforms));

        // perform the transformation, you should run your logic here, below is just a silly example
        pl.put.poznan.sorting_madness.logic.SortingMadness transformer = new pl.put.poznan.sorting_madness.logic.SortingMadness(transforms);
        return transformer.transform(text);
    }



}


