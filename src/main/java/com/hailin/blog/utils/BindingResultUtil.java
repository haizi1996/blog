package com.hailin.blog.utils;

import com.google.common.collect.Maps;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Map;
import java.util.stream.Collectors;

public class BindingResultUtil {

    public static Map<String ,String> handleBindingResult(BindingResult bindingResult){
        Map<String , String> resultMap = Maps.newHashMap();
        if(bindingResult.hasErrors()){
            resultMap = bindingResult.getAllErrors().stream()
                    .map(objectError -> (FieldError)objectError)
                    .collect(Collectors.toMap(fieldError -> fieldError.getField() ,
                            fieldError -> fieldError.getDefaultMessage()));
        }
        return resultMap;
    }

}
