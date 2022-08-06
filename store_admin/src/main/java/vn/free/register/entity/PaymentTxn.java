package vn.free.register.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "payment_txn")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentTxn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "cus_id", nullable = false)
    private Long customerId;

    @Column(name = "product_ids", nullable = false)
    private String productIds;

    @Column(name = "txn_count", nullable = false)
    private Integer txnCount;

    @Column(name = "total_amount", nullable = false)
    private Long totalAmount;

    @Column(name = "cancel_desc")
    private String cancelDesc;

    // 1: khoi tao   2: cho thanh toan    3: da thanh toan  4: da huy
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
