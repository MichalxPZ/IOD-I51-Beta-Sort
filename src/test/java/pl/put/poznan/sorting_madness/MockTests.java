package pl.put.poznan.sorting_madness;



import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;

import org.springframework.test.web.servlet.MockMvc;
import pl.put.poznan.sorting_madness.app.SortingMadnessApplication;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
@ContextConfiguration(classes = SortingMadnessApplication.class)
class RegisterRestControllerTest {

    private static String requestJsonInt = "{\n" +
            "  \"algorithm\": \"BUBBLE_SORT\",\n" +
            "  \"data\": [\n" +
            "    0, 1, 2\n" +
            "  ],\n" +
            "  \"iterationNumber\": 0,\n" +
            "  \"property\": \"string\",\n" +
            "  \"sortingOrder\": \"ASCENDING\"\n" +
            "}";
    private static String requestJsonString = "{\n" +
            "  \"algorithm\": \"BUBBLE_SORT\",\n" +
            "  \"data\": [\n" +
            "    \"0\", \"1\", \"2\"\n" +
            "  ],\n" +
            "  \"iterationNumber\": 0,\n" +
            "  \"property\": \"string\",\n" +
            "  \"sortingOrder\": \"ASCENDING\"\n" +
            "}";
    private static String requestJsonFloat = "{\n" +
            "  \"algorithm\": \"BUBBLE_SORT\",\n" +
            "  \"data\": [\n" +
            "    \"0\", \"1\", \"2\"\n" +
            "  ],\n" +
            "  \"iterationNumber\": 0,\n" +
            "  \"property\": \"string\",\n" +
            "  \"sortingOrder\": \"ASCENDING\"\n" +
            "}";
    private static String requestJsonObjects = "{\n" +
            "  \"algorithm\": \"BUBBLE_SORT\",\n" +
            "  \"data\":  [{\"value\": \"123\"}],\n" +
            "  \"iterationNumber\": 0,\n" +
            "  \"property\": \"value\",\n" +
            "  \"sortingOrder\": \"ASCENDING\"\n" +
            "}";

    @Autowired
    private MockMvc mockMvc;

    @Test
    void whenValidInputInt_thenReturns200() throws Exception {
        mockMvc.perform(post("/api/sort/onedimension/int")
                        .contentType("application/json")
                .content(requestJsonInt))
                .andExpect(status().isOk());
    }
    @Test
    void whenValidInputString_thenReturns200() throws Exception {
        mockMvc.perform(post("/api/sort/onedimension/string")
                        .contentType("application/json")
                .content(requestJsonString))
                .andExpect(status().isOk());
    }
    @Test
    void whenValidInputFloat_thenReturns200() throws Exception {
        mockMvc.perform(post("/api/sort/onedimension/float")
                        .contentType("application/json")
                .content(requestJsonFloat))
                .andExpect(status().isOk());
    }
    @Test
    void whenValidInputObjects_thenReturns200() throws Exception {
        mockMvc.perform(post("/api/sort/multiDimension")
                        .contentType("application/json")
                .content(requestJsonObjects))
                .andExpect(status().isOk());
    }
    @Test
    void whenInValidInputInt_thenReturns400() throws Exception {
        mockMvc.perform(post("/api/sort/onedimension/int")
                        .contentType("application/json"))
                .andExpect(status().isBadRequest());
    }
    @Test
    void whenInValidInputString_thenReturns400() throws Exception {
        mockMvc.perform(post("/api/sort/onedimension/string")
                        .contentType("application/json"))
                .andExpect(status().isBadRequest());
    }
    @Test
    void whenInValidInputFloat_thenReturns400() throws Exception {
        mockMvc.perform(post("/api/sort/onedimension/float")
                        .contentType("application/json"))
                .andExpect(status().isBadRequest());
    }
    @Test
    void whenInvalidInputObjects_thenReturns400() throws Exception {
        mockMvc.perform(post("/api/sort/multiDimension")
                        .contentType("application/json"))
                .andExpect(status().isBadRequest());
    }
    @Test
    void whenInvalidEndpoint_thenReturns404() throws Exception {
        mockMvc.perform(post("/xxx")
                        .contentType("application/json"))
                .andExpect(status().isNotFound());
    }
    @Test
    void whenIvalidContentTypeBadRequest() throws Exception {
        mockMvc.perform(post("/api/sort/onedimension/float")
                        .contentType("text/html")
                        .content(requestJsonFloat))
                .andExpect(status().isUnsupportedMediaType());
    }
}

