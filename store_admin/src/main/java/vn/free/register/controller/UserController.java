package vn.free.register.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.free.register.constant.ResponseCode;
import vn.free.register.request.CourseSearch;
import vn.free.register.request.UserSearch;
import vn.free.register.request.user.NewUserRQ;
import vn.free.register.response.ResponseDTO;
import vn.free.register.service.CourseService;
import vn.free.register.service.UserService;
import vn.free.register.validate.CourseValidate;

/**
 * @author quangnv
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;


    @PostMapping("/search")
    public ResponseEntity<Object> searchUser(@RequestBody UserSearch userSearch) {

        log.info("Call api searchCourse");


        return ResponseEntity.ok().body(
                ResponseDTO.builder()
                        .data(userService.searchUser(userSearch))
                        .code(ResponseCode.SUCCESS.getCode())
                        .message(ResponseCode.SUCCESS.getDesc())
                        .build()
        );

    }

    @PostMapping("/create")
    public ResponseEntity<Object> createUser(@RequestBody NewUserRQ newUserRQ) {

        log.info("Call api create user");
        return ResponseEntity.ok().body(
                userService.createUser(newUserRQ));

    }

    @PostMapping("/update")
    public ResponseEntity<Object> updateUser(@RequestBody NewUserRQ newUserRQ) {

        log.info("Call api update user");
        return ResponseEntity.ok().body(
                userService.updateUser(newUserRQ));

    }

    @PostMapping("/update-status")
    public ResponseEntity<Object> updateStatusUser(@RequestBody NewUserRQ newUserRQ) {

        log.info("Call api update user");
        return ResponseEntity.ok().body(
                userService.updateStatusUser(newUserRQ));

    }


}
