package vn.free.register.request.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class NewUserRQ {

    private Long id;

    private String username;

    private String password;

    private String email;

    private String phone;

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("role_id")
    private Long roleId;

    private Integer status;
}
