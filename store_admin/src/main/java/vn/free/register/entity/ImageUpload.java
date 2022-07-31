package vn.free.register.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "image_upload")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageUpload {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "origin_name", nullable = false)
    private String originName;

    @Column(name = "directory_folder", nullable = false)
    private String directoryFolder;

    @Column(name = "type_obj", nullable = false)
    private String typeObj;

    @Column(name = "created_date", nullable = false)
    private Date createdDate;

}
