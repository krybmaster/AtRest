package pflb.controller;

import pflb.entity.UserForLogin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class UserController {

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public UserForLogin login(@RequestBody UserForLogin user) {

        return null;
    }
}
