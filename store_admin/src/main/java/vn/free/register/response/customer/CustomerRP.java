package vn.free.register.response.customer;


import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@ToString
public class CustomerRP {

    private Long id;

    private String fullName;

    private String identifyCard;

    private String mobile;

    private String address;

    private String dateBorn;

    // 1: mo khoa    2: khoa    3: xoa
    private Integer status;

    private Date createdDate;

    private String createdBy;

    private Date updatedDate;

    private String updatedBy;

}
