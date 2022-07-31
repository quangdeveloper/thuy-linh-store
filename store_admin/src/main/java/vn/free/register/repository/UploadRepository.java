package vn.free.register.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import vn.free.register.entity.ImageUpload;

@Transactional
public interface UploadRepository extends JpaRepository<ImageUpload, Long> {

}
