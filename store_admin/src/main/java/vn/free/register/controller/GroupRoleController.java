package vn.free.register.controller;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.free.register.request.UserSearch;
import vn.free.register.request.user.NewUserRQ;
import vn.free.register.response.ActionRes;
import vn.free.register.response.ResponseDTO;
import vn.free.register.service.UserService;

/**
 * @author quangnv
 */
@Slf4j
@RestController
@RequestMapping("/group-role")
public class GroupRoleController {


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

    @PostMapping("/create")
    public ResponseEntity<Object> createUser(@RequestBody NewUserRQ newUserRQ) {

        log.info("Input create user: {}", gson.toJson(newUserRQ));
        ActionRes response = userService.createUser(newUserRQ);
        log.info("Output create user: {}", gson.toJson(response));
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/update")
    public ResponseEntity<Object> updateUser(@RequestBody NewUserRQ newUserRQ) {

        log.info("Input update user: {}", gson.toJson(newUserRQ));
        ActionRes response = userService.updateUser(newUserRQ);
        log.info("Output update user: {}", gson.toJson(response));
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/update-status")
    public ResponseEntity<Object> updateStatusUser(@RequestBody NewUserRQ newUserRQ) {

        log.info("Input update status user: {}", gson.toJson(newUserRQ));
        ActionRes response = userService.updateStatusUser(newUserRQ);
        log.info("Output update status user: {}", gson.toJson(response));
        return ResponseEntity.ok().body(response);
    }


}
