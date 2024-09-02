package repository;

import org.e7.clinica.entity.Domicilio;
import org.e7.clinica.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPacienteRepository extends JpaRepository<Paciente, Integer> {
}
