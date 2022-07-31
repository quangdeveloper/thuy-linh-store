package vn.free.register.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.free.register.service.UploadService;


/**
 * @author quangnv
 */
@Slf4j
@RestController
@RequestMapping("/upload")
public class UploadController {




    @Autowired
    private UploadService uploadService;


    @PostMapping("/teacher")
    public ResponseEntity uploadTeacher(@RequestParam("file") MultipartFile image, String desc) {

        log.info("Call api upload file");
        return ResponseEntity.ok().body(
                uploadService.uploadTeacher("TEACHER", image));
    }


}
