package jankowiak.kamil.persistence.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Client {

    private String name;
    private String surname;
    private int age;
    private BigDecimal cash;

    public Client() {
    }

    public Client(String name, String surname, int age, BigDecimal cash) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.cash = cash;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public BigDecimal getCash() {
        return cash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return age == client.age &&
                Objects.equals(name, client.name) &&
                Objects.equals(surname, client.surname) &&
                Objects.equals(cash, client.cash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, age, cash);
    }

    @Override
    public String toString() {
        return  name +
                ", " + surname +
                ", age: " + age +
                ", cash: " + cash;
    }
}
