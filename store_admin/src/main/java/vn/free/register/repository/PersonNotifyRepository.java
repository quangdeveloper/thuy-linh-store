package vn.free.register.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.free.register.entity.PersonNotify;

public interface PersonNotifyRepository extends JpaRepository<PersonNotify, Long> {

    @Query("select  p from PersonNotify p where p.registerId = :registerId")
    PersonNotify findByRegisterId(Long registerId);
}
