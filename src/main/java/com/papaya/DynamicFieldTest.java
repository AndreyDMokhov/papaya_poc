package com.papaya;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.papaya.model.FieldTemplate;
import com.papaya.model.FieldValue;
import com.papaya.model.SupplementaryWorkerInformation;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DynamicFieldTest {

    public static void main(String[] args) throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        SupplementaryWorkerInformation form = SupplementaryWorkerInformationRepository.getSupplementaryWorkerInformation();

        SimpleModule module = new SimpleModule();
        module.addSerializer(SupplementaryWorkerInformation.class, new CustomSerializer<>());
        module.addDeserializer(SupplementaryWorkerInformation.class, new CustomDeserializer<>(SupplementaryWorkerInformation.class));

        mapper.registerModule(module);

        String valueAsString = mapper.writeValueAsString(form);

        System.out.println(valueAsString);
        SupplementaryWorkerInformation supplementaryWorkerInformation = mapper.readValue(valueAsString, SupplementaryWorkerInformation.class);
        System.out.println(mapper.writeValueAsString(supplementaryWorkerInformation));

    }
}
