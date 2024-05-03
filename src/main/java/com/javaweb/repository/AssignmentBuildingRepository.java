package com.javaweb.repository;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.javaweb.entity.AssignmentBuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentBuildingRepository extends JpaRepository<AssignmentBuildingEntity,Long> {
    List<AssignmentBuildingEntity> findAllByBuildingId(Long id);
}
