package com.javaweb.service;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


public interface IBuildingService {
    ResponseDTO listStaffs(Long buildingId);
    List<BuildingSearchResponse> findAll(BuildingSearchRequest buildingSearchRequest);
    List<BuildingSearchResponse> getAllBuilding(Pageable pageable);
    BuildingDTO findBuildingById(Long id);
    void addOrUpdateBuilding(BuildingDTO buildingDTO);
    void deleteBuildingById(List<Long> ids);
    void assignmentBuilding(AssignmentBuildingDTO assignmentBuildingDTO);
}
