package vn.free.register.request.course;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CourseNewRQ {

    private Long id;

    private String code;

    private String name;

    private String description;

    private String content;

    @JsonProperty("start_date")
    private String startDate;

    @JsonProperty("end_date")
    private String endDate;

    private Integer amount;

    private Integer age;

    private Integer status;

    private Long price;

    @JsonProperty("url_image")
    private String urlImage;



}
