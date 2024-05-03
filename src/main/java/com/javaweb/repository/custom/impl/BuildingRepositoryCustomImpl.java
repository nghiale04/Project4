package com.javaweb.repository.custom.impl;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BuildingRepositoryCustomImpl implements BuildingRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;
    public static void nomalQuery(BuildingSearchRequest buildingSearchRequest,StringBuilder where){
        try{
            Field[] fiels = buildingSearchRequest.getClass().getDeclaredFields();
            for(Field item: fiels){
                item.setAccessible(true);
                String fieldName = item.getName();
                if(!fieldName.startsWith("area") && !fieldName.startsWith("rentPrice") && !fieldName.equals("typeCode") && !fieldName.equals("staffId")){
                    Object value = item.get(buildingSearchRequest);
                    if (value != null && value != "") {
                        if (item.getType().getName().equals("java.lang.Long") || item.getType().getName().equals("java.lang.Integer")) {
                            where.append(" AND b." + fieldName + "=" + value + " ");
                        } else if (item.getType().getName().equals("java.lang.String")){
                            where.append(" AND b." + fieldName + " LIKE '%" + value + "%' ");
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void specialQuery(BuildingSearchRequest buildingSearchRequest,StringBuilder where){

        Long staffId = buildingSearchRequest.getStaffId();
        if (staffId!=null) {
            where.append(" AND EXISTS (SELECT * FROM assignmentbuilding WHERE assignmentbuilding.buildingid = b.id ");
            where.append(" AND assignmentbuilding.staffid = " + staffId);
            where.append(" ) ");
        }

        Long areaFrom = buildingSearchRequest.getAreaFrom();
        Long areaTo = buildingSearchRequest.getAreaTo();
        if (areaTo!= null || areaFrom !=null){
            where.append(" AND EXISTS (SELECT * FROM rentarea r WHERE b.id = r.buildingid ");
            if (areaFrom != null) {
                where.append(" AND r.value >= " + areaFrom);
            }
            if (areaTo != null) {
                where.append(" AND r.value <= " + areaTo);
            }
            where.append(" ) ");
        }

        Long rentPriceTo = buildingSearchRequest.getRentPriceTo();
        Long rentPriceFrom = buildingSearchRequest.getRentPriceFrom();
        if (rentPriceFrom !=null || rentPriceTo !=null) {
            if (rentPriceFrom !=null) {
                where.append(" AND b.rentprice >= " + rentPriceFrom);
            }
            if (rentPriceTo !=null) {
                where.append(" AND b.rentprice <= " + rentPriceTo);
            }
        }

        List<String> typeCode = buildingSearchRequest.getTypeCode();
        if ((typeCode != null) && (typeCode.size() != 0)) {
            where.append(" AND ( ");
            String sql = typeCode.stream().map(it -> "type LIKE"+"'%"+it+"%' ").collect(Collectors.joining(" OR "));
            where.append(sql);
            where.append(" ) ");
        }
    }

    @Override
    public List<BuildingEntity> findAll(BuildingSearchRequest buildingSearchRequest) {
        StringBuilder sql = new StringBuilder("Select b.* from building b ");
        StringBuilder where = new StringBuilder(" WHERE 1=1 ");
        nomalQuery(buildingSearchRequest, where);
        specialQuery(buildingSearchRequest, where);
        where.append(" GROUP BY b.id;");
        sql.append(where);
        Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
        return query.getResultList();
    }

    @Override
    public List<BuildingEntity> getAllBuildings(Pageable pageable) {
        StringBuilder sql = new StringBuilder(buildQueryFilter())
                .append(" LIMIT ").append(pageable.getPageSize()).append("\n")
                .append(" OFFSET ").append(pageable.getOffset());
        System.out.println("Final query: " + sql.toString());
        Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
        return query.getResultList();
    }

    private String buildQueryFilter() {
        String sql = "SELECT * FROM building";
        return sql;
    }

}
