package dev.t1dmlgus.userservice;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class UserController {

    private final Environment env;

    public UserController(Environment env) {
        this.env = env;
    }

    @GetMapping("/health-check")
    public String status(){
        return env.getProperty("greeting.message");
    }
}
