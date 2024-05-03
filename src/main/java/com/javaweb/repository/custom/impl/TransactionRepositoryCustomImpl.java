package com.javaweb.repository.custom.impl;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.repository.custom.TransactionRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collections;
import java.util.List;

@Repository
public class TransactionRepositoryCustomImpl implements TransactionRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<TransactionEntity> getTransactions(Long customerId, String code) {
        String sql = "select t.* from transaction t inner join customer c on t.customerid = c.id where c.id = "+customerId+ " and t.code = '"+code+"'";
        Query query = entityManager.createNativeQuery(sql, TransactionEntity.class);
        return query.getResultList();
    }

}
