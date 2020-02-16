package com.propellerhead.customerapi.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerChangeStatusResponse {
    private boolean success;
    private String message;
}
