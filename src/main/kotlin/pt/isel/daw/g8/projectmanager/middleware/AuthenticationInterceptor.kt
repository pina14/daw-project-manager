package pt.isel.daw.g8.projectmanager.middleware

import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthenticationInterceptor : HandlerInterceptorAdapter() {

    //TODO implement all authentication rules
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {

        (handler as? HandlerMethod)?.method?.let {m ->
            if (m.isAnnotationPresent(RequiresAuthentication::class.java)
                    || m.declaringClass.isAnnotationPresent(RequiresAuthentication::class.java)){
                return false
            }
        }

        return true
    }
}