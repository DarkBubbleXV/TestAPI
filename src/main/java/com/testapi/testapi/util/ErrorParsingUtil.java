package com.testapi.testapi.util;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

public class ErrorParsingUtil {

    public static List<String> parse(Errors errors){
        List<String> message = new ArrayList<>();
        for(ObjectError error : errors.getAllErrors()){
            message.add(error.getDefaultMessage());
        }
        return message;
    }
}
