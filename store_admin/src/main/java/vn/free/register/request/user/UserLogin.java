package vn.free.register.request.user;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserLogin {

    private String username;

    private String password;

    private Integer type;
}
