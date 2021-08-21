package dan.was.com.example.rest.star.wars.service;


import dan.was.com.example.rest.star.wars.dto.PersonsList;
import dan.was.com.example.rest.star.wars.dto.Planet;
import dan.was.com.example.rest.star.wars.dto.Starship;
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

    private static int idCounter = 0;

    public PersonsListResponse convertToPersonsListResponse(int pageNumber) {

            PersonsList personsList = WebClient.create("https://swapi.dev")
                    .get()
                    .uri(uriBuilder -> uriBuilder.path("/api/people").queryParam("page", pageNumber).build())
                    .retrieve()
                    .bodyToMono(PersonsList.class)
                    .block();

            List<PersonResponse> personResponsesList = new ArrayList<>();


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

        PersonsListResponse personsListResponse = new PersonsListResponse(9, 86, personResponsesList);

        return personsListResponse;
    }
}
