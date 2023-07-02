package telran.java47.personservise.person.personService;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import telran.java47.personservise.person.dao.PersonRepository;
import telran.java47.personservise.person.dto.CityPopulationDto;
import telran.java47.personservise.person.model.Address;
import telran.java47.personservise.person.model.Person;
import telran.java47.personservise.person.dto.PersonDto;
import telran.java47.personservise.person.dto.exception.PersonNotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
    final PersonRepository personRepository;
    final ModelMapper modelMapper;

    @Override
    public Boolean addPerson(PersonDto personDto) {
        if (personRepository.existsById(personDto.getId())) {
            return false;
        }
        personRepository.save(modelMapper.map(personDto, Person.class));
        return true;
    }

    @Override
    public PersonDto findPersonById(Integer id) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
        return modelMapper.map(person, PersonDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonDto> findPersonsByCity(String city) {
        return personRepository.findPersonByAddress_CityIgnoreCase(city)
                .map(p -> modelMapper.map(p, PersonDto.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonDto> findPersonsByAge(Integer ageFrom, Integer ageTo) {
        LocalDate dateOfAgeFrom = ageTo >= 0 ? LocalDate.now().minusYears(ageTo) : LocalDate.now().minusYears(-ageTo); //from the oldest date, preventing negative value
        LocalDate dateOfAgeTo = ageFrom >= 0 ? LocalDate.now().minusYears(ageFrom) : LocalDate.now().minusYears(-ageFrom); //til the newest date, preventing negative value
        return personRepository.findPersonByBirthDateBetween(dateOfAgeFrom, dateOfAgeTo)
                .map(p -> modelMapper.map(p, PersonDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PersonDto updatePersonsName(Integer id, String newName) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
        person.setName(newName.substring(0,1).toUpperCase().concat(newName.substring(1).toLowerCase()));
        personRepository.save(person);
        return modelMapper.map(person, PersonDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonDto> findPersonsByName(String name) {
        return personRepository.findPersonByNameIgnoreCase(name)
                .map(p -> modelMapper.map(p, PersonDto.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CityPopulationDto> findCitiesByPopulation() {
        return personRepository.countPersonsByCity()
                .map(p -> modelMapper.map(p, CityPopulationDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PersonDto updateAddress(Integer id, Address newAddress) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
        person.setAddress(newAddress);
        personRepository.save(person);
        return modelMapper.map(person, PersonDto.class);
    }

    @Override
    public PersonDto deletePerson(Integer id) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
        personRepository.delete(person);
        return modelMapper.map(person, PersonDto.class);
    }

}
