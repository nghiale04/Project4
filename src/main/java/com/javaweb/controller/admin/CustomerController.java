package com.javaweb.controller.admin;

import com.javaweb.constant.SystemConstant;
import com.javaweb.enums.District;
import com.javaweb.enums.StatusType;
import com.javaweb.enums.TransactionType;
import com.javaweb.enums.TypeCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.ICustomerService;
import com.javaweb.service.ITransactionService;
import com.javaweb.service.IUserService;
import com.javaweb.utils.DisplayTagUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller()
public class CustomerController {

    @Autowired
    private IUserService userService;
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private ITransactionService transactionService;
    @RequestMapping(value = "/admin/customer-list", method = RequestMethod.GET)
    public ModelAndView customerList(@ModelAttribute CustomerSearchRequest customerSearchRequest, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/customer/list");
        mav.addObject("modelSearch", customerSearchRequest);
        mav.addObject("listStaffs", userService.getStaffs());

        if(SecurityUtils.getAuthorities().contains("ROLE_STAFF")){
            Long staffId = SecurityUtils.getPrincipal().getId();
            customerSearchRequest.setStaffId(staffId);
            List<CustomerSearchResponse> list = customerService.findAllCustomers(customerSearchRequest);
            List<CustomerSearchResponse> responseList = new ArrayList<>();
            for (CustomerSearchResponse it : list) {
                if(it.getIsactive() == 1){
                    responseList.add(it);
                }
            }
            System.out.println(responseList.size());
            mav.addObject("customers",responseList);
        }
        else{
            List<CustomerSearchResponse> list = customerService.findAllCustomers(customerSearchRequest);
            List<CustomerSearchResponse> responseList = new ArrayList<>();
            for (CustomerSearchResponse it : list) {
                if(it.getIsactive() == 1){
                    responseList.add(it);
                }
            }
            mav.addObject("customers",responseList);
        }
        CustomerSearchResponse model = new CustomerSearchResponse();
        DisplayTagUtils.of(request,model);
        List<CustomerSearchResponse> response = customerService.getAllCustomers(new PageRequest(model.getPage()-1, model.getMaxPageItems()));
        model.setListResult(response);
        model.setTotalItems(customerService.totalCustomers(customerSearchRequest));
        mav.addObject(SystemConstant.MODEL, model);
        return mav;
    }

    @RequestMapping (value = "/admin/customer-edit", method = RequestMethod.GET)
    public ModelAndView customerEdit(@ModelAttribute("customerEdit") CustomerDTO customerDTO, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/customer/edit");
        mav.addObject("listForm", customerDTO);
        mav.addObject("statusType", StatusType.statusType());
        return mav;
    }

    @RequestMapping (value = "/admin/customer-edit-{id}", method = RequestMethod.GET)
    public ModelAndView customerUpdate(@PathVariable("id") Long id, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/customer/edit");
        mav.addObject("transactionType", TransactionType.transactionType());
        mav.addObject("customerEdit", customerService.getCustomerById(id));
        mav.addObject("listCSKH",transactionService.getTransactions(id,"CSKH"));
        mav.addObject("listDDX",transactionService.getTransactions(id,"DDX"));
        mav.addObject("statusType", StatusType.statusType());
        return mav;
    }
}
