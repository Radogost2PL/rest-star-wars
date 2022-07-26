package dan.was.com.example.rest.star.wars.controller;


import dan.was.com.example.rest.star.wars.exceptions.OutOfBoundException;
import dan.was.com.example.rest.star.wars.responsemodel.PersonResponse;
import dan.was.com.example.rest.star.wars.responsemodel.PersonsListResponse;
import dan.was.com.example.rest.star.wars.responsemodel.RestResponse;
import dan.was.com.example.rest.star.wars.service.ConvertPersonDataService;
import dan.was.com.example.rest.star.wars.service.ConvertToPersonsListService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@RequiredArgsConstructor
@RestController
public class CharacterController {
    private static Logger LOGGER = LogManager.getLogger(CharacterController.class);

    @Autowired
    private ConvertPersonDataService convertPersonData;
    @Autowired
    private ConvertToPersonsListService convertToPersonsListService;


    @GetMapping("/characters")
    public RestResponse getAllCharactersFromPage(@RequestParam(value = "page", defaultValue = "1") int page) throws OutOfBoundException {

        PersonsListResponse personsListResponse = new PersonsListResponse();
        try {
             personsListResponse = convertToPersonsListService.convertToPersonsListResponse(page);
        }catch (Exception e){
            RestResponse restResponse = new RestResponse();
            restResponse.setMessage(e.getMessage());
            return restResponse;
        }

        return personsListResponse;
    }

    @GetMapping("/characters/{id}")
    public RestResponse getCharacterById(@PathVariable("id") int id) {

        PersonResponse personResponse = new PersonResponse();
        try {
            personResponse = convertPersonData.convertPerson(id);
        } catch (Exception e) {
            RestResponse restResponse = new RestResponse();
            restResponse.setMessage(e.getMessage());
            return restResponse;
        }
        return personResponse;
    }

}
