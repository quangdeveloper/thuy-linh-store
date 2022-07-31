package vn.free.register.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import vn.free.register.entity.Course;

@Transactional
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("select course from Course  course " +
            "where (:keyword is null or course.name like %:keyword%) " +
            "and (:status  is null or course.status = :status)")
    Page<Course> search(Integer status,
                        String keyword,
                        Pageable pageable);

    @Query("select course from Course  course")
    Page<Course> getTop(Pageable pageable);

    @Modifying
    @Query("update Course c set c.status = :status where c.id = :id ")
    Integer updateStatus(Long id,
                         Integer status);

}
