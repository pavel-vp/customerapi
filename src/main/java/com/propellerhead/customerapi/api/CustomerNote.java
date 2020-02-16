package com.propellerhead.customerapi.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerNote {
    private Long id;
    private Long createTime;
    private String data;
}
