package vn.free.register.controller;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.free.register.request.user.UserSearch;
import vn.free.register.request.user.UserRQ;
import vn.free.register.response.ActionRes;
import vn.free.register.response.ResponseDTO;
import vn.free.register.service.UserService;

/**
 * @author quangnv
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    private static Gson gson = new Gson();

    @PostMapping("/search")
    public ResponseEntity<Object> searchUser(@RequestBody UserSearch userSearch) {

        log.info("Input search user: {}", gson.toJson(userSearch));
        ResponseDTO response = userService.searchUser(userSearch);
        log.info("Output search user: {}", gson.toJson(response));
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/get-by-id")
    public ResponseEntity<Object> getByIdUser(@RequestBody UserRQ userRQ) {

        log.info("Input get by id user: {}", gson.toJson(userRQ));
        ResponseDTO response = userService.getUserById(userRQ);
        log.info("Output get by id user: {}", gson.toJson(response));
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createUser(@RequestBody UserRQ userRQ) {

        log.info("Input create user: {}", gson.toJson(userRQ));
        ActionRes response = userService.createUser(userRQ);
        log.info("Output create user: {}", gson.toJson(response));
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/update")
    public ResponseEntity<Object> updateUser(@RequestBody UserRQ userRQ) {

        log.info("Input update user: {}", gson.toJson(userRQ));
        ActionRes response = userService.updateUser(userRQ);
        log.info("Output update user: {}", gson.toJson(response));
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/update-status")
    public ResponseEntity<Object> updateStatusUser(@RequestBody UserRQ userRQ) {

        log.info("Input update status user: {}", gson.toJson(userRQ));
        ActionRes response = userService.updateStatusUser(userRQ);
        log.info("Output update status user: {}", gson.toJson(response));
        return ResponseEntity.ok().body(response);
    }


}
