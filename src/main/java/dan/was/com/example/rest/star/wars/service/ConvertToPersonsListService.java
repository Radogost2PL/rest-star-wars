package dan.was.com.example.rest.star.wars.service;

import dan.was.com.example.rest.star.wars.dto.PersonsList;
import dan.was.com.example.rest.star.wars.exceptions.remote.ResourceNotFoundExceptions;
import dan.was.com.example.rest.star.wars.exceptions.remote.InternalErrorException;
import dan.was.com.example.rest.star.wars.responsemodel.PersonResponse;
import dan.was.com.example.rest.star.wars.responsemodel.PersonsListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConvertToPersonsListService {
    @Autowired
    private WebClient.Builder webClientBuilder;
    @Autowired
    ConvertPersonDataService convertPersonDataService;

    @Value("${rest.url.swapi}")
    private String BASE_URL;
    @Value("${rest.url.peopleByPage}")
    private String ALL_PEOPLE_BY_PAGE;


    private static int idCounter = 0;

    public PersonsListResponse convertToPersonsListResponse(int pageNumber) {

        PersonsList personsList = webClientBuilder.build()
                .get()
                .uri(BASE_URL, uriBuilder -> uriBuilder.path(ALL_PEOPLE_BY_PAGE).queryParam("page", pageNumber).build())
                .retrieve()
                .onStatus(HttpStatus::is5xxServerError, error -> Mono.error(new InternalErrorException("Remote server internal error")))
                .onStatus(HttpStatus::is4xxClientError, error -> Mono.error(new ResourceNotFoundExceptions("Remote resource not found")))
                .bodyToMono(PersonsList.class)
                .block();

        List<PersonResponse> personResponsesList = new ArrayList<>();

        switch (pageNumber) {
            case 1:
                idCounter = 0;
                break;
            case 2:
                idCounter = 10;
                break;
            case 3:
                idCounter = 20;
                break;
            case 4:
                idCounter = 30;
                break;
            case 5:
                idCounter = 40;
                break;
            case 6:
                idCounter = 50;
                break;
            case 7:
                idCounter = 60;
                break;
            case 8:
                idCounter = 70;
                break;
            case 9:
                idCounter = 80;
                break;
        }

        personsList.getResults().forEach(person -> {
                idCounter = idCounter + 1;

            PersonResponse personResponse = new PersonResponse(idCounter, person.getName(),
                    person.getBirth_year(),
                    person.getEye_color(),
                    person.getGender(),
                    person.getHair_color(),
                    person.getHeight(),
                    person.getMass(),
                    person.getSkin_color(),
                    convertPersonDataService.getHomeworld(person.getHomeworld()),
                    convertPersonDataService.getCharactersStarships(person.getStarships()));

            personResponsesList.add(personResponse);

        });

        return new PersonsListResponse(9, personsList.getCount(), personResponsesList);
    }
}
