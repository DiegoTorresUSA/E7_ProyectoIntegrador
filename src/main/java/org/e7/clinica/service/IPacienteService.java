package org.e7.clinica.service;

import org.e7.clinica.entity.Paciente;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IPacienteService {
    Paciente guardarPaciente(Paciente paciente);

     Optional<Paciente> buscarPorId(Integer id);
    List<Paciente> buscarTodos();
    void modificarPaciente(Paciente paciente);
    void eliminarPaciente(Integer id);
    /*List<Paciente> buscarPorApellidoyNombre(String apellido, String nombre);
    @Query("Select p from Paciente p where p.nombre LIKE %:nombre%")
    List<Paciente> buscarLikeNombre(String nombre);*/
}
