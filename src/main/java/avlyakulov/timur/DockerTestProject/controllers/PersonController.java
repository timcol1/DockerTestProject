package avlyakulov.timur.DockerTestProject.controllers;

import avlyakulov.timur.DockerTestProject.dto.PersonDTO;
import avlyakulov.timur.DockerTestProject.models.Person;
import avlyakulov.timur.DockerTestProject.services.PersonService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/people")
public class PersonController {
    private final PersonService personService;
    private final ModelMapper modelMapper;

    public PersonController(PersonService personService, ModelMapper modelMapper) {
        this.personService = personService;
        this.modelMapper = modelMapper;
    }


    @GetMapping
    public String getViewAllPeople(Model model) {
        model.addAttribute("people", personService.findAll().stream().map(this::mapPersonToPersonDTO).toList());
        return "people/people_list";
    }

    public PersonDTO mapPersonToPersonDTO(Person person) {
        return modelMapper.map(person, PersonDTO.class);
    }
}