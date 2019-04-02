package pt.isel.daw.g8.projectmanager.middleware

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter
import pt.isel.daw.g8.projectmanager.exceptions.NotAuthenticatedException
import pt.isel.daw.g8.projectmanager.repository.UserInfoRepo
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthenticationInterceptor : HandlerInterceptorAdapter() {

    @Autowired lateinit var userRepo : UserInfoRepo

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        (handler as? HandlerMethod)?.method?.let {m ->
            if (m.isAnnotationPresent(RequiresAuthentication::class.java)
                    || m.declaringClass.isAnnotationPresent(RequiresAuthentication::class.java)){
                val authentication = request.getHeader("Authorization")
                        ?: throw NotAuthenticatedException()

                val auth = String(Base64
                                .getDecoder()
                                .decode(authentication))
                        .removePrefix("Basic")
                        .trim().split(":")

                val username = auth[0]
                val password = auth[1]

                val user = userRepo.findById(username)
                if (!user.isPresent || user.get().password != password)
                    throw NotAuthenticatedException()

                request.setAttribute("username", username)
            }
        }

        return true
    }
}