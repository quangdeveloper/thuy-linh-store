package vn.free.register.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import vn.free.register.entity.GroupRole;
import vn.free.register.entity.RequestTxn;
import vn.free.register.entity.User;

@Transactional
public interface RequestTxnRepository extends JpaRepository<RequestTxn, Long> {


    @Query("select u from RequestTxn u " +
            "where (:keyword is null or u.customerName like %:keyword%  or u.customerMobile like %:keyword% ) " +
            "and (:status is null or u.status = :status )")
    Page<RequestTxn> search(String keyword,
                      Integer status,
                      Pageable pageable);
    @Modifying
    @Query("update RequestTxn u set u.status = :status where u.id = :id ")
    Integer updateStatus(Long id,
                         Integer status);

    @Query("select u from RequestTxn u where u.id = :id")
    RequestTxn findByID(Long id);

}
