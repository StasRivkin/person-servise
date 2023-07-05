package telran.java47.personservise.person.personService;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import telran.java47.personservise.person.dao.PersonRepository;
import telran.java47.personservise.person.dto.CityPopulationDto;
import telran.java47.personservise.person.mapper.PersonMapper;
import telran.java47.personservise.person.model.Address;
import telran.java47.personservise.person.model.Child;
import telran.java47.personservise.person.model.Person;
import telran.java47.personservise.person.model.Employee;
import telran.java47.personservise.person.dto.PersonDto;
import telran.java47.personservise.person.dto.exception.PersonNotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService, CommandLineRunner {
    final PersonRepository personRepository;
    final ModelMapper modelMapper;
    final PersonMapper personMapper;

    @Override
    @Transactional
    public Boolean addPerson(PersonDto personDto) {
        if (personRepository.existsById(personDto.getId())) {
            return false;
        }
         personRepository.save(personMapper.mapFromDto(personDto));
        return true;
    }

    @Override
    public PersonDto findPersonById(Integer id) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
        return personMapper.mapToDto(person);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonDto> findPersonsByCity(String city) {
        return personRepository.findPersonByAddress_CityIgnoreCase(city)
                .map(personMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonDto> findPersonsByAge(Integer ageFrom, Integer ageTo) {
        LocalDate dateOfAgeFrom = ageTo >= 0 ? LocalDate.now().minusYears(ageTo) : LocalDate.now().minusYears(-ageTo); //from the oldest date, preventing negative value
        LocalDate dateOfAgeTo = ageFrom >= 0 ? LocalDate.now().minusYears(ageFrom) : LocalDate.now().minusYears(-ageFrom); //til the newest date, preventing negative value
        return personRepository.findPersonByBirthDateBetween(dateOfAgeFrom, dateOfAgeTo)
                .map(personMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PersonDto updatePersonsName(Integer id, String newName) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
        person.setName(newName.substring(0, 1).toUpperCase().concat(newName.substring(1).toLowerCase()));
        return personMapper.mapToDto(person);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonDto> findPersonsByName(String name) {
        return personRepository.findPersonByNameIgnoreCase(name)
                .map(personMapper::mapToDto)
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
    @Transactional
    public PersonDto updateAddress(Integer id, Address newAddress) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
        person.setAddress(newAddress);
        return personMapper.mapToDto(person);
    }

    @Override
    @Transactional
    public PersonDto deletePerson(Integer id) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
        personRepository.delete(person);
        return personMapper.mapToDto(person);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonDto> findChildren() {
        return personRepository.findChildren()
                .map(personMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonDto> findEmployeeBySalary(int salaryFrom, int salaryTo) {
        return personRepository.findPersonBySalary(salaryFrom, salaryTo)
                .map(personMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void run(String... args) throws Exception {
        if (personRepository.count() == 0) {
            Person p = new Person(1000, "John", LocalDate.of(1991, 5, 10), new Address("Tel-Aviv", "Bla-bla", 10));
            Child c = new Child(2000, "Moshe", LocalDate.of(2020, 9, 15), new Address("Ashdod", "Bla-bla", 10), "Shalom");
            Employee e = new Employee(3000, "Sahra", LocalDate.of(1996, 12, 21), new Address("Ashkelon", "Bla-bla", 10), "motorolla", 5000);
            personRepository.save(p);
            personRepository.save(c);
            personRepository.save(e);
        }
    }
}
