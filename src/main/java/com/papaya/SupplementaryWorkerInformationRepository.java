package com.papaya;

import com.papaya.model.FieldTemplate;
import com.papaya.model.FieldValue;
import com.papaya.model.SupplementaryWorkerInformation;

import java.util.HashMap;
import java.util.Map;

public class SupplementaryWorkerInformationRepository {

    public static String getJsonSchema() {
        return "{\n" +
                "  \"definitions\": {},\n" +
                "  \"$schema\": \"http://json-schema.org/draft-07/schema#\",\n" +
                "  \"$id\": \"SupplementaryWorkerInformation.class\",\n" +
                "  \"type\": \"object\",\n" +
                "  \"title\": \"SupplementaryWorkerInformation\",\n" +
                "  \"default\": null,\n" +
                "  \"required\": [\n" +
                "    \"firstName\",\n" +
                "    \"lastName\",\n" +
                "    \"primaryEmail\",\n" +
                "    \"companyName\",\n" +
                "    \"homeAddress\",\n" +
                "    \"bankName\",\n" +
                "    \"iban\",\n" +
                "    \"swift\",\n" +
                "    \"bankAccountNumber\",\n" +
                "    \"bankCode\",\n" +
                "    \"branchName\",\n" +
                "    \"branchCode\"\n" +
                "  ],\n" +
                "  \"properties\": {\n" +
                "    \"firstName\": {\n" +
                "      \"$id\": \"#/properties/firstName\",\n" +
                "      \"type\": \"string\",\n" +
                "      \"title\": \"firstName\",\n" +
                "      \"default\": \"\",\n" +
                "      \"minLength\": 2,\n" +
                "      \"maxLength\": 50\n" +
                "    },\n" +
                "    \"lastName\": {\n" +
                "      \"$id\": \"#/properties/lastName\",\n" +
                "      \"type\": \"string\",\n" +
                "      \"title\": \"lastName\",\n" +
                "      \"default\": \"\",\n" +
                "     \"minLength\": 2,\n" +
                "      \"maxLength\": 50\n" +
                "    },\n" +
                "    \"primaryEmail\": {\n" +
                "      \"$id\": \"#/properties/primaryEmail\",\n" +
                "      \"type\": \"string\",\n" +
                "      \"title\": \"primaryEmail\",\n" +
                "      \"default\": \"\",\n" +
                "      \"examples\": [\n" +
                "        \"info@papaya.com\"\n" +
                "      ],\n" +
                "      \"format\": \"email\"\n" +
                "    },\n" +
                "    \"companyName\": {\n" +
                "      \"$id\": \"#/properties/companyName\",\n" +
                "      \"type\": \"string\",\n" +
                "      \"title\": \"companyName\",\n" +
                "      \"default\": \"\",\n" +
                "      \"examples\": [\n" +
                "        \"Papaya\"\n" +
                "      ],\n" +
                "      \"minLength\": 2,\n" +
                "      \"maxLength\": 50\n" +
                "    },\n" +
                "    \"homeAddress\": {\n" +
                "      \"$id\": \"#/properties/homeAddress\",\n" +
                "      \"type\": \"string\",\n" +
                "      \"title\": \"homeAddress\",\n" +
                "      \"default\": \"\",\n" +
                "      \"minLength\": 2,\n" +
                "      \"maxLength\": 255\n" +
                "    },\n" +
                "    \"bankName\": {\n" +
                "      \"$id\": \"#/properties/bankName\",\n" +
                "      \"type\": \"string\",\n" +
                "      \"title\": \"BankName\",\n" +
                "      \"default\": \"\",\n" +
                "      \"examples\": [\n" +
                "        \"Leumi\"\n" +
                "      ],\n" +
                "      \"minLength\": 2,\n" +
                "      \"maxLength\": 50\n" +
                "    },\n" +
                "    \"iban\": {\n" +
                "      \"$id\": \"#/properties/iban\",\n" +
                "      \"type\": \"string\",\n" +
                "      \"title\": \"iban\",\n" +
                "      \"default\": \"\",\n" +
                "      \"examples\": [\n" +
                "        \"AA00 1111 2222 3333 4444 55\"\n" +
                "      ],\n" +
                "      \"minLength\": 22,\n" +
                "      \"maxLength\": 34,\n" +
                "      \"pattern\": \"^AA+.*$\"\n" +
                "    },\n" +
                "    \"swift\": {\n" +
                "      \"$id\": \"#/properties/swift\",\n" +
                "      \"type\": \"string\",\n" +
                "      \"title\": \"swift\",\n" +
                "      \"default\": \"\",\n" +
                "     \"minLength\": 8,\n" +
                "      \"maxLength\": 11\n" +
                "    },\n" +
                "    \"bankAccountNumber\": {\n" +
                "      \"$id\": \"#/properties/bankAccountNumber\",\n" +
                "      \"type\": \"integer\",\n" +
                "      \"title\": \"bankAccountNumber\",\n" +
                "      \"default\": 0,\n" +
                "      \"minimum\": 100000,\n" +
                "      \"maximum\": 999999\n" +
                "    },\n" +
                "    \"bankCode\": {\n" +
                "      \"$id\": \"#/properties/bankCode\",\n" +
                "      \"type\": \"integer\",\n" +
                "      \"title\": \"bankCode\",\n" +
                "      \"default\": \"\",\n" +
                "      \"minimum\": 1000,\n" +
                "      \"maximum\": 9990\n" +
                "    },\n" +
                "    \"branchName\": {\n" +
                "      \"$id\": \"#/properties/branchName\",\n" +
                "      \"type\": \"string\",\n" +
                "      \"title\": \"branchName\",\n" +
                "      \"default\": \"\"\n" +
                "      \n" +
                "    },\n" +
                "    \"branchCode\": {\n" +
                "      \"$id\": \"#/properties/branchCode\",\n" +
                "      \"type\": \"integer\",\n" +
                "      \"title\": \"branchCode\",\n" +
                "      \"default\": 0,\n" +
                "      \"minimum\": 1000,\n" +
                "      \"maximum\": 9999\n" +
                "    },\n" +
                "    \"additoinalBankAccount\": {\n" +
                "      \"$id\": \"#/properties/additoinalBankAccount\",\n" +
                "      \"type\": \"object\",\n" +
                "      \"title\": \"additoinalBankAccount\",\n" +
                "      \"required\": [\n" +
                "        \"bankName\",\n" +
                "        \"iban\",\n" +
                "        \"swift\",\n" +
                "        \"bankAccountNumber\",\n" +
                "        \"bankCode\",\n" +
                "        \"branchName\",\n" +
                "        \"branchCode\"\n" +
                "      ],\n" +
                "      \"properties\": {\n" +
                "        \"bankName\": {\n" +
                "          \"$id\": \"#/properties/additoinalBankAccount/properties/bankName\",\n" +
                "          \"type\": \"string\",\n" +
                "          \"title\": \"bankName\",\n" +
                "          \"default\": \"\",\n" +
                "          \"minLength\": 2,\n" +
                "          \"maxLength\": 50\n" +
                "        },\n" +
                "        \"iban\": {\n" +
                "          \"$id\": \"#/properties/additoinalBankAccount/properties/iban\",\n" +
                "          \"type\": \"string\",\n" +
                "          \"title\": \"iban\",\n" +
                "          \"default\": \"\",\n" +
                "          \"examples\": [\n" +
                "            \"AA00 1111 2222 3333 4444 55\"\n" +
                "          ],\n" +
                "          \"minLength\": 22,\n" +
                "          \"maxLength\": 34,\n" +
                "          \"pattern\": \"^AA+.*$\"\n" +
                "        },\n" +
                "        \"swift\": {\n" +
                "          \"$id\": \"#/properties/additoinalBankAccount/properties/swift\",\n" +
                "          \"type\": \"string\",\n" +
                "          \"title\": \"swift\"\n" +
                "\n" +
                "        },\n" +
                "        \"bankAccountNumber\": {\n" +
                "          \"$id\": \"#/properties/additoinalBankAccount/properties/bankAccountNumber\",\n" +
                "          \"type\": \"integer\",\n" +
                "          \"title\": \"bankAccountNumber\",\n" +
                "          \"default\": 0\n" +
                "        },\n" +
                "        \"bankCode\": {\n" +
                "          \"$id\": \"#/properties/additoinalBankAccount/properties/bankCode\",\n" +
                "          \"type\": \"string\",\n" +
                "          \"title\": \"bankCode\",\n" +
                "          \"default\": \"\"\n" +
                "        },\n" +
                "        \"branchName\": {\n" +
                "          \"$id\": \"#/properties/additoinalBankAccount/properties/branchName\",\n" +
                "          \"type\": \"string\",\n" +
                "          \"title\": \"branchName\",\n" +
                "          \"default\": \"\"\n" +
                "        },\n" +
                "        \"branchCode\": {\n" +
                "          \"$id\": \"#/properties/additoinalBankAccount/properties/branchCode\",\n" +
                "          \"type\": \"integer\",\n" +
                "          \"title\": \"branchCode\",\n" +
                "          \"default\": 0,\n" +
                "      \"minimum\": 1000,\n" +
                "      \"maximum\": 9999\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}\n";
    }

