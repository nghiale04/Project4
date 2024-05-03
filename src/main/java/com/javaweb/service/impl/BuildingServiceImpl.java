package com.javaweb.service.impl;

import com.javaweb.converter.BuildingDTOConverter;
import com.javaweb.converter.BuildingEntityConverter;
import com.javaweb.converter.BuildingResponseConverter;
import com.javaweb.entity.AssignmentBuildingEntity;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.dto.UserDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.AssignmentBuildingRepository;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.repository.custom.impl.BuildingRepositoryCustomImpl;
import com.javaweb.service.IBuildingService;
import com.javaweb.service.IUserService;
import com.javaweb.utils.UploadFileUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

@Service
public class BuildingServiceImpl implements IBuildingService {
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BuildingDTOConverter buildingDTOConverter;
    @Autowired
    private RentAreaRepository rentAreaRepository;
    @Autowired
    private AssignmentBuildingRepository assignmentBuildingRepository;
    @Autowired
    private BuildingResponseConverter buildingResponseConverter;
    @Autowired
    private BuildingEntityConverter buildingEntityConverter;


    @Override
    public ResponseDTO listStaffs(Long buildingId) {
        BuildingEntity building = buildingRepository.findById(buildingId).get() ; // trả về một building id
        List<UserEntity> staffs = userRepository.findByStatusAndRoles_Code(1,"STAFF"); // trả về những staff đã được cấp quyền
        List<UserEntity> staffAssignment = building.getUserEntities();
        List<StaffResponseDTO> staffResponseDTOS = new ArrayList<>();
        ResponseDTO responseDTO = new ResponseDTO();
        for (UserEntity it :staffs){
            StaffResponseDTO staffResponseDTO = new StaffResponseDTO();
            staffResponseDTO.setFullName(it.getFullName());
            staffResponseDTO.setStaffId(it.getId());
            if (staffAssignment.contains(it)){
                staffResponseDTO.setChecked("checked");
            }else{
                staffResponseDTO.setChecked("");
            }
            staffResponseDTOS.add(staffResponseDTO);
        }
        responseDTO.setData(staffResponseDTOS);
        responseDTO.setMessage("success");
        return responseDTO;
    }

    @Override
    public List<BuildingSearchResponse> findAll(BuildingSearchRequest buildingSearchRequest) {
        List<BuildingEntity> buildingEntities = buildingRepository.findAll(buildingSearchRequest);
        List<BuildingSearchResponse> result = new ArrayList<BuildingSearchResponse>();
        for (BuildingEntity item : buildingEntities) {
            BuildingSearchResponse building = buildingDTOConverter.toBuildingDTO(item);
            result.add(building);
        }
        return result;
    }

    @Override
    public List<BuildingSearchResponse> getAllBuilding(Pageable pageable) {
        List<BuildingEntity> buildingEntities = buildingRepository.getAllBuildings(pageable);
        List<BuildingSearchResponse> results = new ArrayList<>();
        for (BuildingEntity building : buildingEntities) {
           BuildingSearchResponse buildingSearchResponse = buildingDTOConverter.toBuildingDTO(building);
           results.add(buildingSearchResponse);
        }
        return results;
    }

    @Override
    public BuildingDTO findBuildingById(Long id) {
        BuildingDTO buildingDTO = buildingResponseConverter.converterById(id);
        return buildingDTO;
    }

    @Override
    public void addOrUpdateBuilding(BuildingDTO buildingDTO) {
          buildingEntityConverter.toBuildingEntityConverter(buildingDTO);
    }

    @Override
    public void deleteBuildingById(List<Long> ids) {
//        buildingRepository.deleteAllByIdIn(ids);
        for(Long it : ids){
            BuildingEntity building = buildingRepository.findById(it).get();
            List<RentAreaEntity> delete = building.getRentArea();
            rentAreaRepository.deleteAll(delete);
            List<AssignmentBuildingEntity> assignmentBuildingEntities = assignmentBuildingRepository.findAllByBuildingId(it);
            assignmentBuildingRepository.deleteAll(assignmentBuildingEntities);
            buildingRepository.deleteById(it);
        }
    }

    @Override
    public void assignmentBuilding(AssignmentBuildingDTO assignmentBuildingDTO) {
        BuildingEntity building = buildingRepository.findById(assignmentBuildingDTO.getBuildingId()).get();
        List<UserEntity> staffs = userRepository.findByIdIn(assignmentBuildingDTO.getStaffs());
        building.setUserEntities(staffs);
        buildingRepository.save(building);
//        List<AssignmentBuildingEntity> assignmentBuildingEntities = assignmentBuildingRepository.findAllByBuildingId(assignmentBuildingDTO.getBuildingId());
//        assignmentBuildingRepository.deleteAll(assignmentBuildingEntities);
//        BuildingEntity building = buildingRepository.findById(assignmentBuildingDTO.getBuildingId()).get();
//        List<UserEntity> user = userRepository.findByIdIn(assignmentBuildingDTO.getStaffs());
//        for(UserEntity it: user){
//        AssignmentBuildingEntity assignmentBuilding = new AssignmentBuildingEntity();
//        assignmentBuilding.setBuilding(building);
//        assignmentBuilding.setUser(it);
//        assignmentBuildingRepository.save(assignmentBuilding);
//    }
    }


}
