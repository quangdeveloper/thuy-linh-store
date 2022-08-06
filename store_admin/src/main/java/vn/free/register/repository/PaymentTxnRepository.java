package vn.free.register.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import vn.free.register.entity.PaymentTxn;
import vn.free.register.entity.RequestTxn;

import java.util.Date;

@Transactional
public interface PaymentTxnRepository extends JpaRepository<PaymentTxn, Long> {


    @Query("select u from PaymentTxn u " +
            "where (:customerId is null or u.customerId = :customerId) " +
            "and (:status is null or u.status = :status )" +
            "and (:fromDate is null or u.createdDate >= :fromDate )" +
            "and (:toDate is null or u.createdDate <= :toDate )"
    )
    Page<PaymentTxn> search(Long customerId,
                            Integer status,
                            Date fromDate,
                            Date toDate,
                            Pageable pageable);

    @Query("select u from PaymentTxn u where u.id = :id")
    PaymentTxn findByID(Long id);

}
