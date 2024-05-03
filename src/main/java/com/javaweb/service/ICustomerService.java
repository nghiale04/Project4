package com.javaweb.service;

import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.AssignmentCustomerDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ICustomerService {
    List<CustomerSearchResponse> getAllCustomers(Pageable pageable);
    List<CustomerSearchResponse> findAllCustomers(CustomerSearchRequest customerSearchRequest);
    void addOrUpdateCustomer(CustomerDTO customerDTO);
    void deleteCustomer(List<Long> customerIds);
    CustomerDTO getCustomerById(Long id);
    ResponseDTO listStaffs(Long customerId);
    void assignmentCustomer(AssignmentCustomerDTO assignmentCustomerDTO);
    Integer totalCustomers(CustomerSearchRequest customerSearchRequest);

}
