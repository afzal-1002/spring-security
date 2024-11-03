package com.spring.security.wut.spring.security.repository;

import com.spring.security.wut.spring.security.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

    public Users findUsersByUserName(String userName);

}
