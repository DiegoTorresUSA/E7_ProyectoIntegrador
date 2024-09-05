package org.e7.clinica.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter//Lombook
@Setter//Lombook
@AllArgsConstructor//Lombook
@NoArgsConstructor//Lombook
@Entity
@Table(name="odontologos")

public class Odontologo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private String matricula;
    private String nombre;
    private String apellido;

    @OneToMany(mappedBy = "odontologo")
    @JsonIgnore
    //@JsonManagedReference(value = "odontologo-turno")
    private Set<Turno> turnoSet;
    @Override
    public String toString() {
        return "Odontologo{" +
                "Id=" + Id +
                ", matricula=" + matricula +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                '}';
    }
}
