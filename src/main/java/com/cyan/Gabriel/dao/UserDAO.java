package com.cyan.Gabriel.dao;

import com.cyan.Gabriel.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDAO extends JpaRepository<User, String> {

    User save(User user);

    List<User> findAll();

    User findByEmail(@Param("pemail") String email);
}
