package com.se.riddaradb.object;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObjectRepository extends JpaRepository<ObjectEntity, Integer> {
}
