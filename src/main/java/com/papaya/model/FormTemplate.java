package com.papaya.model;

import lombok.*;

import java.util.Map;

@Data
@Builder
public class FormTemplate {
    private String name;
    private String description;
    //enum status can be - ready, active, disabled
    private Status status;
    private Map<String, FieldTemplate> formFieldsTemplates;

}
