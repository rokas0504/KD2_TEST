package org.example;

import jakarta.persistence.*;
import lombok.*;
import org.example.enums.WeekDays;
import org.example.utils.WeekDayConverter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity


public class Darbuotojas implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    @Column(nullable = false)
    protected String name;
    @Column(nullable = false)
    protected String surname;
    @Convert(converter = WeekDayConverter.class)
    @Column(name = "weekDays")
    private List<WeekDays> weekDays;

    public Darbuotojas(String name, String surname, List<WeekDays> weekDays) {
        this.name = name;
        this.surname = surname;
        this.weekDays = weekDays;
    }

    @Override
    public String toString(){
        return name + " " + surname;
    }
}
