package dan.was.com.example.rest.star.wars.exceptions.consumer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NoMappingFoundExceptionModel {
    private String status;
    private String cause;
    private String [] api_handled_uri;
}
