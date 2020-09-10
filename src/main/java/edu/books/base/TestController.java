package edu.books.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @ResponseBody
    @GetMapping("get-password")
    public String index() {
    	System.out.println(bCryptPasswordEncoder.matches("1234567", "$2a$10$LE5.7QPid.uX.DFIlksh0uARwss2x6.7rhUTaU8FidestPRpc9nr6"));
        return "Test";
    }
}
