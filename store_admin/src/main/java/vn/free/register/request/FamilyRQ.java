package vn.free.register.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class FamilyRQ {

    @JsonProperty("name")
    private String name;

    @JsonProperty("relation")
    private String relation;

    @JsonProperty("age")
    private Integer age;

    @JsonProperty("job")
    private String job;

    @JsonProperty("address")
    private String address;
}
