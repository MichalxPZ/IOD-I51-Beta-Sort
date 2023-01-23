package pl.put.poznan.sorting_madness.rest.webConfig

import com.google.gson.*
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.GsonHttpMessageConverter
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import springfox.documentation.spring.web.json.Json
import java.lang.reflect.Type


@EnableWebMvc
@Configuration
@ComponentScan("pl.put.poznan")
open class WebConfig: WebMvcConfigurer {
    override fun configureMessageConverters(messageConverters: MutableList<HttpMessageConverter<*>?>) {
        val gsonHttpMessageConverter = GsonHttpMessageConverter()
        gsonHttpMessageConverter.gson = gson
        messageConverters.add(gsonHttpMessageConverter)
    }

    private val gson = GsonBuilder()
        .registerTypeAdapter(Json::class.java, SpringfoxJsonToGsonAdapter())
        .create()

    private class SpringfoxJsonToGsonAdapter : JsonSerializer<Json?> {

        override fun serialize(json: Json?, type: Type?, context: JsonSerializationContext?): JsonElement {
            val parser = JsonParser()
            return parser.parse(json?.value())
        }
    }

}