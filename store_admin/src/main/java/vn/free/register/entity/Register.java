package vn.free.register.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "register")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Register {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "date_of_birth", nullable = false)
    private Date dateOfBirth;

    //1: MALE ||  2: Female
    @Column(name = "gender", nullable = false)
    private Integer gender;

    @Column(name = "place_of_birth", nullable = false)
    private String placeOfBirth;

    @Column(name = "national", nullable = false)
    private String national;

    @Column(name = "passport", nullable = false)
    private String passport;

    //Thong tin que nha
    @Column(name = "prior_address", nullable = false)
    private String priorAddress;

    @Column(name = "prior_phone", nullable = false)
    private String priorPhone;

    @Column(name = "priorFax", nullable = false)
    private String priorFax;

    @Column(name = "prior_email", nullable = false)
    private String priorEmail;

    // Thong tin tai viet nam
    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "fax")
    private String fax;

    @Column(name = "email")
    private String email;

    @Column(name = "high_school", nullable = false)
    private String highSchool;

    @Column(name = "graduated_year", nullable = false)
    private Integer graduatedYear;

    @Column(name = "classify", nullable = false)
    private Integer classify;

    @Column(name = "special_achievements")
    private String specialAchievements;

    // he dao tao
    @Column(name = "department", nullable = false)
    private String department;

    @Column(name = "course_id", nullable = false)
    private Long courseId;

    @Column(name = "branch", nullable = false)
    private String branch;

    @Column(name = "academic_year", nullable = false)
    private Integer academicYear;

    // loai ung vien
    @Column(name = "applicant_type", nullable = false)
    private Integer applicantType;

    // loai hoc bong
    @Column(name = "budget", nullable = false)
    private Integer budget;

    // 1: init  2: allow   3: not allow  4: deleted
    @Column(name = "status", nullable = false)
    private Integer status;

    @Column(name = "created_date", nullable = false)
    private Date createdDate;

    @Column(name = "updated_date")
    private Date updatedDate;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "reason_cancel")
    private String reasonCancel;
}
