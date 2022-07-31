package vn.free.register.response;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserLoginRP {

    private Long id;
    private String username;
    private  String password;
    private String fullName;
    private String email;
    private String phone;
    private Long roleId;
    private Integer status;
    private String token;

}
