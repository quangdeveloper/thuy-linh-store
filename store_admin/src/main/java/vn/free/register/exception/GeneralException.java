package vn.free.register.exception;

import lombok.Data;
import vn.free.register.constant.ResponseCode;

@Data
public class GeneralException extends RuntimeException {

    private String code;

    private String message;

    private Object value;

    public GeneralException(){

    }

    public GeneralException(ResponseCode responseCode){
        this.code= responseCode.getCode();
        this.message = responseCode.getDesc();

    }

    public GeneralException(String code, String message){
        this.code= code;
        this.message = message;

    }
    public GeneralException(String code, String message, Object value){
        this.code= code;
        this.message = message;
        this.value = value;

    }

}
