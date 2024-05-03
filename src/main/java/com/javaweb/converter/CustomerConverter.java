package com.javaweb.converter;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.enums.StatusType;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CustomerConverter {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CustomerRepository customerRepository;

    public CustomerSearchResponse toCustomerDTO(CustomerEntity customerEntity) {
        CustomerSearchResponse customer = modelMapper.map(customerEntity, CustomerSearchResponse.class);
        Map<String,String> status = StatusType.statusType();
        customer.setStatus(status.get(customerEntity.getStatus()));
        return customer;
    }
}
