package dan.was.com.example.rest.star.wars.exceptions.consumer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class NotFoundExceptionResponse extends RuntimeException {

    private String message;
}
