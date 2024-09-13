package org.e7.clinica.service.impl;

import org.e7.clinica.exception.BadRequestException;
import org.e7.clinica.entity.Paciente;
import org.e7.clinica.exception.ResourceNotFoundException;
import org.e7.clinica.service.IPacienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.e7.clinica.repository.IPacienteRepository;

import java.util.List;
import java.util.Optional;


@Service
public class PacienteService implements IPacienteService {
    private final Logger logger = LoggerFactory.getLogger(PacienteService.class);
    private IPacienteRepository pacienteRepository;
    public PacienteService(IPacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }
    @Override
    public Paciente guardarPaciente(Paciente paciente) {
        if (paciente.getNombre()== null || paciente.getApellido()== null  || paciente.getDni()== null || paciente.getFechaIngreso()== null ||paciente.getDomicilio()== null ){
            logger.error("Los datos nombre, apellido, DNI, domicilio y fecha de ingreso son obligatorios");
            throw new BadRequestException("Los datos nombre, apellido,DNI, domicilio y fecha de ingreso son obligatorios");
        }
        return pacienteRepository.save(paciente);
    }

    @Override
    public Optional<Paciente> buscarPorId(Integer id) {
        Optional<Paciente> paciente = pacienteRepository.findById(id);
        if (paciente.isPresent()) {
            logger.info("paciente encontrado: "+ paciente.get());
            return paciente;//Repository.findById(id);
        } else {
            logger.error("El paciente con ID  " + id + "  no fue encontrado");
            throw new ResourceNotFoundException("El paciente con ID  " + id + "  no fue encontrado");
        }
    }

    @Override
    public List<Paciente> buscarTodos() {
        List<Paciente> pacientes=pacienteRepository.findAll();
        if(pacientes.isEmpty()){
            logger.error("No se encontraron pacientes");
            throw new ResourceNotFoundException("No se encontraron pacientes");
        }
        List<Paciente> pacienteDesdeDb = pacienteRepository.findAll();
        logger.info("Pacientes encontrados: "+ pacienteDesdeDb.size());
        return pacienteDesdeDb;
    }

    @Override
    public void modificarPaciente(Paciente paciente) {
        Optional<Paciente> pacienteEncontrado = pacienteRepository.findById(paciente.getId());
        if(pacienteEncontrado.isPresent()){
            logger.info("Paciente encontrado, actualizando: " + paciente.getId());
            pacienteRepository.save(paciente);
        } else {
            logger.info("El paciente  no fue encontrado");
            throw new ResourceNotFoundException("El paciente  no fue encontrado");
        }
    }

    @Override
    public void eliminarPaciente(Integer id) {
        Optional<Paciente> pacienteEncontrado = pacienteRepository.findById(id);
        if(pacienteEncontrado.isPresent()){
            pacienteRepository.deleteById(id);
            logger.info("Paciente "+pacienteEncontrado.get() + " eliminado");
        } else {
            throw new ResourceNotFoundException("El paciente  con ID "+ id +" no fue encontrado");
        }
    }
}
