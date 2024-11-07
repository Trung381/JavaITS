package com.example.vuvantrung.repository.role;

import com.example.vuvantrung.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>, RoleRepositoryCustom {
    Role findByName(String name);
}
