package com.propellerhead.customerapi.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerNotesResponse {
    private List<CustomerNote> noteList;
}
