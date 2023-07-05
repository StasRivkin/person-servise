package telran.java47.personservise.person.personService;

import telran.java47.personservise.person.dto.CityPopulationDto;
import telran.java47.personservise.person.dto.PersonDto;
import telran.java47.personservise.person.model.Address;

import java.util.List;

public interface PersonService {
    Boolean addPerson(PersonDto personDto);
    PersonDto findPersonById(Integer id);
    List<PersonDto> findPersonsByCity(String city);
    List<PersonDto> findPersonsByAge(Integer ageFrom, Integer ageTo);
    PersonDto updatePersonsName(Integer id, String newName);
    List<PersonDto>  findPersonsByName(String name);
    List<CityPopulationDto> findCitiesByPopulation();
    PersonDto updateAddress(Integer id, Address newAddress);
    PersonDto deletePerson(Integer id);
    List<PersonDto> findChildren();
    List<PersonDto> findEmployeeBySalary(int salaryFrom, int salaryTo);
}
