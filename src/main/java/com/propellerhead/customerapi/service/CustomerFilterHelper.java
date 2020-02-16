package com.propellerhead.customerapi.service;

import com.propellerhead.customerapi.api.CustomerField;
import com.propellerhead.customerapi.api.CustomerSortDirection;

import java.util.HashMap;
import java.util.Map;

public class CustomerFilterHelper {
    public static ComposeFilterResult buildFilter(String field, String value, String sortBy, String sortDir) {
        StringBuilder sqlSb = new StringBuilder();
        Map<String, Object> params = new HashMap<>();

        sqlSb.append("select * from customers where 1=1 ");

        if (field != null && CustomerField.valueOf(field.toUpperCase()) != null) {
            sqlSb.append(" and " + field + " like :param");
            params.put("param", value);
        }
        if (sortBy != null && CustomerField.valueOf(sortBy.toUpperCase()) != null) {
            if (CustomerSortDirection.valueOf(sortDir) == CustomerSortDirection.DESC) {
                sqlSb.append("order by " + sortBy + " desc");
            } else {
                sqlSb.append("order by " + sortBy);
            }
        }

        return new ComposeFilterResult(sqlSb.toString(), params);
    }

}
