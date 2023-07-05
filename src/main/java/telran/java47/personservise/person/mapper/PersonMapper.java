package telran.java47.personservise.person.mapper;

import telran.java47.personservise.person.dto.PersonDto;
import telran.java47.personservise.person.model.Person;

public interface PersonMapper {
    Person mapFromDto(PersonDto person);
    PersonDto mapToDto(Person person);
}
