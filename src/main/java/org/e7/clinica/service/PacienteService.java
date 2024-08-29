package org.e7.clinica.service;

import org.e7.clinica.dao.IDao;
import org.e7.clinica.model.Paciente;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {
    private IDao<Paciente> pacienteIDao;

    public PacienteService(IDao<Paciente> pacienteIDao) {
        this.pacienteIDao = pacienteIDao;
    }

    public Paciente guardarPaciente(Paciente paciente){
        return pacienteIDao.guardar(paciente);
    }

    public Paciente buscarPorId(Integer id){
        return pacienteIDao.buscarPorId(id);
    }
    public List<Paciente> buscarTodos(){
        return pacienteIDao.buscarTodos();
    }
    public void modificarPaciente(Paciente paciente){
        pacienteIDao.modificar(paciente);
    }
    public void eliminarPaciente(Integer id){
        pacienteIDao.eliminar(id);
    }

}
