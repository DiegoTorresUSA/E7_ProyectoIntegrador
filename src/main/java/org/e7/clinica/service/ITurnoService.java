package org.e7.clinica.service;

import org.e7.clinica.entity.Turno;

import java.util.List;
import java.util.Optional;

public interface ITurnoService {
    Turno guardarTurno(Turno turno);

    Optional<Turno> buscarporId(Integer id);

    List<Turno> buscarTodos();


    void modificarTurno (Turno turno);


    void eliminarTurno(Integer id);
}
