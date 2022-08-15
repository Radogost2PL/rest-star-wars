package dan.was.com.example.rest.star.wars.controller;


import dan.was.com.example.rest.star.wars.exceptions.consumer.RemoteServerInternalErrorResponse;
import dan.was.com.example.rest.star.wars.exceptions.consumer.UnknownExceptionResponse;
import dan.was.com.example.rest.star.wars.exceptions.remote.InternalErrorException;
import dan.was.com.example.rest.star.wars.exceptions.remote.ResourceNotFoundExceptions;
import dan.was.com.example.rest.star.wars.exceptions.consumer.NotFoundExceptionResponse;
import dan.was.com.example.rest.star.wars.responsemodel.PersonResponse;
import dan.was.com.example.rest.star.wars.responsemodel.PersonsListResponse;
import dan.was.com.example.rest.star.wars.responsemodel.RestResponse;
import dan.was.com.example.rest.star.wars.service.ConvertPersonDataService;
import dan.was.com.example.rest.star.wars.service.ConvertToPersonsListService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@EnableWebMvc
@RequiredArgsConstructor
@RestController
public class CharacterController {
    private static final Logger LOGGER = LogManager.getLogger(CharacterController.class);

    @Autowired
    private ConvertPersonDataService convertPersonData;
    @Autowired
    private ConvertToPersonsListService convertToPersonsListService;


    @GetMapping("/characters")
    public EntityModel<RestResponse> getAllCharactersFromPage(@RequestParam(value = "page", defaultValue = "1") int page) throws NotFoundExceptionResponse {

        EntityModel<RestResponse> model;
        PersonsListResponse personsListResponse = new PersonsListResponse();
        try {
            personsListResponse = convertToPersonsListService.convertToPersonsListResponse(page);
            model = EntityModel.of(personsListResponse);
        } catch (ResourceNotFoundExceptions e) {
            throw new NotFoundExceptionResponse(e.getMessage());
        } catch (InternalErrorException e) {
            throw new RemoteServerInternalErrorResponse(e.getMessage());
        } catch (Exception e) {
            throw new UnknownExceptionResponse(e.getMessage());
        }
        WebMvcLinkBuilder linkToCharacters
                = linkTo(methodOn(this.getClass()).getAllCharactersFromPage(page));
        model.add(linkToCharacters.withRel("characters-by-page"));
        return model;
    }

    @GetMapping("/characters/{id}")
    public EntityModel<RestResponse> getCharacterById(@PathVariable("id") int id) {

        EntityModel<RestResponse> model;
        try {
            PersonResponse personResponse = convertPersonData.convertPerson(id);
            model = EntityModel.of(personResponse);
        } catch (ResourceNotFoundExceptions e) {
            throw new NotFoundExceptionResponse(e.getMessage());
        } catch (InternalErrorException e) {
            throw new RemoteServerInternalErrorResponse(e.getMessage());
        } catch (Exception e) {
            throw new UnknownExceptionResponse(e.getMessage());
        }
        WebMvcLinkBuilder linkToCharacter =
                linkTo(methodOn(this.getClass()).getCharacterById(id));

        model.add(linkToCharacter.withRel("character-by-id"));
        return model;
    }

}
