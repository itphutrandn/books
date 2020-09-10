package edu.books.security;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import edu.books.exception.PermissionException;

/**
 * Created by Admin on 10/10/2019.
 */

@Component("accessManager")
public class AccessManagerHandler {
    @Autowired
    private MessageSource messageSource;

    @Autowired
    private HttpServletRequest request;

    public void validateRoles(String... roles) throws PermissionException {
        List<String> authorities = (List<String>) request.getSession().getAttribute("roles");
        if (authorities != null) {
            for (String role : roles) {
                if (authorities.contains(role)) {
                    return;
                }
            }
        }
        throw new PermissionException(messageSource.getMessage("api.permission.fail", new String[]{}, null));
    }
}