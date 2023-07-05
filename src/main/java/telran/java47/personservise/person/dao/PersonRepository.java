package telran.java47.personservise.person.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import telran.java47.personservise.person.model.CityPopulation;
import telran.java47.personservise.person.model.Person;

import java.time.LocalDate;
import java.util.stream.Stream;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    Stream<Person> findPersonByAddress_CityIgnoreCase(String city);

    Stream<Person> findPersonByBirthDateBetween(LocalDate dateFrom, LocalDate dateTo);

    Stream<Person> findPersonByNameIgnoreCase(String name);

    @Query(value = "SELECT NEW telran.java47.personservise.person.model.CityPopulation(person.address.city, COUNT(*)) FROM Person person GROUP BY person.address.city")
    Stream<CityPopulation> countPersonsByCity();

    @Query(value = "SELECT NEW telran.java47.personservise.person.model.Child(person.id, person.name, person.birthDate, person.address, person.kindergarten) " +
            "FROM Person person WHERE person.birthDate > TIMESTAMPADD(YEAR, -18, CURRENT_DATE())")
    Stream<Person> findChildren();

    @Query(value = "SELECT NEW telran.java47.personservise.person.model.Employee(person.id, person.name, person.birthDate, person.address, person.company, person.salary)" +
            "FROM Person person WHERE person.salary > :salaryFrom AND person.salary < :salaryTo")
    Stream<Person> findPersonBySalary(int salaryFrom, int salaryTo);

}
