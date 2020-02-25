package com.papaya.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.papaya.CustomDeserializer;
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
    private String bankName;
    private String iban;
    private String swift;
    private int bankAccountNumber;
    private int bankCode;
    private String branchName;
    private int branchCode;
    private Map<String, FieldValue> formFields;

}
