package vn.free.register.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.free.register.entity.Family;
import vn.free.register.entity.PersonNotify;

import java.util.List;

public interface FamilyRepository extends JpaRepository<Family, Long> {

    @Query("select  p from Family p where p.registerId = :registerId")
    List<Family> findByRegisterId(Long registerId);

}
