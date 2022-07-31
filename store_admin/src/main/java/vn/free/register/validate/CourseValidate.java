package vn.free.register.validate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import vn.free.register.constant.ResponseCode;
import vn.free.register.request.CourseSearch;
import vn.free.register.response.ResponseDTO;

import java.util.Collections;

@Slf4j
public class CourseValidate {

    public static ResponseEntity<Object> validateSearch(CourseSearch courseSearch){

        if (courseSearch.getPageIndex() == null || courseSearch.getPageIndex() <= 0){

            log.warn("Invalid page number");
            return ResponseEntity.ok().body(
                    ResponseDTO.builder()
                            .data(ResponseCode.INVALID_DATA)
                            .build()
            );
        }

        if (courseSearch.getPageSize() == null || courseSearch.getPageSize() <= 0){

            log.warn("Invalid page number");
            return ResponseEntity.ok().body(
                    ResponseDTO.builder()
                            .data(ResponseCode.INVALID_DATA)
                            .build()
            );
        }

        return null;
    }
}
