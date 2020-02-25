package com.papaya;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.JsonSchemaGenerator;
import com.papaya.model.FieldTemplate;
import com.papaya.model.FormTemplate;
import com.papaya.model.Validation;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class SerializationSchemaService {
    static final ObjectMapper mapper = new ObjectMapper();

    public  JsonSchema getJsonSchema(Class clazz) throws IOException {

        JsonSchemaGenerator schemaGen = new JsonSchemaGenerator(mapper);
        return schemaGen.generateSchema(mapper.constructType(clazz));
    }

    public static void main(String[] args) throws IOException {
        SerializationSchemaService schemaService = new SerializationSchemaService();

        FormTemplate formTemplate = schemaService.createFormTemplate();

        Map<String, FieldTemplate> formFieldsTemplates = formTemplate.getFormFieldsTemplates();

    }

    private FormTemplate createFormTemplate() {
        HashMap<String, FieldTemplate> fieldNestTemplateHashMap = new HashMap<String, FieldTemplate>();
        HashMap<String, FieldTemplate> fieldTemplateHashMap = new HashMap<String, FieldTemplate>();

        FieldTemplate nest_field = FieldTemplate.builder().label("nest_label").type("String").defaultValue("Def_Vel").build();
        fieldNestTemplateHashMap.put("String", nest_field);
        FieldTemplate fieldTemplate = FieldTemplate.builder().type("Object").label("Label_1").description("Field_1").comment("Comment").defaultValue("Def_Value")
                .validation(Validation.builder().build()).nestedFieldsTemplates(fieldNestTemplateHashMap).build();
        fieldTemplateHashMap.put("Label_1", fieldTemplate);

        return FormTemplate.builder().name("Name").description("Description").formFieldsTemplates(fieldTemplateHashMap).build();

    }

}
