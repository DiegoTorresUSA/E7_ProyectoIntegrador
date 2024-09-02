package org.e7.clinica.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
@Getter//Lombook
@Setter//Lombook
@AllArgsConstructor//Lombook
@NoArgsConstructor//Lombook
@Entity
@Table(name="turnos")

public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)// id autoincremental si la base de datos lo genera
    private Integer id;
    private Paciente paciente;
    private Odontologo odontologo;
    private LocalDate fecha;
    @Override
    public String toString() {
        return "Turno{" +
                "id=" + id +
                ", paciente=" + paciente +
                ", odontologo=" + odontologo +
                ", fecha=" + fecha +
                '}';
    }
}
