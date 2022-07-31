package vn.free.register.request.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class NewUserRQ {

    private Long id;

    private String username;

    private String password;

    private String email;

    private String phone;

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("gr_role_id")
    private Long groupRoleId;

    private Integer status;
}
