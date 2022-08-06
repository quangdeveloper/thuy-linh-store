package vn.free.register.request.paymnet_txn;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;


@Data
@ToString
@Builder
public class PaymentTxnRQ {

    private Long id;

    @JsonProperty("cus_id")
    private Long customerId;

    @JsonProperty("product_ids")
    private String productIds;

    @JsonProperty("txn_count")
    private Integer txnCount;

    @JsonProperty("total_amount")
    private Long totalAmount;

    @JsonProperty("cancel_desc")
    private String cancelDesc;

    // 1: khoi tao   2: cho thanh toan    3: da thanh toan  4: da huy
    private Integer status;

    @JsonProperty("payment_txn_details")
    private List<PaymentTxnDetailRQ> paymentTxnDetails;


}
