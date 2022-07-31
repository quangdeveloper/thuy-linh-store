package vn.free.register.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.free.register.constant.ResponseCode;
import vn.free.register.request.CourseSearch;
import vn.free.register.request.UserSearch;
import vn.free.register.request.teacher.TeacherNewRQ;
import vn.free.register.request.teacher.TeacherSearch;
import vn.free.register.request.user.NewUserRQ;
import vn.free.register.response.ResponseDTO;
import vn.free.register.service.CourseService;
import vn.free.register.service.TeacherService;
import vn.free.register.validate.CourseValidate;

/**
 * @author quangnv
 */
@Slf4j
@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @PostMapping("/find-all")
    public ResponseEntity<Object> searchTeacher() {

        return ResponseEntity.ok().body(
                ResponseDTO.builder()
                        .data(teacherService.findAll())
                        .code(ResponseCode.SUCCESS.getCode())
                        .message(ResponseCode.SUCCESS.getDesc())
                        .build()
        );

    }

    @PostMapping("/search")
    public ResponseEntity<Object> searchTeacher(@RequestBody TeacherSearch teacherSearch) {

        log.info("Call api searchCourse");

        return ResponseEntity.ok().body(
                ResponseDTO.builder()
                        .data(teacherService.searchTeacher(teacherSearch))
                        .code(ResponseCode.SUCCESS.getCode())
                        .message(ResponseCode.SUCCESS.getDesc())
                        .build()
        );

    }

    @PostMapping("/create")
    public ResponseEntity<Object> createTeacher(@RequestBody TeacherNewRQ newUserRQ) {

        log.info("Call api create user");
        return ResponseEntity.ok().body(
                teacherService.createTeacher(newUserRQ));

    }

    @PostMapping("/update")
    public ResponseEntity<Object> updateTeacher(@RequestBody TeacherNewRQ newUserRQ) {

        log.info("Call api update user");
        return ResponseEntity.ok().body(
                teacherService.updateTeacher(newUserRQ));

    }

    @PostMapping("/update-status")
    public ResponseEntity<Object> updateStatusTeacher(@RequestBody TeacherNewRQ newUserRQ) {

        log.info("Call api update user");
        return ResponseEntity.ok().body(
                teacherService.updateStatusTeacher(newUserRQ));

    }



    @GetMapping("/get-top")
    public ResponseEntity<Object> getTopTeacher() {

        return ResponseEntity.ok().body(
                ResponseDTO.builder()
                        .data(teacherService.getTop(6))
                        .code(ResponseCode.SUCCESS.getCode())
                        .message(ResponseCode.SUCCESS.getDesc())
                        .build()
        );

    }
}
