package org.e7.clinica.repository;

import org.e7.clinica.entity.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOdontologoRepository extends JpaRepository<Odontologo, Integer> {

    public List<Odontologo> findByApellidoAndNombre(String apellido, String nombre);
    List<Odontologo> findByNombreOrderByMatricula(String nombre);

    @Query("SELECT COUNT(o) FROM Odontologo o")
    Long contarOdontologos();

}
