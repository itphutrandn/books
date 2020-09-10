package edu.books.controller.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.books.base.AbstractController;
import edu.books.constant.UrlConstants;
import edu.books.model.ResponseAPI;
import edu.books.model.UserRequest;
import edu.books.service.UserService;
import edu.books.util.ObjectUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping(UrlConstants.URI_API + UrlConstants.URI_ADMIN)
@Api(value = "api.user", description = "User API")
public class APIAdminUserController extends AbstractController {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UserService userService;


    @PostMapping(value =  UrlConstants.URL_USER)
    @ApiOperation(value = "List Users.", response = Object.class)
    public ResponseAPI search(@RequestBody UserRequest userRequest) {
        log.info("Call API search users");
        ObjectUtil.removeEmptyField(userRequest);

        return (ResponseAPI) new Object();
    }

}

