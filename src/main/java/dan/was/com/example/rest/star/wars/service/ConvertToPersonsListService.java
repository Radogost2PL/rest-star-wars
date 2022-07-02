package dan.was.com.example.rest.star.wars.service;

import dan.was.com.example.rest.star.wars.dto.PersonsList;
import dan.was.com.example.rest.star.wars.exceptions.OutOfBoundException;
import dan.was.com.example.rest.star.wars.responsemodel.PersonResponse;
import dan.was.com.example.rest.star.wars.responsemodel.PersonsListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConvertToPersonsListService {
    @Autowired
    private WebClient.Builder webClientBuilder;
    @Autowired
    ConvertPersonDataService convertPersonDataService;

    private String BASE_URL = "https://swapi.dev/api";
    private String ALL_PEOPLE_BY_PAGE = "/people/";

    private static int idCounter = 0;

    public PersonsListResponse convertToPersonsListResponse(int pageNumber) {
        if (pageNumber < 1 || pageNumber > 9) {
            throw new OutOfBoundException("pages 1-9 only");
        }

        PersonsList personsList = webClientBuilder.build()
                .get()
                .uri(BASE_URL, uriBuilder -> uriBuilder.path(ALL_PEOPLE_BY_PAGE).queryParam("page", pageNumber).build())
                .retrieve()
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
