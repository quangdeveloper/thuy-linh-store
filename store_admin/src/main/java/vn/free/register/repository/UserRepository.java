package vn.free.register.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import vn.free.register.entity.User;

@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u " +
            "where (:keyword is null or u.username like %:keyword%  or u.fullName like %:keyword% ) " +
            "and (:status is null or u.status = :status )")
    Page<User> search(String keyword,
                      Integer status,
                      Pageable pageable);

    @Modifying
    @Query("update User u set u.status = :status where u.id = :id ")
    Integer updateStatus(Long id,
                         Integer status);

    @Query("select u from User u where u.username = :username and u.status = 1")
    User findByUsername(String username);

    @Query("select u from User u where u.username = :username")
    User findByUsernameAll(String username);

    @Query("select u from User u where u.id = :id")
    User findByID(Long id);
}
