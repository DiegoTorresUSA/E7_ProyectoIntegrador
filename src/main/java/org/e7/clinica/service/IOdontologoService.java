package org.e7.clinica.service;

import org.e7.clinica.entity.Odontologo;
import java.util.List;
import java.util.Optional;

public interface IOdontologoService {
    Odontologo guardarOdontologo(Odontologo odontologo);

    Optional <Odontologo>  buscarPorId(Integer id);

     List<Odontologo> buscarTodos();
    void modificarOdontologo(Odontologo odontologo);

    void eliminarOdontologo(Integer id);

    List<Odontologo> buscarPorApellidoyNombre(String apellido, String nombre);

    List<Odontologo> buscarPorNombreOderByMatricula(String nombre);

    Long contarOdontologos();




}
