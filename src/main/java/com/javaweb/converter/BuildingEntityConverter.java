package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.utils.UploadFileUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component

public class BuildingEntityConverter {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private RentAreaRepository rentAreaRepository;
    private final UploadFileUtils uploadFileUtils;
    @Autowired
    public BuildingEntityConverter(UploadFileUtils uploadFileUtils) {
        this.uploadFileUtils = uploadFileUtils;
    }
    public void toBuildingEntityConverter(BuildingDTO buildingDTO){
        BuildingEntity building = new BuildingEntity();
        building = modelMapper.map(buildingDTO,BuildingEntity.class);
        List<String> typeCode = buildingDTO.getTypeCode();
        String typeCodeFinal = typeCode.stream().map(it -> it.toString()).collect(Collectors.joining(","));
        building.setType(typeCodeFinal);
        saveThumbnail(buildingDTO,building);

        String rentArea = buildingDTO.getRentArea();
        String[] values = rentArea.split(",");
        List<RentAreaEntity> rentAreaEntities =new ArrayList<>();
        for (String it : values){
            RentAreaEntity rentAreaEntity = new RentAreaEntity();
            rentAreaEntity.setValue(Integer.parseInt(it));
            rentAreaEntity.setBuilding(building);
            rentAreaEntities.add(rentAreaEntity);
        }
        building.setRentArea(rentAreaEntities);
        buildingRepository.save(building);
    }
    private void saveThumbnail(BuildingDTO buildingDTO, BuildingEntity buildingEntity) {
        String path = "/building/" + buildingDTO.getImageName();
        if (null != buildingDTO.getImageBase64()) {
            if (null != buildingEntity.getImage()) {
                if (!path.equals(buildingEntity.getImage())) {
                    File file = new File("C://home/office" + buildingEntity.getImage());
                    file.delete();
                }
            }
            byte[] bytes = Base64.decodeBase64(buildingDTO.getImageBase64().getBytes());
            uploadFileUtils.writeOrUpdate(path, bytes);
            buildingEntity.setImage(path);
        }
    }
}
