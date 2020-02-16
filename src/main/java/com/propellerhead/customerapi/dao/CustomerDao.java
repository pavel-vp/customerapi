package com.propellerhead.customerapi.dao;

import com.propellerhead.customerapi.model.CustomerEntity;
import com.propellerhead.customerapi.model.NoteEntity;

import java.util.List;
import java.util.Map;

public interface CustomerDao {
    CustomerEntity getCustomerById(Long customerId);

    void changeStatus(Long customerId, String toString);

    List<NoteEntity> getNotesBuCustomerId(Long customerId);

    NoteEntity addNoteEntity(Long customerId, String data);

    NoteEntity updateNoteEntity(Long customerId, Long id, String data);

    void deleteNoteEntity(Long id);

    List<CustomerEntity> getCustomerList(String sql, Map<String, Object> params);
}
