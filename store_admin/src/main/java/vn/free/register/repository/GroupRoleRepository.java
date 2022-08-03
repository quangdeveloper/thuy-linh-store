package vn.free.register.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import vn.free.register.entity.GroupRole;

@Transactional
public interface GroupRoleRepository extends JpaRepository<GroupRole, Long> {

}
