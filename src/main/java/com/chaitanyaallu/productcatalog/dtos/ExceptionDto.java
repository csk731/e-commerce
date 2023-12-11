package com.chaitanyaallu.productcatalog.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@Builder
// delombok can convert these annotations in to code.
public class ExceptionDto {
    private HttpStatus errorCode;
    private String message;
}
