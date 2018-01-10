package pflb.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pflb.entity.UserForLogin;

@RestController
public class UserController {

    int ResultCode;

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public int postAuthUser(@RequestBody UserForLogin user) {
        //sql запрос на аутентификацию
        return ResultCode;
    }

    @RequestMapping(value = "/user/session/{id}", method = RequestMethod.GET)
    public int getUserInfo(@PathVariable String id){
        //sql запрос на получение информации о пользователе
        return ResultCode;
    }

    @RequestMapping(value = "/auth/session/{id}", method = RequestMethod.DELETE)
    public int deleteSession(@PathVariable String id){
        //sql запрос на удалене.
        return ResultCode;
    }


}
