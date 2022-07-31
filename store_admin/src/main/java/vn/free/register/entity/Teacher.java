package vn.free.register.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "teacher")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "date_of_birth", nullable = false)
    private Date dateOfBirth;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "fax", nullable = false)
    private String fax;

    // 1: Thac si    2: Tien si      3: pho giao su      4: giao su
    @Column(name = "academic_degree", nullable = false)
    private Integer academicDegree;

    // 1: Ngoai ngu     2: IT
    @Column(name = "specialize", nullable = false)
    private Integer specialize;

    @Column(name = "short_desc", nullable = false)
    private String shortDesc;


    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    // 1: mo khoa    2: khoa    3: xoa
    @Column(name = "status", nullable = false)
    private Integer status;

    @Column(name = "created_date", nullable = false)
    private Date createdDate;

    @Column(name = "created_by", nullable = false)
    private String createdBy;


    @Column(name = "updated_date")
    private Date updatedDate;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "gender")
    private Integer gender;

}
