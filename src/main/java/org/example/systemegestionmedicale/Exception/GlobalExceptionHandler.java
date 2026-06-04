package org.example.systemegestionmedicale.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String , String> handleValidationExceptions(MethodArgumentNotValidException ex){
        Map<String , String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName , errorMessage);
        });

        return errors;
    }
    @ExceptionHandler(org.springframework.security.authentication.BadCredentialsException.class)
       @ResponseStatus(org.springframework.http.HttpStatus.UNAUTHORIZED)
         public Map<String, String> handleBadCredentials(org.springframework.security.authentication.BadCredentialsException ex) {
                Map<String, String> error = new HashMap<>();
               error.put("error", "Nom d'utilisateur ou mot de passe incorrect");
                return error;
            }

                @ExceptionHandler(org.springframework.security.access.AccessDeniedException.class)
       @ResponseStatus(org.springframework.http.HttpStatus.FORBIDDEN)
        public Map<String, String> handleAccessDenied(org.springframework.security.access.AccessDeniedException ex) {
                 Map<String, String> error = new HashMap<>();
                error.put("error", "Vous n'avez pas la permission d'accéder à cette ressource");
                return error;
             }
}
