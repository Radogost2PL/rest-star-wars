package dan.was.com.example.rest.star.wars.exceptions;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class OutOfBoundException extends RuntimeException {

    private String message;
}
