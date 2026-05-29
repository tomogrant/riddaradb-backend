package com.se.riddaradb.sagaversion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SagaVersionMotifRepository extends JpaRepository<SagaVersionMotifEntity, SagaVersionMotifKey> {

    List<SagaVersionMotifEntity> findBySagaVersionEntityId(int id);

    List<SagaVersionMotifEntity> findByMotifEntityId(int id);

}
