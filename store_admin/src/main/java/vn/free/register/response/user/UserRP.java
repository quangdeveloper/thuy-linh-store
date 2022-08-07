package vn.free.register.response.user;


import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@ToString
public class UserRP {

    private Long id;

    private String username;

    private String password;

    private String fullName;

    private String email;

    private String mobile;

    private String address;

    private String dateBorn;

    private Long groupRoleId;

    // 1: mo khoa    2: khoa    3: xoa
    private Integer status;

    private String createdDate;

    private String createdBy;

    private String updatedDate;

    private String updatedBy;

    private String groupRoleName;


}
