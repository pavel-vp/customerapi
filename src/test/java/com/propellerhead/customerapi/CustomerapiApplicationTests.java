package com.propellerhead.customerapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.propellerhead.customerapi.api.*;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class CustomerapiApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();


    private String getCustomersFiltered(String query) throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/customer" + query)
        )
                .andExpect(status().isOk())
                .andReturn();
        return mvcResult.getResponse().getContentAsString();
    }

    @Rollback
    @Test
    public void getAllCustomersTest() throws Exception {

        String query = "";

        CustomerInfoResponse response = objectMapper.readValue(getCustomersFiltered(query), CustomerInfoResponse.class);

        Assert.assertEquals(5, response.getCustomerList().size());
    }

    @Rollback
    @Test
    public void getCustomerWithFilterTest() throws Exception {

        String query = "?field=name&value=%a%";

        CustomerInfoResponse response = objectMapper.readValue(getCustomersFiltered(query), CustomerInfoResponse.class);

        Assert.assertEquals(4, response.getCustomerList().size());
    }

    private String getCustomerDetail(Long id) throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/customer/" + id)
        )
                .andExpect(status().isOk())
                .andReturn();
        return mvcResult.getResponse().getContentAsString();
    }

    @Rollback
    @Test
    public void getOneCustomerTest() throws Exception {

        Long customerId = 1l;

        CustomerDetailResponse response = objectMapper.readValue(getCustomerDetail(customerId), CustomerDetailResponse.class);

        Assert.assertEquals(customerId, response.getCustomer().getId());
    }

    private String getCustomerNotes(Long id) throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/customer/" + id+"/notes")
        )
                .andExpect(status().isOk())
                .andReturn();
        return mvcResult.getResponse().getContentAsString();
    }

    @Rollback
    @Test
    public void getCustomerNotesTest() throws Exception {

        Long customerId = 1l;

        CustomerNotesResponse response = objectMapper.readValue(getCustomerNotes(customerId), CustomerNotesResponse.class);

        Assert.assertEquals(2, response.getNoteList().size());
    }

    private String updateStatus(Long id, CustomerStatus status) throws Exception {
        String request = objectMapper.writeValueAsString(new CustomerChangeStatusReuest(id, status));
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders
                        .put("/customer/status")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request)
        )
                .andExpect(status().isOk())
                .andReturn();
        return mvcResult.getResponse().getContentAsString();
    }

    @Rollback
    @Test
    public void updateStatusTest() throws Exception {

        Long customerId = 3l;

        CustomerChangeStatusResponse response = objectMapper.readValue(updateStatus(customerId, CustomerStatus.CURRENT), CustomerChangeStatusResponse.class);

        Assert.assertTrue(response.isSuccess());

        CustomerDetailResponse customerDetailResponse = objectMapper.readValue(getCustomerDetail(customerId), CustomerDetailResponse.class);

        Assert.assertEquals(CustomerStatus.CURRENT, customerDetailResponse.getCustomer().getStatus());


    }


}
