package com.sumerge.grad.program.jaxrs.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Logger;

import static com.fasterxml.jackson.core.JsonGenerator.Feature.ESCAPE_NON_ASCII;
import static com.fasterxml.jackson.databind.DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY;
import static com.fasterxml.jackson.databind.DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS;
import static com.fasterxml.jackson.databind.DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS;
import static com.fasterxml.jackson.databind.SerializationFeature.INDENT_OUTPUT;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;
import static java.util.logging.Level.SEVERE;

public class Util {

    private static final Logger LOG = Logger.getLogger(Util.class.getName());

    public static String getAsJsonFormatted(Object obj) {
        if (obj != null) {
            try {
                ObjectMapper mapper = getObjectMapper();
                mapper.configure(INDENT_OUTPUT, true);
                if (obj instanceof String) {
                    obj = mapper.readValue(String.valueOf(obj), Object.class);
                }
                return mapper.writeValueAsString(obj);
            } catch (IOException e) {
                LOG.log(SEVERE, e.getMessage(), e);
                if (obj instanceof Object[]) {
                    return Arrays.deepToString((Object[]) obj);
                }
                return obj.toString();
            }
        }
        return null;
    }

    public static ObjectMapper getObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(ESCAPE_NON_ASCII, true);
        mapper.configure(ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        mapper.configure(UNWRAP_SINGLE_VALUE_ARRAYS, true);
        mapper.configure(WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.enable(USE_BIG_DECIMAL_FOR_FLOATS);
        return mapper;
    }

}
