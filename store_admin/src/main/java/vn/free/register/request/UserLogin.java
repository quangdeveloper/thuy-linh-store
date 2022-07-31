package vn.free.register.request;


import lombok.Data;

@Data
public class UserLogin {

    private String username;

    private String password;

    private Integer type;
}
