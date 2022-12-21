package pl.put.poznan.sorting_madness.rest.swaggerConfig

import com.google.gson.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.json.Json
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.lang.reflect.Type


/**
 * Swagger configuration for Sorting Madness REST API.
 */
@Configuration
@EnableSwagger2
open class SwaggerConfiguration{
    /**
     * Creates a new Docket bean for Swagger.
     * @return the new Docket bean
     */
    @Bean
    open fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.ant("/api/**"))
            .build()
            .apiInfo(getApiInfo())
    }

    /**
     * Creates a new ApiInfo object for Swagger.
     * @return the new ApiInfo object
     */
    private fun getApiInfo(): ApiInfo {
        return ApiInfoBuilder()
            .version("1.0.0")
            .title("SORTING MADNESS - REST API")
            .description("Sorting Madness to sort any time of data you want")
            .build()
    }

}