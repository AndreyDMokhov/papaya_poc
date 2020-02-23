package com.papaya;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum JavaTypes {
    STRING(1, "String"),
    DOUBLE(2, "double"),
    LONG(3, "long"),
    INTEGER(4, "integer"),
    SHORT(5, "short"),
    BYTE(6, "byte"),
    BOOLEAN(7, "boolean"),
    OBJECT(8, "object");

    private Integer id;
    private String javaScriptName;


}
