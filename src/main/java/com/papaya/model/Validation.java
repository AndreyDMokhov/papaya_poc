package com.papaya.model;

import lombok.Builder;
import lombok.Data;

import java.util.Set;
@Data
@Builder
public class Validation {

    private Set<String> validationRules;
    private boolean required;
}