    public static String getJson() {
        return "{\"firstName\":\"John\",\"lastName\":\"Doe\",\"primaryEmail\":\"info@papaya.com\",\"companyName\":\"Papaya\",\"homeAddress\":\"Harish\",\"bankName\":\"Mizrahi\",\"iban\":\"AA00 1111 2222 3333 4444 55\",\"swift\":\"123456789h\",\"bankAccountNumber\":123456,\"bankCode\":1234,\"branchName\":\"Eilat\",\"branchCode\":1234,\"AdditionalBankAccount\":{\"bankCode\":1234,\"iban\":\"AA00 1111 2222 3333 4444 55\",\"branchName\":\"Eilat\",\"bankName\":\"Leumi\",\"bankAccountNumber\":123456,\"swift\":\"AA00 1111 2222 3333 4444 55\"}}";
    }

    public static SupplementaryWorkerInformation getSupplementaryWorkerInformation() {
        return SupplementaryWorkerInformation.builder()
                .companyName("Papaya")
                .firstName("John")
                .lastName("Doe")
                .homeAddress("Harish")
                .primaryEmail("info@papaya.com")
                .bankName("Mizrahi")
                .iban("AA00 1111 2222 3333 4444 55")
                .swift("123456789h")
                .bankAccountNumber(123456)
                .bankCode(1234)
                .branchCode(1234)
                .branchName("Eilat")
                .formFields(createDynamicField())
                .build();
    }

