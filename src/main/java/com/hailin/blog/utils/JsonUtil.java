package com.hailin.blog.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonUtil {

    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String objectToJson(Object object) {
        String result = null;
        try {
            result = objectMapper.writeValueAsString(object);
        }catch (Exception e){
            logger.error("paser json exception" , e);
        }
        return result;
    }

}
