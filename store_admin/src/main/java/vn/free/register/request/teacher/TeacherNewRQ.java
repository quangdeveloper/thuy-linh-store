package vn.free.register.request.teacher;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.Date;

@Data
public class TeacherNewRQ {

    private Long id;

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("date_of_birth")
    private String dateOfBirth;

    @JsonProperty("address")
    private String address;

    private String phone;

    private String email;

    private String fax;

    @JsonProperty("academic_degree")
    private Integer academicDegree;

    private Integer specialize;

    @JsonProperty("short_desc")
    private String shortDesc;

    @JsonProperty("image")
    private String imageUrl;

    private Integer status;

    private Integer gender;
}
