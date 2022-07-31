package vn.free.register.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import vn.free.register.entity.Family;
import vn.free.register.entity.Language;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class RegisterRQ {

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("date_of_birth")
    private String dateOfBirth;

    //1: MALE ||  2: Female
    @JsonProperty("gender")
    private Integer gender;

    @JsonProperty("place_of_birth")
    private String placeOfBirth;

    @JsonProperty("national")
    private String national;

    @JsonProperty("passport")
    private String passport;

    //Thong tin que nha
    @JsonProperty("prior_address")
    private String priorAddress;

    @JsonProperty("prior_phone")
    private String priorPhone;

    @JsonProperty("prior_fax")
    private String priorFax;

    @JsonProperty("prior_email")
    private String priorEmail;

    // Thong tin tai viet nam
    @JsonProperty("address")
    private String address;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("fax")
    private String fax;

    @JsonProperty("email")
    private String email;

    @JsonProperty("high_school")
    private String highSchool;

    @JsonProperty("graduated_year")
    private Integer graduatedYear;

    @JsonProperty("classify")
    private Integer classify;

    @JsonProperty("special_achievements")
    private String specialAchievements;

    // he dao tao
    @JsonProperty("department")
    private String department;

    @JsonProperty("course_id")
    private Long courseId;

    @JsonProperty("branch")
    private String branch;

    @JsonProperty("academic_year")
    private Integer academicYear;

    // loai ung vien
    @JsonProperty("applicant_type")
    private Integer applicantType;

    // loai hoc bong
    @JsonProperty("budget")
    private Integer budget;

    @JsonProperty("personNotify_rq")
    private PersonNotifyRQ personNotifyRQ;

    @JsonProperty("language_list")
    private List<LanguageRQ> languageRQList;

    @JsonProperty("family_list")
    private List<FamilyRQ> familyRQList;

}
