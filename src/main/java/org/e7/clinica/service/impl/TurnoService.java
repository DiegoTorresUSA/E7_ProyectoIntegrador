package org.e7.clinica.service.impl;

import org.e7.clinica.entity.Odontologo;
import org.e7.clinica.entity.Paciente;
import org.e7.clinica.entity.Turno;
import org.e7.clinica.exception.ResourceNotFoundException;
import org.e7.clinica.service.ITurnoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.e7.clinica.repository.ITurnoRepository;


import java.util.List;
import java.util.Optional;

@Service
public class TurnoService implements ITurnoService {
    private final Logger logger = LoggerFactory.getLogger(TurnoService.class);

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
        Turno turnoDesdeDb = null;
        if (turno.getPaciente() ==  null || turno.getOdontologo() == null){
            throw new IllegalArgumentException("El odontologo o Paciente no puede ser  un valor nulo");
        }
        Optional<Paciente> paciente = pacienteService.buscarPorId(turno.getPaciente().getId());
        Optional<Odontologo> odontologo = odontologoService.buscarPorId(turno.getOdontologo().getId());

        if (paciente.isPresent() && odontologo.isPresent()) {
            turno.setPaciente(paciente.get());
            turno.setOdontologo(odontologo.get());
            turnoDesdeDb = turnoRepository.save(turno);
            logger.info("turno "+turnoDesdeDb.getId() +  " guardado");
            return turnoDesdeDb;
        } else {
            throw new ResourceNotFoundException("El Paciente o el Odontologo no fueron encontrados");
        }
    }

    @Override
    public Optional<Turno> buscarporId(Integer id) {
        Optional<Turno> turno = turnoRepository.findById(id);
            if(turno.isPresent()){
                logger.info("turno encontrado: "+ turno.get());
                return turno;
            } else {
                logger.error("El turno con ID  " + id + "  no fue encontrado");
                throw new ResourceNotFoundException("El turno con ID  " + id + "  no fue encontrado");
            }
    }

    @Override
    public List<Turno> buscarTodos() {
        List<Turno> turnos = turnoRepository.findAll();
        logger.info("turnos encontrados: "+ turnos);
        return turnos;
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
            logger.info("turno "+turno.getId() +  " modificado");
        } else {
            logger.error("El turno con ID  " + turno.getId() + "  no fue encontrado");
            throw new ResourceNotFoundException("El turno  no fue encontrado");
        }
    }

    @Override
    public void eliminarTurno(Integer id) {
        Optional<Turno> turno = turnoRepository.findById(id);
        if (turno.isPresent()) {
            turnoRepository.deleteById(id);
            logger.info("turno "+turno.get() + " eliminado");
        } else {
            logger.error("El turno  con ID" + id + " no fue encontrado");
            throw new ResourceNotFoundException("El turno  con ID " + id + " no fue encontrado");
        }
    }
}