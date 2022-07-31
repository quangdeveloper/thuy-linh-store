package vn.free.register.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import vn.free.register.entity.Teacher;
import vn.free.register.entity.User;

import java.util.Date;

@Transactional
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    @Query("select teach from Teacher  teach")
    Page<Teacher> getTop(Pageable pageable);


    @Query("select u from Teacher u " +
            "where (:keyword is null or u.fullName like %:keyword%  or u.email like %:keyword% ) " +
            "and (:status is null or u.status = :status )")
    Page<Teacher> search(String keyword,
                      Integer status,
                      Pageable pageable);

    @Modifying
    @Query("update Teacher u set u.status = :status, u.status = :status, u.updatedDate = :updateDate, u.updatedBy = :updatedBy  where u.id = :id ")
    Integer updateStatus(Long id,
                         Integer status,
                         String updatedBy,
                         Date updateDate);

}
