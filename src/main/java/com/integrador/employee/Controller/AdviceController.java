package com.integrador.employee.Controller;

import com.integrador.employee.DTO.ErrorDTO;
import com.integrador.employee.Exception.EmployeeNotFoundException;
import com.integrador.employee.Exception.HandlerResponseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdviceController {

    @ExceptionHandler(value = HandlerResponseException.class)
    public ResponseEntity<ErrorDTO> handlerResponseException(HandlerResponseException ex){
        ErrorDTO error = ErrorDTO.builder().code(String.valueOf(ex.getStatus().value())).messaje(ex.getMessage()).build();
        return ResponseEntity.status(ex.getStatus()).body(error);
    }

    @ExceptionHandler(value = EmployeeNotFoundException.class)
    public ResponseEntity<ErrorDTO> employeeNotFoundException(EmployeeNotFoundException ex){
        ErrorDTO error = ErrorDTO.builder().code(String.valueOf(ex.getStatus().value())).messaje(ex.getMessage()).build();
        return ResponseEntity.status(ex.getStatus()).body(error);
    }
}
