package com.se.riddaradb.sagaversion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SagaVersionRepository extends JpaRepository<SagaVersionEntity, Integer> {
}
