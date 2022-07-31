package vn.free.register.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "course")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "content", nullable = false, length = 1000000)
    private String content;

    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Column(name = "end_date", nullable = false)
    private Date endDate;

    //so luong hoc sinh
    @Column(name = "amount", nullable = false)
    private Integer amount;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "price", nullable = false)
    private Long price;

    @Column(name = "url_image", nullable = false)
    private String urlImage;

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

}
