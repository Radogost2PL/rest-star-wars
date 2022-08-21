package dan.was.com.example.rest.star.wars.exceptions;

import dan.was.com.example.rest.star.wars.exceptions.consumer.NoMappingFoundExceptionModel;
import dan.was.com.example.rest.star.wars.exceptions.consumer.NotFoundExceptionResponse;
import dan.was.com.example.rest.star.wars.exceptions.consumer.RemoteServerInternalErrorResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Date;
import java.util.HashMap;

@RestControllerAdvice
public class ExceptionResolver {

    @Value("${example.valid.uri.pages}")
    private String urlPages;
    @Value("${example.valid.uri.character}")
    private String urlCharacter;

    @ExceptionHandler(org.springframework.web.servlet.NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public NoMappingFoundExceptionModel noHandlerFoundException(NoHandlerFoundException e, WebRequest request) {
        NoMappingFoundExceptionModel noMappingFoundExceptionModel = new NoMappingFoundExceptionModel();
        noMappingFoundExceptionModel.setStatus("404");
        noMappingFoundExceptionModel.setCause(e.getLocalizedMessage());
        String[] uriList = new String[]{urlPages, urlCharacter};
        noMappingFoundExceptionModel.setApi_handled_uri(uriList);
        HashMap<String, String> response = new HashMap<>();

        return noMappingFoundExceptionModel;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundExceptionResponse.class)
    @ResponseBody
    protected BasicExceptionTemplate resourceNotFound(NotFoundExceptionResponse ex, WebRequest request) {
        return new BasicExceptionTemplate(new Date(),
                ex.getMessage(), request.getDescription(true));
    }


    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    @ExceptionHandler(RemoteServerInternalErrorResponse.class)
    @ResponseBody
    protected BasicExceptionTemplate remoteServererrorException(RemoteServerInternalErrorResponse ex, WebRequest request) {
        return new BasicExceptionTemplate(new Date()
                , ex.getMessage(), request.getDescription(true));
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    protected BasicExceptionTemplate unknownExceptionHandler(Exception ex, WebRequest request) {
        return new BasicExceptionTemplate(new Date()
                , ex.getMessage(), request.getDescription(true));
    }
}
