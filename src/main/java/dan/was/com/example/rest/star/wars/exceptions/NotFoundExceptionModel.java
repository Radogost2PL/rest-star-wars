package dan.was.com.example.rest.star.wars.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotFoundExceptionModel {
    private String status;
    private String cause;
    private String [] api_handled_uri;
}
