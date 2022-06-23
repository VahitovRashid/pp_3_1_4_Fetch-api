package com.rashidvakhitov.pp_3_1_4_fetchapi.repository;


import com.rashidvakhitov.pp_3_1_4_fetchapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.name = :name")
    User getUser(@Param("name") String name);
}
