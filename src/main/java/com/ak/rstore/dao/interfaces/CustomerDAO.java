package com.ak.rstore.dao.interfaces;

import com.ak.rstore.exceptions.RecordAlreadyExistsException;
import com.ak.rstore.model.Customer;

import java.util.List;

public interface CustomerDAO  {
    void createCustomer(Customer customer) throws RecordAlreadyExistsException;
    void saveOrUpdateCustomer(Customer customer);
    boolean deleteCustomer(Customer customer);
    int deleteAllCustomers();
    Customer findCustomerById(int customerId);
    Customer findCustomerByName(String loginName);
    List findAllCustomers();
}
