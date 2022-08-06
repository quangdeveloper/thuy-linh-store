package vn.free.register.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import vn.free.register.entity.PaymentTxnDetail;

import java.util.List;

@Transactional
public interface PaymentTxnDetailRepository extends JpaRepository<PaymentTxnDetail, Long> {

    @Query("select u from PaymentTxnDetail u where u.payId = :payId")
    List<PaymentTxnDetail> findByPaymentID(Long payId);

}
