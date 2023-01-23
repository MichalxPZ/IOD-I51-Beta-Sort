package pl.put.poznan.sorting_madness.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The SortingMadnessApplication is the main class of the application. It is responsible for starting the application.
 * It uses the {@link SpringApplication} to run the application, which will scan the base package and its sub-packages
 * for components, configurations and other classes.
 */
@SpringBootApplication(scanBasePackages = "pl.put.poznan.sorting_madness.*")
public class SortingMadnessApplication {

    /**
     * The main method of the application. It starts the application by calling {@link SpringApplication#run(Class, String...)}.
     * @param args the arguments passed to the application
     */
    public static void main(String[] args) {
        SpringApplication.run(pl.put.poznan.sorting_madness.app.SortingMadnessApplication.class, args);
    }
}
