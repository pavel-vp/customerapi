package com.propellerhead.customerapi.api;

public interface CustomerAPI {
    // Get a list of customers. Filter and sort the list of customers.
    CustomerInfoResponse getCustomerList(String field, String value, String sortBy, String sortDir);

    // Get full details of a single customer.
    CustomerDetailResponse getCustomerDetail(Long customerId);

    // Change the status of a customer.
    CustomerChangeStatusResponse changeCustomerStatus(CustomerChangeStatusReuest request);

    // Get all notes for a customer.
    CustomerNotesResponse getCustomerNotes(Long customerId);

    // Add, edit and delete a note for a customer
    CustomerNoteActionResult addNode(CustomerNoteRequest request);
    CustomerNoteActionResult updateNode(CustomerNoteRequest request);
    CustomerNoteActionResult deleteNode(CustomerNoteRequest request);

}
