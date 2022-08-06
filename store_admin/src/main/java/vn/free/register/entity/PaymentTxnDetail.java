package vn.free.register.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "payment_txn_detail")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentTxnDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "pay_id", nullable = false)
    private Long payId;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "product_price", nullable = false)
    private Long productPrice;

    @Column(name = "product_quan", nullable = false)
    private Integer productQuantity;

    @Column(name = "date_quan", nullable = false)
    private Integer dateQuantity;

    // 1:use   2: cancel
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
