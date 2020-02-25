package com.papaya;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.papaya.model.FieldTemplate;
import com.papaya.model.FieldValue;
import com.papaya.model.SupplementaryWorkerInformation;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DynamicFieldTest {

    public static void main(String[] args) throws IOException {
        DynamicFieldTest dynamicFieldTest = new DynamicFieldTest();
        ObjectMapper mapper = new ObjectMapper();

        SupplementaryWorkerInformation form = dynamicFieldTest.createForm();

        SimpleModule module = new SimpleModule();
        module.addSerializer(SupplementaryWorkerInformation.class, new CustomSerializer<>());
        module.addDeserializer(SupplementaryWorkerInformation.class, new CustomDeserializer<>(SupplementaryWorkerInformation.class));

        mapper.registerModule(module);

        String valueAsString = mapper.writeValueAsString(form);
        System.out.println(valueAsString);
        SupplementaryWorkerInformation supplementaryWorkerInformation = mapper.readValue(valueAsString, SupplementaryWorkerInformation.class);

        System.out.println(mapper.writeValueAsString(supplementaryWorkerInformation));

    }

    private SupplementaryWorkerInformation createForm() {
        return SupplementaryWorkerInformation.builder()
                .companyName("Papaya")
                .firstName("Moshe")
                .lastName("Ivanov")
                .homeAddress("Harish")
                .primaryEmail("info@papaya.com")
                .workTime(8)
                .resident(true)
                .formFields(createDynamicField())
                .build();
    }

    private Map<String, FieldValue> createDynamicField() {
        Map<String, FieldValue> fields = new HashMap<>();
        Map<String, FieldValue> nestedFields = new HashMap<>();
        Map<String, FieldValue> nestedLevel_1_Fields = new HashMap<>();


        nestedLevel_1_Fields.put("Name", FieldValue.builder()
                .fieldTemplate(FieldTemplate.builder()
                        .type(JavaTypes.STRING.getJavaScriptName())
                        .label("name")
                        .build())
                .value("Vova")
                .build());
        nestedLevel_1_Fields.put("Age", FieldValue.builder()
                .fieldTemplate(FieldTemplate.builder()
                        .label("Age")
                        .type(JavaTypes.INTEGER.getJavaScriptName())
                        .build())
                .value("10")
                .build());
        nestedLevel_1_Fields.put("School_1", FieldValue.builder()
                .fieldTemplate(FieldTemplate.builder()
                        .label("School_1")
                        .type(JavaTypes.STRING.getJavaScriptName())
                        .build())
                .value("им.Ленинского комсомола")
                .build());

        FieldTemplate familyFieldTempl = FieldTemplate.builder()
                .label("Family")
                .type(JavaTypes.OBJECT.getJavaScriptName())
                .build();
        FieldTemplate wifeNameFieldTempl = FieldTemplate.builder()
                .label("Wife's name")
                .type(JavaTypes.STRING.getJavaScriptName())
                .build();
        FieldTemplate sonFieldTempl = FieldTemplate.builder()
                .label("Son")
                .type(JavaTypes.STRING.getJavaScriptName())
                .build();

        nestedFields.put("Wife", FieldValue.builder()
                .fieldTemplate(wifeNameFieldTempl)
                .value("Sara")
                .build());
        nestedFields.put("Son", FieldValue.builder()
                .fieldTemplate(sonFieldTempl)
                .nestedFields(nestedLevel_1_Fields)
                .build());


        fields.put("Family", FieldValue.builder()
                .fieldTemplate(familyFieldTempl)
                .nestedFields(nestedFields)
                .build());
        fields.put("Hobby", FieldValue.builder()
                .fieldTemplate(FieldTemplate.builder()
                        .label("Hobby").type(JavaTypes.STRING.getJavaScriptName()).build())
                .value("Butterflies")
                .build());

        return fields;

    }
}
