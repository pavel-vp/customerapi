package com.propellerhead.customerapi.api;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private Long id;
    private String name;
    private CustomerStatus status;
    private long creationTime;
    private String contactInfo;
}
