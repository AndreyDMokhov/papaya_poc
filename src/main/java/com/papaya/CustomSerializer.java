package com.papaya;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.papaya.model.FieldValue;
import lombok.SneakyThrows;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class CustomSerializer<T> extends StdSerializer<T> {

   public CustomSerializer() {
        this(null);
    }

    public CustomSerializer(Class<T> t) {
        super(t);
    }

    @SneakyThrows
    public void serialize(Object value, JsonGenerator gen, SerializerProvider provider) throws IOException {

        Map<String, JsonSerializer<?>> numberSerializers = new HashMap<>();

        gen.writeStartObject();

        Field[] fields = value.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if(field.get(value) != null && Map.class.isAssignableFrom(field.getType())) {
                writeDynamicValue((Map<String, FieldValue>) field.get(value),gen);
            } else if(field.get(value) != null) {
                writeSimpleValue(field, value, gen);
            }
        }

        gen.writeEndObject();
    }

    private void writeSimpleValue(Field field, Object value, JsonGenerator gen ) throws IOException, IllegalAccessException {


           if(field.getType() == Boolean.TYPE || field.getType() == Boolean.class) {
               gen.writeBooleanField(field.getName(), field.getBoolean(value));
           } else if(field.getType() == Character.TYPE || field.getType() == Character.class) {
               gen.writeStringField(field.getName(), String.valueOf(field.getChar(value)));
           } else if(field.getType() == Byte.TYPE || field.getType() == Byte.class) {
               gen.writeNumberField(field.getName(), field.getByte(value));
           }  else if(field.getType() == Short.TYPE || field.getType() == Short.class) {
               gen.writeNumberField(field.getName(), field.getShort(value));
           } else if(field.getType() == Integer.TYPE || field.getType() == Integer.class) {
               gen.writeNumberField(field.getName(), field.getInt(value));
           } else if(field.getType() == Long.TYPE|| field.getType() == Long.class) {
               gen.writeNumberField(field.getName(), field.getLong(value));
           } else if(field.getType() == Float.TYPE || field.getType() == Float.class) {
               gen.writeNumberField(field.getName(), field.getFloat(value));
           } else if(field.getType() == Double.TYPE || field.getType() == Double.class) {
               gen.writeNumberField(field.getName(), field.getDouble(value));
           }else  {
               gen.writeStringField(field.getName(), (String) field.get(value));
           }

    }

    private void writeDynamicValue(Map<String, FieldValue> nestedFields, JsonGenerator gen) throws IOException {
        for (Map.Entry<String, FieldValue> fieldValueMap : nestedFields.entrySet()) {
            FieldValue fieldValue = fieldValueMap.getValue();
            if (fieldValue.getValue() != null){

               writeValueByType(fieldValue, gen);

            } else if (fieldValue.getNestedFields() != null) {
                gen.writeObjectFieldStart(fieldValue.getFieldTemplate().getLabel());
                writeDynamicValue(fieldValue.getNestedFields(), gen);
                gen.writeEndObject();
            }

        }
    }

    private void writeValueByType(FieldValue fieldValue, JsonGenerator gen) throws IOException {

        String type = fieldValue.getFieldTemplate().getType();

        if (type.equals(JavaTypes.INTEGER.getJavaScriptName())) {
           gen.writeNumberField(fieldValue.getFieldTemplate().getLabel(), Integer.parseInt(fieldValue.getValue()));
       } else if (type.equals(JavaTypes.LONG.getJavaScriptName())) {
           gen.writeNumberField(fieldValue.getFieldTemplate().getLabel(), Long.parseLong(fieldValue.getValue()));
       } else if (type.equals(JavaTypes.DOUBLE.getJavaScriptName())) {
           gen.writeNumberField(fieldValue.getFieldTemplate().getLabel(), Double.parseDouble(fieldValue.getValue()));
       } else if (type.equals(JavaTypes.BOOLEAN.getJavaScriptName())) {
            gen.writeBooleanField(fieldValue.getFieldTemplate().getLabel(), Boolean.parseBoolean(fieldValue.getValue()));
        } else {
            gen.writeStringField(fieldValue.getFieldTemplate().getLabel(), fieldValue.getValue());

        }

    }

}