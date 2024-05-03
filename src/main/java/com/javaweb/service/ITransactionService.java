package com.javaweb.service;

import com.javaweb.model.dto.TransactionDTO;

import java.util.List;

public interface ITransactionService {
    List<TransactionDTO> getTransactions(Long customerId, String code);
    void saveTransaction(TransactionDTO transaction);
}
