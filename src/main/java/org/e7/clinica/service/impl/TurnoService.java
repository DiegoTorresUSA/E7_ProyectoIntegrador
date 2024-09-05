package org.e7.clinica.service.impl;

import org.e7.clinica.entity.Odontologo;
import org.e7.clinica.entity.Paciente;
import org.e7.clinica.entity.Turno;
import org.e7.clinica.service.ITurnoService;
import org.springframework.stereotype.Service;
import org.e7.clinica.repository.ITurnoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoService implements ITurnoService {
    private ITurnoRepository turnoRepository;
    private PacienteService pacienteService;
    private OdontologoService odontologoService;

    public TurnoService(ITurnoRepository turnoRepository, PacienteService pacienteService, OdontologoService odontologoService) {
        this.turnoRepository = turnoRepository;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }

    @Override
    public Turno guardarTurno(Turno turno){
        Optional<Paciente> paciente = pacienteService.buscarPorId(turno.getPaciente().getId());
        Optional<Odontologo>  odontologo = odontologoService.buscarPorId(turno.getOdontologo().getId());
        Turno turnoARetornar = null;
        if (paciente.isPresent() && odontologo.isPresent()) {
            turno.setPaciente(paciente.get());
            turno.setOdontologo(odontologo.get());
            // voy a persistir el turno
            turnoARetornar = turnoRepository.save(turno);
        }
        return turnoARetornar;
    }
    @Override
    public Optional<Turno> buscarporId(Integer id) {
        return turnoRepository.findById(id);
    }

    @Override
    public List<Turno> buscarTodos() {
        return turnoRepository.findAll();
    }

    @Override
    public void modificarTurno(Turno turno) {
        //Armo el turno
        Optional<Paciente> paciente = pacienteService.buscarPorId(turno.getPaciente().getId());
        Optional<Odontologo> odontologo = odontologoService.buscarPorId(turno.getOdontologo().getId());
        if (paciente.isPresent() && odontologo.isPresent()) {
            turno.setPaciente(paciente.get());
            turno.setOdontologo(odontologo.get());
            // Persistir el turno, el setteo de la fecha viene en turno
            turnoRepository.save(turno);
        }
    }
    @Override
    public void eliminarTurno(Integer id) {
        turnoRepository.deleteById(id);
    }
}
