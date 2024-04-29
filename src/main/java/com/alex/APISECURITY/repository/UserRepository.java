package com.alex.APISECURITY.repository;


import com.alex.APISECURITY.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {


    //These methods do the same. The first one is using hibernate

    Optional<UserEntity> findUserEntityByUsername(String username);
    /*
    @Query("SELECT u FROM UserEntity u WHERE u.username = 1")
    Optional<UserEntity> findUser();
    */

}
