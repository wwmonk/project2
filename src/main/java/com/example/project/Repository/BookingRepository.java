package com.example.project.Repository;

import com.example.project.Entity.BookingEntity;
import com.example.project.Entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Integer> {
    Optional<BookingEntity> findByName(String name);
}
