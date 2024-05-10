package com.example.project.Repository;

import com.example.project.Entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface LoginRepository extends JpaRepository<MemberEntity, Integer> {
    Optional<MemberEntity> findByUsername(String username);
}
