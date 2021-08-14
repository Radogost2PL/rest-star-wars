package dan.was.com.example.rest.star.wars.responsemodel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PersonsListResponse {
    private int pages;
    private int count;
    private List<PersonResponse> personsList;
}
