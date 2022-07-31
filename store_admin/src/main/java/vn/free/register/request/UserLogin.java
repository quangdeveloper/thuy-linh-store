package vn.free.register.request;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserLogin {

    private String username;

    private String password;

    private Integer type;
}
