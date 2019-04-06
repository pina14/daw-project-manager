package pt.isel.daw.g8.projectmanager.middleware

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter
import pt.isel.daw.g8.projectmanager.ProjectPaths
import pt.isel.daw.g8.projectmanager.model.errorModel.errorRepresentations.BadRequestException
import pt.isel.daw.g8.projectmanager.model.errorModel.errorRepresentations.NotAuthenticatedException
import pt.isel.daw.g8.projectmanager.model.errorModel.errorRepresentations.WrongCredentialsException
import pt.isel.daw.g8.projectmanager.repository.UserInfoRepo
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthenticationInterceptor : HandlerInterceptorAdapter() {

    @Autowired lateinit var userRepo : UserInfoRepo

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        (handler as? HandlerMethod)?.method?.let {m ->
            if (m.isAnnotationPresent(RequiresAuthentication::class.java)
                    || m.declaringClass.isAnnotationPresent(RequiresAuthentication::class.java)){
                val authentication = request.getHeader("Authorization")
                        ?: throw NotAuthenticatedException()

                val auth : List<String>
                val authToDecode = authentication.removePrefix("Basic").trim()
                try {
                    auth = String(Base64
                            .getDecoder()
                            .decode(authToDecode))
                            .trim()
                            .split(":")
                } catch (ex : IllegalArgumentException) {
                    throw BadRequestException("Authentication has to encoded in Base64 format.")
                }

                if(authToDecode.isEmpty() || auth.size != 2)
                    throw BadRequestException("Authentication header has to be in format 'Basic <username>:<password>'.")

                val username = auth[0]
                val password = auth[1]

                val user = userRepo.findById(username)
                if (!user.isPresent || user.get().password != password)
                    throw WrongCredentialsException()

                request.setAttribute(ProjectPaths.USERNAME_VAR, username)
            }
        }

        return true
    }
}