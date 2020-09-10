package edu.books.controller.api.auth;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.books.base.AbstractController;
import edu.books.config.JwtTokenUtil;
import edu.books.constant.CommonConstants;
import edu.books.constant.UrlConstants;
import edu.books.domain.User;
import edu.books.model.JwtRequest;
import edu.books.model.JwtResponse;
import edu.books.model.ResponseAPI;
import edu.books.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping(UrlConstants.URI_API + UrlConstants.URI_ADMIN)
@Api(value = "api.user", description = "User API")
public class APIAuthController extends AbstractController {
	
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    @Qualifier("userDetailsService")
    private UserDetailsService userDetailsService;
    
    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UserService userService;


    @SuppressWarnings({"unchecked", "rawtypes"})
	@PostMapping(value =  UrlConstants.URL_AUTH_LOGIN)
    @ApiOperation(value = "Login", response = Object.class)
    public ResponseAPI search(@RequestBody JwtRequest authenticationRequest,
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestHeader(value = "Authorization", required = false, defaultValue = "") String authorization) throws NoSuchMessageException, Exception {
        log.info("Call API login");
        User user = userService.findByEmailAndPassword(authenticationRequest.getEmail(), authenticationRequest.getPassword());
        if (user == null) {
            throw new Exception(messageSource.getMessage("api.admin.login.wrong", new String[]{}, null), null);
        }
        if(CommonConstants.DEACTIVE.equals(user.getEnabled())) {
        	 throw new Exception(messageSource.getMessage("api.admin.login.disabled", new String[]{}, null), null);
        }
        
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        final String token = jwtTokenUtil.generateToken(userDetails);
        
        JwtResponse jwtResponse = new JwtResponse(token);
        String accessToken = jwtResponse.getToken();
        //Update token when login success
        userService.updateToken(accessToken, authenticationRequest.getEmail());
		HashMap resultLogin = new HashMap();
        resultLogin.put("access_token", accessToken);
        user.setConfirmPassword("");
        user.setPassword("");
        resultLogin.put("user", user);
        request.getSession().setAttribute("userInfo", user);
        ResponseAPI responseAPI = new ResponseAPI(HttpStatus.SC_OK, "OK", resultLogin);
        return responseAPI;
    }
    
    @RequestMapping(value = UrlConstants.URL_AUTH_LOGOUT, method = RequestMethod.GET)
    public ResponseAPI logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        if(session.getAttribute("userInfo") != null) {
        	User user =  (User)session.getAttribute("userInfo");
        	userService.updateToken(null, user.getEmail());
        }
    	session.removeAttribute("userInfo");
        //session.removeAttribute("roles");
        ResponseAPI responseAPI = new ResponseAPI(HttpStatus.SC_OK, "OK", null);
        return responseAPI;
    }

}

