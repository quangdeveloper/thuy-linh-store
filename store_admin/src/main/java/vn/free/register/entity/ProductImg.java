package vn.free.register.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "product_img")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "image", nullable = false)
    private String image;

    // 1: mo khoa    2:  xoa
    @Column(name = "status", nullable = false)
    private Integer status;


}
