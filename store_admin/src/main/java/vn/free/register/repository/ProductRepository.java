package vn.free.register.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import vn.free.register.entity.Customer;
import vn.free.register.entity.Product;

import java.util.Date;
import java.util.List;

@Transactional
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select u from Product u " +
            "where (:keyword is null or u.code like %:keyword%  or u.name like %:keyword% ) " +
            "and (:status is null or u.status = :status )" +
            "and (:fromDate is null or u.createdDate >= :fromDate )" +
            "and (:toDate is null or u.createdDate <= :toDate )"
    )
    Page<Product> search(String keyword,
                         Integer status,
                         Date fromDate,
                         Date toDate,
                         Pageable pageable);

    @Query("select u from Product u " +
            "where (:keyword is null or u.code like %:keyword%  or u.name like %:keyword% ) "
    )
    Page<Product> searchActive(String keyword,
                         Pageable pageable);

    @Modifying
    @Query("update Product u set u.status = :status where u.id = :id ")
    Integer updateStatus(Long id,
                         Integer status);

    @Query("select u from Product u where u.id = :id")
    Product findByID(Long id);


    @Query("select u from Product u where u.code = :code")
    Product findByCode(String code);

}
