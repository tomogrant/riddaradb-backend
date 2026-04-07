package com.se.riddaradb.repositories;

import com.se.riddaradb.entities.SagaVersionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SagaVersionRepository extends JpaRepository<SagaVersionEntity, Integer> {
}
