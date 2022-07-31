package vn.free.register.response;


import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class UserLoginRP {

    private Long id;
    private String username;
    private  String password;
    private String fullName;
    private String email;
    private String phone;
    private Long groupRoleId;
    private Integer status;
    private String token;

}
