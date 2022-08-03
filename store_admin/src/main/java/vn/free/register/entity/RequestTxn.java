package vn.free.register.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "request_txn")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestTxn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "cus_mobile", nullable = false)
    private String customerMobile;

    @Column(name = "cus_name", nullable = false)
    private String customerName;

    @Column(name = "date_req", nullable = false)
    private Date dateRequest;

    // 1: khoi tao   2: đã xem    3: xoa
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
