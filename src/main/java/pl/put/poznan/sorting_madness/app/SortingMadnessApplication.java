package pl.put.poznan.sorting_madness.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"pl.put.poznan.sorting_madness.rest"})
public class SortingMadnessApplication {

    public static void main(String[] args) {
        SpringApplication.run(pl.put.poznan.sorting_madness.app.SortingMadnessApplication.class, args);
    }
}
