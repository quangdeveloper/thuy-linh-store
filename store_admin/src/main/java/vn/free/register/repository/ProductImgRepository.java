package vn.free.register.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import vn.free.register.entity.ProductImg;

import java.util.List;

@Transactional
public interface ProductImgRepository extends JpaRepository<ProductImg, Long> {


    @Query("select u from ProductImg u where u.productId = :productId and u.status = 1")
    List<ProductImg> findByProductID(Long productId);

}
