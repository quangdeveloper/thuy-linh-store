package vn.free.register.request.customer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import java.util.Date;


@Data
@ToString
public class CustomerRQ {

    private Long id;

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("identify_card")
    private String identifyCard;

    private String mobile;

    private String address;

    @JsonProperty("date_born")
    private String dateBorn;

    // 1: mo khoa    2: khoa    3: xoa
    private Integer status;


}
