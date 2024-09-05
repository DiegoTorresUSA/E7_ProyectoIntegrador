package org.e7.clinica.service.impl;

import org.e7.clinica.entity.Paciente;
import org.e7.clinica.service.IPacienteService;
import org.springframework.stereotype.Service;
import repository.IPacienteRepository;

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
    public Optional<Paciente>  buscarPorId(Integer id) {
        return pacienteRepository.findById(id);
    }

    @Override
    public List<Paciente> buscarTodos() {
        return pacienteRepository.findAll();
    }

    @Override
    public void modificarPaciente(Paciente paciente) {pacienteRepository.save(paciente);
    }

    @Override
    public void eliminarPaciente(Integer id) {
        pacienteRepository.deleteById(id);
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
