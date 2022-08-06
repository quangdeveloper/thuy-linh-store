package vn.free.register.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import vn.free.register.entity.Customer;
import vn.free.register.entity.User;

import java.util.List;

@Transactional
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("select u from Customer u where u.status = 1")
    List<Customer> findAllActive();

    @Query("select u from Customer u " +
            "where (:keyword is null or u.fullName like %:keyword%  or u.identifyCard like %:keyword% or u.mobile like %:keyword% ) " +
            "and (:status is null or u.status = :status )")
    Page<Customer> search(String keyword,
                          Integer status,
                          Pageable pageable);

    @Query("select u from Customer u where u.id = :id")
    Customer findByID(Long id);

    @Query("select u from Customer u where u.identifyCard = :identifyCard")
    Customer findByIdentifyCard(String identifyCard);
}
