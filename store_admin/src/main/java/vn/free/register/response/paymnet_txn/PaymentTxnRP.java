package vn.free.register.response.paymnet_txn;


import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import vn.free.register.entity.Customer;
import java.util.Date;
import java.util.List;

@Data
@Builder
@ToString
public class PaymentTxnRP {

    private Long id;

    private Customer customer;

    private String productIds;

    private Integer txnCount;

    private Long totalAmount;

    private String cancelDesc;

    // 1: khoi tao   2: cho thanh toan    3: da thanh toan  4: da huy
    private Integer status;

    private Date createdDate;

    private String createdBy;

    private Date updatedDate;

    private String updatedBy;

    private List<PaymentTxnDetailRP> paymentTxnDetails;
}
