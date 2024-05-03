package com.javaweb.service.impl;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.TransactionRepository;
import com.javaweb.service.ITransactionService;
import org.modelmapper.ModelMapper;
import org.omg.IOP.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
@Service
public class TransactionSeviceImpl implements ITransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public List<TransactionDTO> getTransactions(Long customerId, String code) {
        List<TransactionEntity> list = transactionRepository.getTransactions(customerId, code);
        List<TransactionDTO> result = new ArrayList<>();
        for (TransactionEntity it : list) {
            TransactionDTO transactionDTO = modelMapper.map(it, TransactionDTO.class);
            Long customerID = it.getId();
            transactionDTO.setCustomerId(customerID);
            Date dateModifie = it.getModifiedDate();
            Date dateCreate = it.getCreatedDate();
            if (dateCreate.compareTo(dateModifie)==0){
                transactionDTO.setModifiedBy(null);
                transactionDTO.setModifiedDate(null);
            }
            result.add(transactionDTO);
        }
        return result;
    }

    @Override
    public void saveTransaction(TransactionDTO transaction) {
        TransactionEntity transactionEntity = modelMapper.map(transaction, TransactionEntity.class);
        CustomerEntity customerEntity = customerRepository.findById(transaction.getCustomerId()).get();
        transactionEntity.setCustomer(customerEntity);
        if(transaction.getId() != null){
            TransactionEntity transactionExmp = transactionRepository.findById(transaction.getId()).get();
            Date createDate = transactionExmp.getCreatedDate();
            String createBy = transactionExmp.getCreatedBy();
            transactionEntity.setCreatedBy(createBy);
            transactionEntity.setCreatedDate(createDate);
        }
        transactionRepository.save(transactionEntity);
    }
}
