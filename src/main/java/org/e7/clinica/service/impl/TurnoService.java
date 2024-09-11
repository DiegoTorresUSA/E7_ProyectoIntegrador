package org.e7.clinica.service.impl;

import org.e7.clinica.entity.Odontologo;
import org.e7.clinica.entity.Paciente;
import org.e7.clinica.entity.Turno;
import org.e7.clinica.exception.ResourceNotFoundException;
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
    public Turno guardarTurno(Turno turno) {
        if (turno.getPaciente() ==  null || turno.getOdontologo() == null){
            throw new IllegalArgumentException("El odontologo o Paciente no puede ser  un valor nulo");
        }
        Optional<Paciente> paciente = pacienteService.buscarPorId(turno.getPaciente().getId());
        Optional<Odontologo> odontologo = odontologoService.buscarPorId(turno.getOdontologo().getId());

        if (paciente.isPresent() && odontologo.isPresent()) {
            turno.setPaciente(paciente.get());
            turno.setOdontologo(odontologo.get());
            return turnoRepository.save(turno);
        } else {
            throw new ResourceNotFoundException("El Paciente o el Odontologo no fueron encontrados");
        }
    }

    @Override
    public Optional<Turno> buscarporId(Integer id) {
        Optional<Turno> turno = turnoRepository.findById(id);
            if(turno.isPresent()){
                return turno;
            } else {
                throw new ResourceNotFoundException("El turno con ID  " + id + "  no fue encontrado");
            }
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
        } else {
            throw new ResourceNotFoundException("El turno  no fue encontrado");
        }
    }

  /*  Optional <Turno>  turnoGenerado= turnoService.buscarporId(turno.getId());
        if(turnoGenerado.isPresent()){
        turnoService.modificarTurno(turno);
        String jsonResponse = "{\"mensaje\": \"El turno fue modificado\"}";
        return ResponseEntity.ok(jsonResponse);
    } else {
        return ResponseEntity.notFound().build();
    }*/
    @Override
    public void eliminarTurno(Integer id) {
        Optional<Turno> turno = turnoRepository.findById(id);
        if (turno.isPresent()) {
            turnoRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("El turno  con ID" + id + " no fue encontrado");
        }
    }
}