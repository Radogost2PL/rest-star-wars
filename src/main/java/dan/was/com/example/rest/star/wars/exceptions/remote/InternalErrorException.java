package dan.was.com.example.rest.star.wars.exceptions.remote;

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
public class InternalErrorException extends RuntimeException {
    private String message;
}
