package vn.free.register.request.register;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RegisterRequest {

    private Long id;
    private Integer status;
    @JsonProperty("reason_cancel")
    private String reasonCancel;
}
