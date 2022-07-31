package vn.free.register.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import vn.free.register.constant.ResponseCode;

import java.util.Collections;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResponseDTO {

    @JsonIgnore
    private Object data;

    private String code;

    private String message;

    private long total;

    @JsonProperty("data")
    public Object getBody() {

        if (data != null && data instanceof ResponseCode) {
            this.code = ((ResponseCode) data).getCode();
            this.message = ((ResponseCode) data).getDesc();
            data = Collections.emptyList();
            return data;
        }

        return data;

    }

}
