package avlyakulov.timur.DockerTestProject.controllers;

import avlyakulov.timur.DockerTestProject.dto.PersonDTO;
import avlyakulov.timur.DockerTestProject.models.Person;
import avlyakulov.timur.DockerTestProject.services.PersonService;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
        model.addAttribute("people", personService.findAll().stream().map(this::toPersonDTO).toList());
        return "people/people_list";
    }

    @GetMapping("/test")
    @ResponseBody
    public ResponseEntity<?> testAPI() {

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(Map.of("status", "OK"));
    }


    @GetMapping("/{id}")
    public String getViewPerson(@PathVariable int id, Model model) {
        model.addAttribute("person", toPersonDTO(personService.getPersonById(id)));
        return "people/person";
    }

    @GetMapping("/new")
    public String getViewCreatePerson(Model model) {
        model.addAttribute("person", new PersonDTO());
        return "people/add_person";
    }

    @PostMapping
    public String createPerson(@ModelAttribute("person") PersonDTO personDTO) {
        Person person = toPerson(personDTO);
        personService.createPerson(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String getViewEditPerson(@PathVariable int id, Model model) {
        model.addAttribute("person", toPersonDTO(personService.getPersonById(id)));
        return "people/edit_person";
    }

    @PatchMapping("/{id}")
    public String updatePerson(@ModelAttribute("person") PersonDTO personDTO, @PathVariable int id) {
        personService.updatePerson(toPerson(personDTO), id);
        return "redirect:/people/{id}";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable int id) {
        personService.deletePerson(id);
        return "redirect:/people";
    }


    public PersonDTO toPersonDTO(Person person) {
        return modelMapper.map(person, PersonDTO.class);
    }

    public Person toPerson(PersonDTO personDTO) {
        return modelMapper.map(personDTO, Person.class);
    }
}
