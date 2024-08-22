package org.e7.clinica.service;

import org.e7.clinica.dao.IDao;
import org.e7.clinica.model.Paciente;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {
    //interfaz data accces
    private IDao<Paciente> pacienteIDao;
    //contructor para funcionar con la implementacion del IDao
    public PacienteService(IDao<Paciente> pacienteIDao) {
        this.pacienteIDao = pacienteIDao;
    }
    //metodos implementados para la clase
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
