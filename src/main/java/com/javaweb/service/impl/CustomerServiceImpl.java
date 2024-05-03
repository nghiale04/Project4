package com.javaweb.service.impl;

import com.javaweb.converter.CustomerConverter;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.AssignmentCustomerDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.TransactionRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.IBuildingService;
import com.javaweb.service.ICustomerService;
import com.javaweb.service.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class CustomerServiceImpl implements ICustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private CustomerConverter customerConverter;
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<CustomerSearchResponse> getAllCustomers(Pageable pageable) {
        List<CustomerEntity> findAllCustomers = customerRepository.getAllCustomer(pageable);
        List<CustomerSearchResponse> result = new ArrayList<>();
        for (CustomerEntity it : findAllCustomers) {
            CustomerSearchResponse customerSearchResponse = customerConverter.toCustomerDTO(it);
            if(customerSearchResponse.getIsactive() == 1){
                result.add(customerSearchResponse);
            }
        }
        System.out.println(result.size());
        return result;
    }

    @Override
    public List<CustomerSearchResponse> findAllCustomers(CustomerSearchRequest customerSearchRequest) {
        List<CustomerEntity> findAllCustomers = customerRepository.findAllCustomer(customerSearchRequest);
        List<CustomerSearchResponse> result = new ArrayList<>();
        for (CustomerEntity it : findAllCustomers) {
            CustomerSearchResponse customerSearchResponse = customerConverter.toCustomerDTO(it);
            result.add(customerSearchResponse);
        }
        return result;
    }

    @Override
    public void addOrUpdateCustomer(CustomerDTO customerDTO) {
        CustomerEntity customerEntity = modelMapper.map(customerDTO, CustomerEntity.class);
        customerEntity.setIsactive(1);
        if (customerDTO.getStatus() == null || customerDTO.getStatus() == ""){
            customerEntity.setStatus("CXL");
        }
        if(customerDTO.getId() != null){
            CustomerEntity customer = customerRepository.findById(customerDTO.getId()).get();
            Date createDate = customer.getCreatedDate();
            String createBy = customer.getCreatedBy();
            customerEntity.setCreatedDate(createDate);
            customerEntity.setCreatedBy(createBy);
        }
        customerRepository.save(customerEntity);
    }

    @Override
    public void deleteCustomer(List<Long> customerIds) {
        List<CustomerEntity> list = customerRepository.findAllByIdIn(customerIds);
        for (CustomerEntity it : list) {
            it.setIsactive(0);
            customerRepository.save(it);
        }
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        CustomerEntity customerEntity = customerRepository.findById(id).get();
        CustomerDTO customerDTO = modelMapper.map(customerEntity, CustomerDTO.class);
        return customerDTO;
    }

    @Override
    public ResponseDTO listStaffs(Long customerId) {
        CustomerEntity customer = customerRepository.findById(customerId).get() ; // trả về một building id
        List<UserEntity> staffs = userRepository.findByStatusAndRoles_Code(1,"STAFF"); // trả về những staff đã được cấp quyền
        List<UserEntity> staffAssignment = customer.getUsers();
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
    public void assignmentCustomer(AssignmentCustomerDTO assignmentCustomerDTO) {
        CustomerEntity customer = customerRepository.findById(assignmentCustomerDTO.getCustomerId()).get();
        List<UserEntity> staffs = userRepository.findByIdIn(assignmentCustomerDTO.getStaffs());
        customer.setUsers(staffs);
        customerRepository.save(customer);
    }

    @Override
    public Integer totalCustomers(CustomerSearchRequest customerSearchRequest) {
        List<CustomerEntity> customers = customerRepository.findAllCustomer(customerSearchRequest);
        List<CustomerEntity> result = new ArrayList<>();
        for(CustomerEntity it : customers){
            if (it.getIsactive()==1){
                result.add(it);
            }
        }
        return result.size();
    }


}
