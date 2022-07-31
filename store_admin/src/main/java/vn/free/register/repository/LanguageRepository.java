package vn.free.register.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.free.register.entity.Family;
import vn.free.register.entity.Language;

import java.util.List;

public interface LanguageRepository extends JpaRepository<Language, Long> {

    @Query("select  p from Language p where p.registerId = :registerId")
    List<Language> findByRegisterId(Long registerId);
}