    private static Map<String, FieldValue> createDynamicField() {
        Map<String, FieldValue> formFields = new HashMap<>();
        Map<String, FieldValue> nestedBankAccount = new HashMap<>();

        nestedBankAccount.put("bankName", FieldValue.builder()
                .name("bankName")
                .fieldTemplate(FieldTemplate.builder()
                        .label("bankName")
                        .type(JavaTypes.STRING.getJavaScriptName())
                        .build())
                .value("Leumi")
                .build());
        nestedBankAccount.put("iban", FieldValue.builder()
                .name("bankName")
                .fieldTemplate(FieldTemplate.builder()
                        .label("iban")
                        .type(JavaTypes.STRING.getJavaScriptName())
                        .build())
                .value("AA00 1111 2222 3333 4444 55")
                .build());
        nestedBankAccount.put("swift", FieldValue.builder()
                .name("swift")
                .fieldTemplate(FieldTemplate.builder()
                        .label("swift")
                        .type(JavaTypes.STRING.getJavaScriptName())
                        .build())
                .value("AA00 1111 2222 3333 4444 55")
                .build());
        nestedBankAccount.put("bankCode", FieldValue.builder()
                .name("bankCode")
                .fieldTemplate(FieldTemplate.builder()
                        .label("bankCode")
                        .type(JavaTypes.INTEGER.getJavaScriptName())
                        .build())
                .value("1234")
                .build());
        nestedBankAccount.put("branchName", FieldValue.builder()
                .name("branchName")
                .fieldTemplate(FieldTemplate.builder()
                        .label("branchName")
                        .type(JavaTypes.STRING.getJavaScriptName())
                        .build())
                .value("Eilat")
                .build());
        nestedBankAccount.put("bankAccountNumber", FieldValue.builder()
                .name("bankAccountNumber")
                .fieldTemplate(FieldTemplate.builder()
                        .label("bankAccountNumber")
                        .type(JavaTypes.INTEGER.getJavaScriptName())
                        .build())
                .value("123456")
                .build());

        formFields.put("AdditionalBankAccount", FieldValue.builder()
                .fieldTemplate(FieldTemplate.builder()
                        .type(JavaTypes.OBJECT.getJavaScriptName())
                        .label("AdditionalBankAccount")
                        .build())
                .nestedFields(nestedBankAccount)
                .build());


        return formFields;

    }
}
