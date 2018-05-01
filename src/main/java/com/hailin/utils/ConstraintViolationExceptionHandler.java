package com.hailin.utils;


import com.google.common.base.Joiner;
import org.springframework.util.CollectionUtils;
import javax.validation.ConstraintViolationException;

public class ConstraintViolationExceptionHandler {
    public static String getMessage(ConstraintViolationException e) {
        String res = null;
        if(!CollectionUtils.isEmpty(e.getConstraintViolations())){
            res = Joiner.on(";").join(e.getConstraintViolations());
        }
        return res;
    }
}
