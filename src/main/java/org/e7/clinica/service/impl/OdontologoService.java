package org.e7.clinica.service.impl;

import org.e7.clinica.entity.Odontologo;
import org.e7.clinica.service.IOdontologoService;
import org.springframework.stereotype.Service;
import org.e7.clinica.repository.IOdontologoRepository;


import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService implements IOdontologoService {
    private IOdontologoRepository iOdontologoRepository;

    public OdontologoService(IOdontologoRepository iOdontologoRepository) {
        this.iOdontologoRepository = iOdontologoRepository;
    }

    @Override
    public Odontologo guardarOdontologo(Odontologo odontologo) {
        return iOdontologoRepository.save(odontologo);
    }

    @Override
    public Optional<Odontologo> buscarPorId(Integer id) {
        return iOdontologoRepository.findById(id);
    }

    @Override
    public List<Odontologo> buscarTodos() {
        return iOdontologoRepository.findAll();
    }

    @Override
    public void modificarOdontologo(Odontologo odontologo) {
        iOdontologoRepository.save(odontologo);
    }

    @Override
    public void eliminarOdontologo(Integer id) {
        iOdontologoRepository.deleteById(id);
    }

    @Override
    public List<Odontologo> buscarPorApellidoyNombre(String apellido, String nombre) {
        return iOdontologoRepository.findByApellidoAndNombre(apellido, nombre);
    }

    @Override
    public List<Odontologo> buscarPorNombreOderByMatricula(String nombre) {
        return iOdontologoRepository.findByNombreOrderByMatricula(nombre);
    }

    @Override
    public Long contarOdontologos() {
        return iOdontologoRepository.contarOdontologos();
    }


}



