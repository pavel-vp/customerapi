package com.propellerhead.customerapi.service;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
public class ComposeFilterResult {
    private String sql;
    private Map<String, Object> params ;
}
