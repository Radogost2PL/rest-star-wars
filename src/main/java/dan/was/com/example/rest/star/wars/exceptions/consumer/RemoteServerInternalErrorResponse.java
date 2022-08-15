package dan.was.com.example.rest.star.wars.exceptions.consumer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ResponseStatus(HttpStatus.PAYMENT_REQUIRED)
public class RemoteServerInternalErrorResponse extends RuntimeException{
    private String message;


}
