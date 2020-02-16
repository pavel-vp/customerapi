package com.propellerhead.customerapi.dao;

import com.propellerhead.customerapi.model.CustomerEntity;
import com.propellerhead.customerapi.model.NoteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class CustomerDaoMySqlImpl implements CustomerDao {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public CustomerDaoMySqlImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    private RowMapper<CustomerEntity> customerRowMapper = (ResultSet resultSet, int i) -> {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(resultSet.getLong("id"));
        customerEntity.setName(resultSet.getString("name"));
        customerEntity.setStatus(resultSet.getString("status"));
        customerEntity.setCreateTime(resultSet.getLong("createTime"));
        customerEntity.setContactInfo(resultSet.getString("contactInfo"));
        return customerEntity;
    };

    @Override
    public List<CustomerEntity> getCustomerList(String sql, Map<String, Object> params) {
        return namedParameterJdbcTemplate.query(sql, params, customerRowMapper);
    }

    @Override
    public CustomerEntity getCustomerById(Long customerId) {
        String q = "select * from customers where id = :id";
        Map<String, Object> params = new HashMap<>();
        params.put("id", customerId);
        List<CustomerEntity> list = namedParameterJdbcTemplate.query(q, params, customerRowMapper);
        if (list != null && list.size() == 1) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public void changeStatus(Long customerId, String status) {
        CustomerEntity customerEntity = getCustomerById(customerId);
        if (customerEntity == null)
            throw new IllegalStateException("There is no customer with id = " + customerId);
        String q = "update customers set status = :status where id = :id";
        Map<String, Object> params = new HashMap<>();
        params.put("status", status);
        params.put("id", customerId);
        namedParameterJdbcTemplate.update(q, params);
    }

    private RowMapper<NoteEntity> noteRowMapper = (ResultSet resultSet, int i) -> {
        NoteEntity noteEntity = new NoteEntity(resultSet.getLong("id"), resultSet.getLong("customerid"), resultSet.getLong("createTime"), resultSet.getString("data"));
        return noteEntity;
    };

    @Override
    public List<NoteEntity> getNotesBuCustomerId(Long customerId) {
        String q = "select * from notes where customerid=:customerid order by createTime";
        Map<String, Object> params = new HashMap<>();
        params.put("customerid", customerId);
        return namedParameterJdbcTemplate.query(q, params, noteRowMapper);
    }

    private NoteEntity getNote(Long noteId) {
        String q = "select * from notes where id = :id";
        Map<String, Object> params = new HashMap<>();
        params.put("id", noteId);
        List<NoteEntity> list = namedParameterJdbcTemplate.query(q, params, noteRowMapper);
        if (list != null && list.size() == 1) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public NoteEntity addNoteEntity(Long customerId, String data) {
        String q = "insert into notes (customerId, data) values(?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.getJdbcTemplate().update(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps =
                                connection.prepareStatement(q, new String[] {"id"});
                        ps.setLong(1, customerId);
                        ps.setString(2, data);
                        return ps;
                    }
                },
                keyHolder);
        Long noteId = keyHolder.getKey().longValue();
        return getNote(noteId);
    }

    @Override
    public NoteEntity updateNoteEntity(Long customerId, Long id, String data) {
        NoteEntity newNote = getNote(id);

        String q = "update notes set data = :data where id = :id";
        Map<String, Object> params = new HashMap<>();
        params.put("data", data);
        params.put("id", id);
        namedParameterJdbcTemplate.update(q, params);

        newNote.setData(data);
        return newNote;
    }

    @Override
    public void deleteNoteEntity(Long id) {
        String q = "delete from notes where id = :id";
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        namedParameterJdbcTemplate.update(q, params);
    }


}
