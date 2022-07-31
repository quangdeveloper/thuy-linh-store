package vn.free.register.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import vn.free.register.response.ResponseDTO;


@ControllerAdvice
@Slf4j
public class AppExceptionHandle {

    @ExceptionHandler(value = {GeneralException.class})
    protected ResponseEntity<Object> generalException(GeneralException ex, WebRequest re){

        log.error("ExceptionControlHandler.GeneralException: {}", ex.getMessage());
        return ResponseEntity.ok().body(
                ResponseDTO.builder()
                        .data(null)
                        .code(ex.getCode())
                        .message(ex.getMessage())
                        .build()
        );
    }


}
