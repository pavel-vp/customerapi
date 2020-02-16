package com.propellerhead.customerapi.web;

import com.propellerhead.customerapi.api.*;
import com.propellerhead.customerapi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("customer")
public class CustomerController implements CustomerAPI {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @Override
    @RequestMapping(value = "", method = RequestMethod.GET)
    public @ResponseBody CustomerInfoResponse getCustomerList(@RequestParam(value = "field", required = false) String field, @RequestParam(value = "value", required = false) String value, @RequestParam(value = "sortby", required = false) String sortBy, @RequestParam(value = "sortdir", required = false) String sortDir) {

        List<Customer> result = customerService.getCustomerList(field, value, sortBy, sortDir);
        return new CustomerInfoResponse(result);
    }

    @Override
    @RequestMapping(value = "/{customerId}", method = RequestMethod.GET)
    public @ResponseBody CustomerDetailResponse getCustomerDetail(@PathVariable Long customerId) {
        Customer result = customerService.getCustomer(customerId);
        return new CustomerDetailResponse(result);
    }

    @Override
    @RequestMapping(value = "/status", method = RequestMethod.PUT)
    public @ResponseBody CustomerChangeStatusResponse changeCustomerStatus(@RequestBody CustomerChangeStatusReuest request) {
        String result = customerService.changeStatus(request.getCustomerId(), request.getNewStatus());
        return new CustomerChangeStatusResponse(result == null, result);
    }

    @Override
    @RequestMapping(value = "/{customerId}/notes", method = RequestMethod.GET)
    public @ResponseBody CustomerNotesResponse getCustomerNotes(@PathVariable Long customerId) {
        List<CustomerNote> result = customerService.getCustomerNotes(customerId);
        return new CustomerNotesResponse(result);
    }

    @Override
    @RequestMapping(value = "/addnote", method = RequestMethod.POST)
    public @ResponseBody CustomerNoteActionResult addNode(@RequestBody CustomerNoteRequest request) {
        CustomerNote note = customerService.addNote(request.getCustomerId(), request.getNote());
        return new CustomerNoteActionResult(note != null, note == null ? "Error wile creating a note!": null, note);
    }

    @Override
    @RequestMapping(value = "/updatenote", method = RequestMethod.PUT)
    public @ResponseBody CustomerNoteActionResult updateNode(@RequestBody CustomerNoteRequest request) {
        CustomerNote note = customerService.updateNote(request.getCustomerId(), request.getNote());
        return new CustomerNoteActionResult(note != null, note == null ? "Error wile updating a note!": null, note);
    }

    @Override
    @RequestMapping(value = "/deletenote", method = RequestMethod.DELETE)
    public @ResponseBody CustomerNoteActionResult deleteNode(@RequestBody CustomerNoteRequest request) {
        boolean success = customerService.deleteNote(request.getCustomerId(), request.getNote());
        return new CustomerNoteActionResult(success, success ? "Error wile deleting a note!": null, null);
    }
}
