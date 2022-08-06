package vn.free.register.response.paymnet_txn;


import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@ToString
public class PaymentTxnDetailRP {

    private Long id;

    private Long payId;

    private Long productId;

    private Long productPrice;

    private Integer productQuantity;

    private Integer dateQuantity;

    // 1:use   2: cancel
    private Integer status;

    private Date createdDate;

    private String createdBy;

    private Date updatedDate;

    private String updatedBy;
}
