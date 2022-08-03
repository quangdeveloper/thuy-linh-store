package vn.free.register.request.customer;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import java.util.Date;


@Data
@ToString
public class CustomerRQ {

    private Long id;
    private String fullName;
    private String identifyCard;
    private String mobile;
    private String address;
    private String dateBorn;
    // 1: mo khoa    2: khoa    3: xoa
    private Integer status;

}
