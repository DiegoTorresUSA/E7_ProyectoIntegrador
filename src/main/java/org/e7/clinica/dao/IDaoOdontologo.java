package org.e7.clinica.dao;

import java.util.List;

public interface IDaoOdontologo<T>{
    T guardar(T t);

    T buscarPorId(Integer id);

    List<T> listar();

    void modificar(T t);

    void eliminar(Integer id);

}
