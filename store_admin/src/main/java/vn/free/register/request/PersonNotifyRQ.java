package vn.free.register.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class PersonNotifyRQ {

    @JsonProperty("name")
    private String name;

    @JsonProperty("address")
    private String address;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("fax")
    private String fax;

    @JsonProperty("email")
    private String email;

}
