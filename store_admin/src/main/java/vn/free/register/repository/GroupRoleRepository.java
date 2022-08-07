package vn.free.register.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import vn.free.register.entity.Customer;
import vn.free.register.entity.GroupRole;

@Transactional
public interface GroupRoleRepository extends JpaRepository<GroupRole, Long> {

    @Query("select u from GroupRole u where u.id = :id")
    GroupRole findByID(Long id);

}
