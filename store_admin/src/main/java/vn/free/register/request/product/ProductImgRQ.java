package vn.free.register.request.product;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ProductImgRQ {

    private Long id;
    private Long productId;
    private String image;

    // 1: mo khoa    2:  xoa
    private Integer status;

}
