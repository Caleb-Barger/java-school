package com.lambdaschool.schools.exceptions;

/*
private String title;
private int status;
private String detail;
private Date timestamp;
private String developerMessage;
private List<ValidationError> errors = new ArrayList<>();
*/

import com.lambdaschool.schools.handlers.HelpFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class CustomErrorDetails extends DefaultErrorAttributes {

    @Autowired
    private HelpFunctions helper;

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {

        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);

        Map<String, Object> errorDetails = new LinkedHashMap<>();
        errorDetails.put("title", errorAttributes.get("error"));
        errorDetails.put("status", errorAttributes.get("status"));
        errorAttributes.put("detail", errorAttributes.get("message"));
        errorAttributes.put("timestamp", errorAttributes.get("timestamp"));
        errorAttributes.put("developerMessage", helper.getConstraintViolation(this.getError(webRequest)));

        return errorDetails;
    }
}
