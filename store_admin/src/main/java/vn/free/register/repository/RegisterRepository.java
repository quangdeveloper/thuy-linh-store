package vn.free.register.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import vn.free.register.entity.Register;
import vn.free.register.entity.User;

import java.util.Date;
@Transactional
public interface RegisterRepository extends JpaRepository<Register, Long> {

    @Query("select r from Register r " +
            "where (:keyword is null or r.fullName like %:keyword%  or r.passport like %:keyword% ) " +
            "and (:status is null or r.status = :status ) " +
            "and (:fromDate is null or r.createdDate >= :fromDate) " +
            "and (:toDate is null or r.createdDate <= :toDate)")
    Page<Register> search(String keyword,
                      Integer status,
                      Date fromDate,
                      Date toDate,
                      Pageable pageable);

    @Modifying
    @Query("update Register u set u.status = :status, u.reasonCancel = :reasonCancel, u.updatedBy = :updateBy, u.updatedDate = :updateDate where u.id = :id ")
    Integer updateStatus(Long id,
                         Integer status,
                         String reasonCancel,
                         String updateBy,
                         Date updateDate);



}
