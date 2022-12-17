package pl.put.poznan.sorting_madness.rest.webConfig

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.GsonHttpMessageConverter
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@EnableWebMvc
@Configuration
@ComponentScan("pl.put.poznan")
open class WebConfig: WebMvcConfigurer {
    override fun configureMessageConverters(messageConverters: MutableList<HttpMessageConverter<*>?>) {
        messageConverters.add(GsonHttpMessageConverter())
    }
}