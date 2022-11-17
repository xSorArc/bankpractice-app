package bank;

import bank.controllers.AuthenticationController;
import bank.data.UserRepository;
import bank.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AuthenticationFilter extends HandlerInterceptorAdapter implements HandlerInterceptor {

    @Autowired
    private UserRepository userRepository;

    @Autowired // Needed for method getUserFromSession()
    private AuthenticationController authenticationController;

    // Whitelist: List of items NOT subject to restriction
    private static final List<String> whitelist = Arrays.asList("/login", "/register", "/logout", "/css", "/about");

    // Takes a string representing a URL path and checks to see if it starts with any entry from whitelist.
    private static boolean isWhiteListed(String path) {
        for (String pathRoot : whitelist) {
            if (path.startsWith(pathRoot)) { // More restrictive would be to use equals()
                return true; // Is whitelisted
            }
        }
        return false; // Not whitelisted
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws IOException {

        // Allows (not signed-in)user to access the page if whitelisted.
        if (isWhiteListed(request.getRequestURI())) {
            return true; // Request may proceed if returns true.
        }

        // Retrieves the user session, which is contained in the request.
        HttpSession session = request.getSession();
        // Retrieves the User obj corresponding to the given user. This will be null if the user is not logged in.
        User user = authenticationController.getUserFromSession(session);

        if (user != null) { // User is not null, so user is logged in.
            return true; // Request processing will continue as normal & call the correct controller.
        }

        response.sendRedirect("/login"); // User is null, redirection to login page.
        return false; // Proceeding will halt and no controller will be called.
    }
}
