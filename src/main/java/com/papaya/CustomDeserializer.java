package com.papaya;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.papaya.model.FieldTemplate;
import com.papaya.model.FieldValue;
import lombok.SneakyThrows;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CustomDeserializer<T> extends StdDeserializer<T> {

    public CustomDeserializer() {
        this(null);
    }

    public CustomDeserializer(Class<?> clazz) {
        super(clazz);
    }

    @SneakyThrows
    @Override
    public T deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode jsonNode = p.getCodec().readTree(p);

        Object instance = super._valueClass.getConstructor().newInstance();
        Class<?> clazz = super._valueClass;

        Map<String, FieldValue> formField = new HashMap<>();       //create map entry for dynamic fields

        Iterator<Map.Entry<String, JsonNode>> fieldsIterator = jsonNode.fields();

        while (fieldsIterator.hasNext()) {
            Map.Entry<String, JsonNode> jsonField = fieldsIterator.next();
            String key = jsonField.getKey();

            try {
                clazz.getDeclaredField(key);
            } catch (NoSuchFieldException e) {
                if(jsonField.getValue().isObject()){
                    formField.put(jsonField.getKey(), FieldValue.builder()
                            .fieldTemplate(FieldTemplate.builder()
                                    .type(JavaTypes.OBJECT.getJavaScriptName())
                                    .label(jsonField.getKey())
                                    .build())
                            .nestedFields(dynamicObjectFieldHandler(jsonField.getValue().fields(), key))
                            .build());
                } else {
                    formField.put(key, dynamicSimpleField(key, jsonField));
                }
                 continue;
            }
            Field field = clazz.getDeclaredField(key);
            if (field != null) {
                field.setAccessible(true);

                field.set(instance,  getValueByType(jsonField) );
            }
        }
        Field formFields = clazz.getDeclaredField("formFields");
        formFields.setAccessible(true);
        formFields.set(instance, formField);

        return (T) instance;
    }

    private Object getValueByType(Map.Entry<String, JsonNode> jsonField) {
        JsonNode jsonNode = jsonField.getValue();
        if(jsonNode.isShort()) {
            return jsonNode.asInt();
        } else if(jsonNode.isInt()) {
              return jsonNode.asInt();
        } else if(jsonNode.isLong()) {
              return jsonNode.asLong();
        } else if(jsonNode.isDouble()) {
            return jsonNode.asDouble();
        } else if (jsonNode.isFloat()) {
            return jsonNode.isFloat();
        } else if (jsonNode.isBoolean()) {
            return jsonNode.asBoolean();
        }
        return jsonNode.asText();
    }

    private Map<String, FieldValue> dynamicObjectFieldHandler(Iterator<Map.Entry<String, JsonNode>> jsonField, String key) {
        Map<String, FieldValue> fieldValueMap = new HashMap<>();

        while (jsonField.hasNext()) {
            Map.Entry<String, JsonNode> nextField = jsonField.next();
            if(nextField.getValue().isObject()) {

                fieldValueMap.put(key, FieldValue.builder()
                        .fieldTemplate(FieldTemplate.builder()
                                .type(JavaTypes.OBJECT.getJavaScriptName())
                                .label(nextField.getKey())
                                .build())
                        .nestedFields(dynamicObjectFieldHandler(nextField.getValue().fields(), nextField.getKey()))
                        .build());
            } else {
                fieldValueMap.put(nextField.getKey(), dynamicSimpleField(nextField.getKey(), nextField));
            }
        }
        return fieldValueMap;
    }

    private FieldValue dynamicSimpleField(String key, Map.Entry<String, JsonNode> jsonField) {
        return FieldValue.builder()
                .fieldTemplate(FieldTemplate.builder()
                        .label(key)
                        .type(getNodeType(jsonField.getValue()))
                        .build())
                .value(String.valueOf(getValueByType(jsonField)))
                .build();
    }

    private String getNodeType(JsonNode jsonNode) {

        if(jsonNode.getNodeType().equals(JsonNodeType.NUMBER)){
            JsonParser.NumberType numberType = jsonNode.numberType();
            if(numberType == JsonParser.NumberType.INT) {
                return JavaTypes.INTEGER.getJavaScriptName();
            } else if (numberType == JsonParser.NumberType.LONG) {
                return JavaTypes.LONG.getJavaScriptName();
        } else if (numberType == JsonParser.NumberType.DOUBLE) {
                return JavaTypes.DOUBLE.getJavaScriptName();
            }
        }
        return jsonNode.getNodeType().name();
    }
}