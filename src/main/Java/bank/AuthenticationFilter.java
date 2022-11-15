package bank;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthenticationFilter extends HandlerInterceptorAdapter implements HandlerInterceptor {

    //TODO:
    // add userRepo
    // add AuthController
    // List<String> whitelist?
    // boolean to check if isWhiteListed
    // preHandle method to work before everything

}
