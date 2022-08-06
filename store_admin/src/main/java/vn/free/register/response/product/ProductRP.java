package vn.free.register.response.product;


import lombok.*;
import vn.free.register.entity.ProductImg;

import java.util.Date;
import java.util.List;

@Data
@Builder
@ToString
public class ProductRP {

    private Long id;
    private String code;
    private String name;
    private String desc;
    private String logo;
    private Long quantity;
    private Long priceImport;
    private Long priceExport;

    // 1: mo khoa    2: khoa    3: xoa
    private Integer status;

    private Date createdDate;
    private String createdBy;
    private Date updatedDate;
    private String updatedBy;

    private List<ProductImg> productImages;

}
