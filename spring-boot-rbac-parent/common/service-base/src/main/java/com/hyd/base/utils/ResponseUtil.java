package com.hyd.base.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponseUtil {
    public static void response(HttpServletResponse httpServletResponse,Response response){
        ObjectMapper objectMapper = new ObjectMapper();
        httpServletResponse.setStatus(HttpStatus.OK.value());
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);

        try {
            objectMapper.writeValue(httpServletResponse.getWriter(),response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
