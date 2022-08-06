package vn.free.register.request.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;


@Data
@ToString
public class ProductRQ {

    private Long id;
    private String code;
    private String name;
    private String desc;
    private String logo;
    private Long quantity;

    @JsonProperty("price_import")
    private Long priceImport;

    @JsonProperty("price_export")
    private Long priceExport;

    // 1: mo khoa    2: khoa    3: xoa
    private Integer status;

    @JsonProperty("product_images")
    private List<ProductImgRQ> productImages;

}
