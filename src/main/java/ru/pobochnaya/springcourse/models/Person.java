package ru.pobochnaya.springcourse.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    //@Min()
    private String name;
    @Column(name = "age")
    private Integer age;

    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Past(message = "The date must be in the past.")
    private Date dateOfBirth;

    @Column(name = "create_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @Email
    private String email;

    @Enumerated(EnumType.STRING)
    private Mood mood;

    @OneToMany(mappedBy = "person")
    private List<Item> items;

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", dateOfBirth=" + dateOfBirth +
                ", createAt=" + createAt +
                ", email='" + email + '\'' +
                ", mood=" + mood +
                ", items=" + itemName(items) +
                '}';
    }

    private String itemName(List<Item> items) {
        String itemsName = "";
        for(Item item : items) {
            itemsName += item.getName() + " ";
        }

        return itemsName;
    }
}
