package vn.free.register.controller;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.free.register.response.ResponseDTO;
import vn.free.register.service.GroupRoleService;

/**
 * @author quangnv
 */
@Slf4j
@RestController
@RequestMapping("/group-role")
public class GroupRoleController {


    @Autowired
    private GroupRoleService groupRoleService;

    private static Gson gson = new Gson();

    @PostMapping("/get-all")
    public ResponseEntity<Object> getAllGrRole() {

        log.info("Input get all group role");
        ResponseDTO response = groupRoleService.getAllGrRole();
        log.info("Output get all group role: {}", gson.toJson(response));
        return ResponseEntity.ok().body(response);
    }


}
