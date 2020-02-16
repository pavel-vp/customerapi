package com.propellerhead.customerapi.service;

import com.propellerhead.customerapi.api.*;

import java.util.List;

public interface CustomerService {

    List<Customer> getCustomerList(String field, String value, String sortBy, String sortDir);

    Customer getCustomer(Long customerId);

    String changeStatus(Long customerId, CustomerStatus newStatus);

    List<CustomerNote> getCustomerNotes(Long customerId);

    CustomerNote addNote(Long customerId, CustomerNote note);

    CustomerNote updateNote(Long customerId, CustomerNote note);

    boolean deleteNote(Long customerId, CustomerNote note);
}
