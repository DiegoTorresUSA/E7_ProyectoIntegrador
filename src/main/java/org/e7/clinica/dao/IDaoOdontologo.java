package org.e7.clinica.dao;

import java.util.List;

public interface IDaoOdontologo<T>{
    T guardar(T t);

    List<T> listarOdontologos();

    void modificar(T t);

    void eliminar(T t);

}
