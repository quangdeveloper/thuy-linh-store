package vn.free.register.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.free.register.constant.ResponseCode;
import vn.free.register.request.CourseSearch;
import vn.free.register.request.course.CourseNewRQ;
import vn.free.register.response.ResponseDTO;
import vn.free.register.service.CourseService;

/**
 * @author quangnv
 */
@Slf4j
@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/search")
    public ResponseEntity<Object> searchCourse(@RequestBody CourseSearch courseSearch) {

        log.info("Call api searchCourse");

        return ResponseEntity.ok().body(
                ResponseDTO.builder()
                        .data(courseService.searchCourse(courseSearch))
                        .code(ResponseCode.SUCCESS.getCode())
                        .message(ResponseCode.SUCCESS.getDesc())
                        .build()
        );

    }


    @PostMapping("/create")
    public ResponseEntity<Object> createCourse(@RequestBody CourseNewRQ courseNewRQ) {

        log.info("Call api create Course");
        return ResponseEntity.ok().body(
                courseService.createCourse(courseNewRQ));

    }

    @PostMapping("/update")
    public ResponseEntity<Object> updateCourse(@RequestBody CourseNewRQ courseNewRQ) {

        log.info("Call api update Course");
        return ResponseEntity.ok().body(
                courseService.updateCourse(courseNewRQ));

    }

    @PostMapping("/update-status")
    public ResponseEntity<Object> updateStatusCourse(@RequestBody CourseNewRQ courseNewRQ) {

        log.info("Call api update Course");
        return ResponseEntity.ok().body(
                courseService.updateStatusCourse(courseNewRQ));

    }

    @GetMapping("/get-all")
    public ResponseEntity<Object> getAllCourse() {

        log.info("Call api getTopCourse");

        return ResponseEntity.ok().body(
                ResponseDTO.builder()
                        .data(courseService.getAll())
                        .code(ResponseCode.SUCCESS.getCode())
                        .message(ResponseCode.SUCCESS.getDesc())
                        .build()
        );

    }


    @GetMapping("/get-top")
    public ResponseEntity<Object> getTopCourse() {

        log.info("Call api getTopCourse");

        return ResponseEntity.ok().body(
                ResponseDTO.builder()
                        .data(courseService.getTopCourse(3))
                        .code(ResponseCode.SUCCESS.getCode())
                        .message(ResponseCode.SUCCESS.getDesc())
                        .build()
        );

    }

}
