package telran.java47.personservise.person.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import telran.java47.personservise.person.dto.CityPopulationDto;
import telran.java47.personservise.person.dto.PersonDto;
import telran.java47.personservise.person.model.Address;
import telran.java47.personservise.person.personService.PersonService;

import java.util.List;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {
    final PersonService personService;

    @PostMapping
    public Boolean addPerson(@RequestBody PersonDto personDto) {
        return personService.addPerson(personDto);
    }

    @GetMapping("/{id}")
    public PersonDto findPersonById(@PathVariable Integer id) {
        return personService.findPersonById(id);
    }

    @GetMapping("/city/{city}")
    public List<PersonDto> findPersonsByCity(@PathVariable String city) {
        return personService.findPersonsByCity(city);
    }


    @PutMapping("/{id}/name/{newName}")
    public PersonDto updatePersonsName(@PathVariable Integer id, @PathVariable String newName) {
        return personService.updatePersonsName(id, newName);
    }

    @GetMapping("/ages/{ageFrom}/{ageTo}")
    public List<PersonDto> findPersonsByAge(@PathVariable Integer ageFrom, @PathVariable Integer ageTo) {
        return personService.findPersonsByAge(ageFrom, ageTo);
    }

    @GetMapping("/name/{name}")
    public List<PersonDto> findPersonsByName(@PathVariable String name) {
        return personService.findPersonsByName(name);
    }

    @GetMapping("/population/city")
    public List<CityPopulationDto> findCitiesByPopulation() {
        return personService.findCitiesByPopulation();
    }

    @PutMapping("/{id}/address")
    public PersonDto updateAddress(@PathVariable Integer id, @RequestBody Address newAddress) {
        return personService.updateAddress(id, newAddress);
    }

    @DeleteMapping("/{id}")
    public PersonDto deletePerson(@PathVariable Integer id) {
        return personService.deletePerson(id);
    }

}
