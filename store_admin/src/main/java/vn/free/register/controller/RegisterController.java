package vn.free.register.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.free.register.constant.ResponseCode;
import vn.free.register.entity.Register;
import vn.free.register.request.RegisterRQ;
import vn.free.register.request.register.RegisterRequest;
import vn.free.register.request.register.RegisterSearch;
import vn.free.register.response.ResponseDTO;
import vn.free.register.service.RegisterService;
import vn.free.register.validate.RegisterValidate;

/**
 * @author quangnv
 */
@Slf4j
@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @PostMapping("/search")
    public ResponseEntity<Object> searchRegister(@RequestBody RegisterSearch search) {

        log.info("Call api register");

        return ResponseEntity.ok().body(
                ResponseDTO.builder()
                        .data(registerService.searchRegister(search))
                        .code(ResponseCode.SUCCESS.getCode())
                        .message(ResponseCode.SUCCESS.getDesc())
                        .build()
        );

    }


    @PostMapping("/update-status")
    public ResponseEntity<Object> updateStatusRegister(@RequestBody RegisterRequest request) {

        log.info("Call api register");

        return ResponseEntity.ok().body(
                ResponseDTO.builder()
                        .data(registerService.changeStatus(request))
                        .code(ResponseCode.SUCCESS.getCode())
                        .message(ResponseCode.SUCCESS.getDesc())
                        .build()
        );

    }

    @GetMapping("/get-by-id")
    public ResponseEntity<Object> getById(Long id) {

        log.info("Call api register");

        return ResponseEntity.ok().body(
                ResponseDTO.builder()
                        .data(registerService.getByID(id))
                        .code(ResponseCode.SUCCESS.getCode())
                        .message(ResponseCode.SUCCESS.getDesc())
                        .build()
        );

    }

}
