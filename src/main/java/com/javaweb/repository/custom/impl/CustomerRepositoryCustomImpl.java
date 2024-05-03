package com.javaweb.repository.custom.impl;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.repository.custom.CustomerRepositoryCustom;
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
public class CustomerRepositoryCustomImpl implements CustomerRepositoryCustom{
    @PersistenceContext
    private EntityManager entityManager;
    public static void nomalQuery(CustomerSearchRequest customerSearchRequest, StringBuilder where){
        try{
            Field[] fiels = customerSearchRequest.getClass().getDeclaredFields();
            for(Field item: fiels){
                item.setAccessible(true);
                String fieldName = item.getName();
                if(!fieldName.equals("staffId")){
                    Object value = item.get(customerSearchRequest);
                    if (value != null && value != "") {
                        if (item.getType().getName().equals("java.lang.Long") || item.getType().getName().equals("java.lang.Integer")) {
                            where.append(" AND c." + fieldName + "=" + value + " ");
                        } else if (item.getType().getName().equals("java.lang.String")){
                            where.append(" AND c." + fieldName + " LIKE '%" + value + "%' ");
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void specialQuery(CustomerSearchRequest customerSearchRequest,StringBuilder where){

        Long staffId = customerSearchRequest.getStaffId();
        if (staffId!=null) {
            where.append(" AND EXISTS (SELECT * FROM assignmentcustomer WHERE assignmentcustomer.customerid = c.id ");
            where.append(" AND assignmentcustomer.staffid = " + staffId);
            where.append(" ) ");
        }
    }
    @Override
    public List<CustomerEntity> findAllCustomer(CustomerSearchRequest customerSearchRequest) {
        StringBuilder sql = new StringBuilder("Select c.* from customer c ");
        StringBuilder where = new StringBuilder(" WHERE 1=1 ");
        nomalQuery(customerSearchRequest, where);
        specialQuery(customerSearchRequest, where);
        System.out.println(sql);
        where.append(" GROUP BY c.id ");
        sql.append(where);
        Query query = entityManager.createNativeQuery(sql.toString(), CustomerEntity.class);
        return query.getResultList();
    }

    @Override
    public List<CustomerEntity> getAllCustomer(Pageable pageable) {
        StringBuilder sql = new StringBuilder(buildQueryFilter())
                .append(" LIMIT ").append(pageable.getPageSize()).append("\n")
                .append(" OFFSET ").append(pageable.getOffset());
        System.out.println("Final query: " + sql.toString());
        Query query = entityManager.createNativeQuery(sql.toString(), CustomerEntity.class);
        return query.getResultList();
    }

    private String buildQueryFilter() {
        String sql = "SELECT * FROM customer ";
        return sql;
    }
}
