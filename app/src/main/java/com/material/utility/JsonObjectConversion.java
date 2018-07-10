package com.material.utility;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.logging.Logger;


public class JsonObjectConversion {
    Logger jsonConversion = Logger.getLogger(this.getClass().getName());

    public JsonObjectConversion() {
    }

    public Object jsonToObject(String jsonInString, Class classTo) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            if (classTo.getName().contains("BitPesaPaymentMethodOutRes")) {
                mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            }
        }catch (Exception exp){
            //if something nasty happens ignore error and continue
        }
        Object genericClass = new Object();
        try {
            genericClass = mapper.readValue(jsonInString, classTo);
            jsonConversion.info(classTo.getSimpleName()+":");
            jsonConversion.info(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(genericClass));
        } catch (IOException e) {
            jsonConversion.info(e.getMessage());
            return "";
        }
        return genericClass;
    }

    public String objectToJson(Object classTo) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        String jsonInStrings = null;

        try {
            jsonInStrings = mapper.writeValueAsString(classTo);
            Object json = mapper.readValue(jsonInStrings, Object.class);
            jsonConversion.info(classTo.getClass().getSimpleName()+":");
            jsonConversion.info(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return jsonInStrings;
    }
}