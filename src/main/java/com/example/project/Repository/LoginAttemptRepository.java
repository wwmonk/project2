package com.example.project.Repository;

import com.example.project.Entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginAttemptRepository extends JpaRepository<MemberEntity, Integer> {

    MemberEntity findByName(String name);
}
