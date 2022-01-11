package com.web.relocation.repository;

import com.web.relocation.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<UserEntity, String> {
    UserEntity findByUserIdAndPassword(String userId, String password);

    Optional<UserEntity> findByUserId(String userId);
}