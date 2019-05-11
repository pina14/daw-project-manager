package pt.isel.daw.g8.projectmanager.middleware

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class MvcConfig : WebMvcConfigurer {

    @Autowired
    private lateinit var authInterceptor: AuthenticationInterceptor

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
    }

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(authInterceptor)
    }
}