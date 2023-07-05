package telran.java47.personservise.person.mapper;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import telran.java47.personservise.person.dto.ChildDto;
import telran.java47.personservise.person.dto.EmployeeDto;
import telran.java47.personservise.person.dto.PersonDto;
import telran.java47.personservise.person.model.Child;
import telran.java47.personservise.person.model.Employee;
import telran.java47.personservise.person.model.Person;

@RequiredArgsConstructor
@Component
@Getter
public class PersonMapperImpl implements PersonMapper {
    final ModelMapper modelMapper;

    @Override
    public Person mapFromDto(PersonDto person) {
        if (person instanceof ChildDto) {
            return modelMapper.map(person, Child.class);
        } else if (person instanceof EmployeeDto) {
            return modelMapper.map(person, Employee.class);
        } else {
            return modelMapper.map(person, Person.class);
        }
    }

    @Override
    public PersonDto mapToDto(Person person) {
        if (person instanceof Child) {
            return modelMapper.map(person, ChildDto.class);
        } else if (person instanceof Employee) {
            return modelMapper.map(person, EmployeeDto.class);
        } else {
            return modelMapper.map(person, PersonDto.class);
        }
    }
}
