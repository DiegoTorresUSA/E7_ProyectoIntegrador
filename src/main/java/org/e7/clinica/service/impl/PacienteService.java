package org.e7.clinica.service.impl;

import org.e7.clinica.entity.Paciente;
import org.e7.clinica.exception.ResourceNotFoundException;
import org.e7.clinica.service.IPacienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.e7.clinica.repository.IPacienteRepository;

import java.util.List;
import java.util.Optional;


@Service
public class PacienteService implements IPacienteService {
    private IPacienteRepository pacienteRepository;
    public PacienteService(IPacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }
    @Override
    public Paciente guardarPaciente(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    @Override
    public Optional<Paciente> buscarPorId(Integer id) {
        Optional<Paciente> paciente = pacienteRepository.findById(id);
        if (paciente.isPresent()) {
            return paciente;//Repository.findById(id);
        } else {
            throw new ResourceNotFoundException("El paciente con ID  " + id + "  no fue encontrado");
        }
    }

    @Override
    public List<Paciente> buscarTodos() {
        List<Paciente> pacientes=pacienteRepository.findAll();
        if(pacientes.isEmpty()){
            throw new ResourceNotFoundException("No se encontraron pacientes");
        }
        return pacienteRepository.findAll();
    }

    @Override
    public void modificarPaciente(Paciente paciente) {
        Optional<Paciente> pacienteEncontrado = pacienteRepository.findById(paciente.getId());
        if(pacienteEncontrado.isPresent()){
            pacienteRepository.save(paciente);
        } else {
            throw new ResourceNotFoundException("El paciente  no fue encontrado");
        }
    }

    @Override
    public void eliminarPaciente(Integer id) {
        Optional<Paciente> pacienteEncontrado = pacienteRepository.findById(id);
        if(pacienteEncontrado.isPresent()){
            pacienteRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("El paciente  con ID "+ id +" no fue encontrado");
        }
    }

    /*@Override
    public List<Paciente> buscarPorApellidoyNombre(String apellido, String nombre) {
        return pacienteRepository.findAllById(apellido, nombre);
    }

    @Override
    public List<Paciente> buscarLikeNombre(String nombre) {
        return pacienteRepository.findByNombreLike(nombre);
    }*/
}
