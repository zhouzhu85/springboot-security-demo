package com.zhouzhu.springbootsecurity.demo.repository;

import com.zhouzhu.springbootsecurity.demo.enums.RoleName;
import com.zhouzhu.springbootsecurity.demo.pojo.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author zhouzhu
 * @Description
 * @create 2019-06-12 15:44
 */
@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(RoleName roleName);
}
