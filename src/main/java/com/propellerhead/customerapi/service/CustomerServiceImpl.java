package com.propellerhead.customerapi.service;

import com.propellerhead.customerapi.api.*;
import com.propellerhead.customerapi.dao.CustomerDao;
import com.propellerhead.customerapi.model.CustomerEntity;
import com.propellerhead.customerapi.model.NoteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerDao customerDao;

    @Autowired
    public CustomerServiceImpl(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public List<Customer> getCustomerList(String field, String value, String sortBy, String sortDir) {
        ComposeFilterResult filterResult = CustomerFilterHelper.buildFilter(field, value, sortBy, sortDir);

        List<Customer> result = new ArrayList<>();
        for (CustomerEntity customerEntity : customerDao.getCustomerList(filterResult.getSql(), filterResult.getParams())) {
            result.add(new Customer(customerEntity.getId(), customerEntity.getName(), CustomerStatus.valueOf(customerEntity.getStatus()), customerEntity.getCreateTime(), customerEntity.getContactInfo()));
        }
        return result;
    }

    @Override
    public Customer getCustomer(Long customerId) {
        CustomerEntity customerEntity = customerDao.getCustomerById(customerId);
        if (customerEntity != null) {
            return new Customer(customerEntity.getId(), customerEntity.getName(), CustomerStatus.valueOf(customerEntity.getStatus()), customerEntity.getCreateTime(), customerEntity.getContactInfo());
        }
        return null;
    }

    @Override
    public String changeStatus(Long customerId, CustomerStatus newStatus) {
        try {
            customerDao.changeStatus(customerId, newStatus.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return "An error occurs while changing status";
        }
        return null;
    }

    @Override
    public List<CustomerNote> getCustomerNotes(Long customerId) {
        try {
            List<NoteEntity> noteEntityList = customerDao.getNotesBuCustomerId(customerId);
            List<CustomerNote> result = new ArrayList<>();
            for (NoteEntity entity : noteEntityList) {
                result.add(new CustomerNote(entity.getId(), entity.getCreateTime(), entity.getData()));
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public CustomerNote addNote(Long customerId, CustomerNote note) {
        try {
            NoteEntity noteEntity = customerDao.addNoteEntity(customerId, note.getData());
            return new CustomerNote(noteEntity.getId(), noteEntity.getCreateTime(), noteEntity.getData());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public CustomerNote updateNote(Long customerId, CustomerNote note) {
        try {
            NoteEntity noteEntity = customerDao.updateNoteEntity(customerId, note.getId(), note.getData());
            return new CustomerNote(noteEntity.getId(), noteEntity.getCreateTime(), noteEntity.getData());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteNote(Long customerId, CustomerNote note) {
        try {
            customerDao.deleteNoteEntity(note.getId());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
