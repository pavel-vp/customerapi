package com.propellerhead.customerapi.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerNoteRequest {
    private Long customerId;
    private CustomerNote note;

}
