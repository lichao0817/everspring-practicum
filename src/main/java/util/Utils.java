package util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {
    public static String getJsonBody(Object o) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String resp = "{\"data\":" + objectMapper.writeValueAsString(o) + "}";
            return resp;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "error";
        }
    }
}
