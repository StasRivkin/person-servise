package telran.java47.personservise.person.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.time.LocalDate;
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "employee")
public class Employee extends Person{
    private static final long serialVersionUID = 2406608053472324936L;
    String company;
    int salary;

    public Employee(Integer id, String name, LocalDate birthDate, Address address, String company, int salary) {
        super(id, name, birthDate, address);
        this.company = company;
        this.salary = salary;
    }

    public Employee(String company, int salary) {
        this.company = company;
        this.salary = salary;
    }

    @JsonProperty("type")
    public String getType() {
        return this.getClass().getSimpleName().toLowerCase();
    }
}
