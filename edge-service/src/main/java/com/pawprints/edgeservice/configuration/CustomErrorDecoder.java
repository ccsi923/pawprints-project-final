package com.pawprints.edgeservice.configuration;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import static feign.FeignException.errorStatus;


@Component
public class CustomErrorDecoder implements ErrorDecoder {



    @Override
    public Exception decode(String methodKey, Response response)  {
        if (response.status() >= 400 && response.status() <= 499) {
            /*
            String[] messages = null;
            try {
                messages = new ObjectMapper().readValue(response.body().toString(), String[].class);
            } catch (JsonProcessingException e) {
                e.getMessage();
            }
            System.out.println(messages);
             */
            return new ResponseStatusException(HttpStatus.valueOf(response.status()), "Error caused by " + methodKey);
        }
        /*
        if (response.status() >= 500 && response.status() <= 599) {
            return new StashServerException(
                    response.status(),
                    response.reason()
            );
        }
         */
        return errorStatus(methodKey, response);
    }
}
