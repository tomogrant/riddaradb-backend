package com.se.riddaradb.repositories;

import com.se.riddaradb.entities.MotifEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface MotifRepository extends JpaRepository<MotifEntity, Integer> {

    List<MotifEntity> findByParentIsNull();

    List<MotifEntity> findByParentId(int id);

}
