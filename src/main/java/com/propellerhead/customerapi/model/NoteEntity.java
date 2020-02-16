package com.propellerhead.customerapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoteEntity  {
    private Long id;
    private Long customerId;
    private Long createTime;
    private String data;
}
