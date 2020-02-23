package com.papaya;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.Objects;

@AllArgsConstructor
@Getter
public enum ValidationRule {
    EMAIL(1, JSR303.EMAIL, JsonSchemaValidation.EMAIL  ),
    MIN_LENGTH(2, JSR303.SIZE_MIN, JsonSchemaValidation.MIN_LENGTH ),
    MAX_LENGTH(3, JSR303.SIZE_MAX, JsonSchemaValidation.MAX_LENGTH),
    MINIMUM(3, JSR303.DECIMAL_MAX, JsonSchemaValidation.MAXIMUM),
    MAXIMUM(4, JSR303.DECIMAL_MIN, JsonSchemaValidation.MINIMUM),
    MAX_ITEMS(5, JSR303.SIZE_MAX, JsonSchemaValidation.MAX_ITEMS),
    MIN_ITEMS(6, JSR303.SIZE_MAX, JsonSchemaValidation.MIN_ITEMS),
    MAX(7, JSR303.MAX, JsonSchemaValidation.MAXIMUM),
    MIN(8, JSR303.MIN, JsonSchemaValidation.MINIMUM);

    public static String getJsonSchemaAttribute(JSR303 jsr303) {
      return Objects.requireNonNull(Arrays.stream(values())
              .filter(x -> x.jsr303.equals(jsr303))
              .findFirst()
              .orElse(null))
              .getJsonSchemaAttribute().getName();

    }

    public static String getJsr303ClassName(JsonSchemaValidation jsonSchemaValidation) {
        return Objects.requireNonNull(Arrays.stream(values())
                .filter(x -> x.getJsonSchemaAttribute().equals(jsonSchemaValidation))
                .findFirst()
                .orElse(null))
                .getJsr303().getAnnotationClass();
    }

    private Integer id;
    private JSR303 jsr303;
    private JsonSchemaValidation jsonSchemaAttribute;


}



@AllArgsConstructor
@Getter
enum JsonSchemaValidation {

    MIN_LENGTH("minLength"),
    MAX_LENGTH("maxLength"),
    PATTERN("pattern"),
    EMAIL("email"),

    MAXIMUM("maximum"),
    MINIMUM("minimum"),
    MULTIPLE_OF("multipleOf"),         // ???
    EXCLUSIVE_MAX("exclusiveMaximum"), // ???
    EXCLUSIVE_MIN("exclusiveMinimum"), // ???

    MAX_ITEMS("maxItems"),
    MIN_ITEMS("minItems"),
    UNIQUE_ITEMS("uniqueItems"),
    MAX_CONTAINS("maxContains"),

    MEDIATYPE("contentMediaType"),
    CONTENT_ENCODING("contentEncoding"),
    URI("uri")
    ;

    private String name;
}

@AllArgsConstructor
@Getter
enum JSR303 {
    SIZE_MIN("javax.validation.constraints.Size"),
    SIZE_MAX("javax.validation.constraints.Size"),
    PATTERN("javax.validation.constraints.Pattern"),
    EMAIL("javax.validation.constraints.Email"),
    ASSERT_TRUE("javax.validation.constraints.AssertTrue"),
    ASSERT_FALSE("javax.validation.constraints.AssertFalse"),
    DECIMAL_MAX("javax.validation.constraints.DecimalMax"),
    DECIMAL_MIN("javax.validation.constraints.DecimalMin"),
    DIGIT("javax.validation.constraints.Digits"),
    FUTURE("javax.validation.constraints.Future"),
    FUTURE_OR_PRESENT("javax.validation.constraints.FutureOrPresent"),
    MAX("javax.validation.constraints.Max"),
    MIN("javax.validation.constraints.MIN"),
    NEGATIVE("javax.validation.constraints.Negative"),
    NEGATIVE_OR_ZERO("javax.validation.constraints.NegativeOrZero"),
    NOT_BLANK("javax.validation.constraints.NotBlank"),
    NOT_EMPTY("javax.validation.constraints.NotEmpty"),
    NOT_NULL("javax.validation.constraints.NotNull"),
    NULL("javax.validation.constraints.Null"),
    PAST("javax.validation.constraints.Past"),
    PAST_OR_PRESENT("javax.validation.constraints.PastOrPresent"),
    POSITIVE("javax.validation.constraints.PastOrPresent"),
    POSITIVE_OR_ZERO("javax.validation.constraints.PositiveOrZero");

    private String annotationClass;
}




