package com.propellerhead.customerapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerEntity {
    private Long id;
    private String name;
    private String status;
    private Long createTime;
    private String contactInfo;

}
