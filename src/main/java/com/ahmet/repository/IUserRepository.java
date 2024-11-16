package com.ahmet.repository;

import com.ahmet.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User,Long> {

    User findByUsername(String username);

}
