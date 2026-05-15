package com.se.riddaradb.repositories;

import com.se.riddaradb.entities.MotifEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotifRepository extends JpaRepository<MotifEntity, Integer> {
}
