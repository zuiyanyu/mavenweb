package springbootstu.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springbootstu.domain.UserEntity;

@RestController
@RequestMapping("converter")
public class UserEntityController {

    @GetMapping("/user")
    public UserEntity test(UserEntity user) {
        return user;
    }
}
