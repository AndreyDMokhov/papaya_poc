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

public class FieldValue {
    private String value;
    private Map<String, FieldValue> nestedFields;
    private FieldTemplate fieldTemplate;
}
