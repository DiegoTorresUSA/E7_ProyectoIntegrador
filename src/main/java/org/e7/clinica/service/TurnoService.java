package org.e7.clinica.service;

import org.e7.clinica.dao.IDao;
import org.e7.clinica.dao.IDaoOdontologo;
import org.e7.clinica.model.Odontologo;
import org.e7.clinica.model.Paciente;
import org.e7.clinica.model.Turno;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoService {
    private IDaoOdontologo<Turno> turnoIDao;
    private PacienteService pacienteService;
    private OdontologoService odontologoService;

    public TurnoService(IDaoOdontologo<Turno> turnoIDao, PacienteService pacienteService, OdontologoService odontologoService) {
        this.turnoIDao = turnoIDao;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }

    public Turno guardarTurno(Turno turno){
        Paciente paciente = pacienteService.buscarPorId(turno.getPaciente().getId());
        Odontologo odontologo = odontologoService.buscarporId(turno.getOdontologo().getId());
        Turno turnoARetornar = null;

        if (paciente != null && odontologo != null){
            turno.setPaciente(paciente);
            turno.setOdontologo(odontologo);
            turnoARetornar = turnoIDao.guardar(turno);
        }
        return turnoARetornar;
    }

    public Turno buscarporId(Integer id){
        return turnoIDao.buscarPorId(id);
    }

    public List<Turno> buscarTodos(){
        return turnoIDao.listar();
    }

    public void modificarTurno (Turno turno){
        turnoIDao.modificar(turno);
    }

    public void eliminarTurno(Integer id){
        turnoIDao.eliminar(id);
    }

}
