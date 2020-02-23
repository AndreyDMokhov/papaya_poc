package com.papaya.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.papaya.CustomSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SupplementaryWorkerInformation {
    private String firstName;
    private String lastName;
    private String primaryEmail;
    private String companyName;
    private String homeAddress;
    private int workTime;
    private boolean resident;
    private Map<String, FieldValue> formFields;
    private Status status;
}
