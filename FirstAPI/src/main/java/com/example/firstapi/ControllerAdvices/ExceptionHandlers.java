package com.example.firstapi.ControllerAdvices;


import com.example.firstapi.DTO.ExceptionDTO;
import com.example.firstapi.Exceptions.ProductNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // --> This handles the exception only when the controller throws the exception. If try and block  ,
                  // is used to catch the exception in the code means this will not handle exception.
public class ExceptionHandlers {

    @ExceptionHandler(ArithmeticException.class)                          // --> this will handle arithmetic exception globally as any exception
    public ResponseEntity<Void> handleArithmeticException(){             // happening inside backend if not handled will be thrown via controller ,
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);  // so at that time this will handle it.

    }

    @ExceptionHandler(ProductNotExistException.class)
    public ResponseEntity<ExceptionDTO> handleProductNotExistException(ProductNotExistException exception){
        ExceptionDTO dto = new ExceptionDTO();
        dto.setMessage(exception.getMessage());
        return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);

    }



}
