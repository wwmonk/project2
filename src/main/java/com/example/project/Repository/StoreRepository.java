package com.example.project.Repository;

import com.example.project.Entity.MemberEntity;
import com.example.project.Entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoreRepository
        extends JpaRepository<StoreEntity, Long> {
    Optional<StoreEntity> findById(long id);
}
