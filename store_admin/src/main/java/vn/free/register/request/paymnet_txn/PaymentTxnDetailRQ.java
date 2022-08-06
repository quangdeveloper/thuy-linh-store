package vn.free.register.request.paymnet_txn;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Builder
@Data
@ToString
public class PaymentTxnDetailRQ {

    private Long id;

    @JsonProperty("pay_id")
    private Long payId;

    @JsonProperty("product_id")
    private Long productId;

    @JsonProperty("product_price")
    private Long productPrice;

    @JsonProperty("product_quan")
    private Integer productQuantity;

    @JsonProperty("date_quan")
    private Integer dateQuantity;

    // 1:use   2: cancel
    private Integer status;

}
