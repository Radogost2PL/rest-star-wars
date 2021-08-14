package dan.was.com.example.rest.star.wars.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;

@RestControllerAdvice
public class ExceptionResolver {


    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public NotFoundExceptionModel noHandlerFoundException(NoHandlerFoundException e, WebRequest request) {
        NotFoundExceptionModel notFoundExceptionModel = new NotFoundExceptionModel();
        notFoundExceptionModel.setStatus("404");
        notFoundExceptionModel.setCause(e.getLocalizedMessage());
        String[] uriList = new String[]{"http://localhost:8080/characters?page=x/", "http://localhost:8080/characters/{id}"};


        notFoundExceptionModel.setApi_handled_uri(uriList);
        HashMap<String, String> response = new HashMap<>();

        return notFoundExceptionModel;
    }
}
