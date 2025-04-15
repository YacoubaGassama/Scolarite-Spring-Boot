package uahb.m1gl.gestionscolarite.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uahb.m1gl.gestionscolarite.dto.ErrorDTO;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    //erreur 500 =internal_server_error
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    @ExceptionHandler(value = {Exception.class})
    public ErrorDTO handleException(Exception exception){
        return ErrorDTO.builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value()+"")
                .message(exception.getMessage())
                .build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    @ExceptionHandler(value = {ScolariteNotFoundException.class})
    public ErrorDTO handleException(ScolariteNotFoundException exception){
        return ErrorDTO.builder()
                .code(HttpStatus.NOT_FOUND.value()+"")
                .message(exception.getMessage())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(value = {ScolariteException.class})
    public ErrorDTO handleException(ScolariteException exception){
        return ErrorDTO.builder()
                .code(HttpStatus.BAD_REQUEST.value()+"")
                .message(exception.getMessage())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public Map<String, String> handleValidationsException(MethodArgumentNotValidException exception){
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error)->{
            errors.put(((FieldError) error).getField(), error.getDefaultMessage());
        });
        return errors;
    }

}