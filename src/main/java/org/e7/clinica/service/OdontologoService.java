package org.e7.clinica.service;

import org.e7.clinica.dao.IDaoOdontologo;
import org.e7.clinica.dao.impl.OdontologoDaoH2;
import org.e7.clinica.model.Odontologo;
import org.e7.clinica.model.Paciente;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoService {
    private IDaoOdontologo<Odontologo> odontologoIDaoOdontologo;

    public OdontologoService(IDaoOdontologo<Odontologo> odontologoIDaoOdontologo) {
        this.odontologoIDaoOdontologo = odontologoIDaoOdontologo;
    }
    public Odontologo guardarOdontologo(Odontologo odontologo) {
        return odontologoIDaoOdontologo.guardar(odontologo);
    }

    public  Odontologo  buscarPorId(Integer id){
        return odontologoIDaoOdontologo.buscarPorId(id);
    }

    public List<Odontologo> listarOdontologo() {
        return odontologoIDaoOdontologo.listarOdontologos();
    }
    public void modificarOdontologo(Odontologo odontologo){
        odontologoIDaoOdontologo.modificar(odontologo);
    }
    public void eliminarOdontologoe(Integer id){
        odontologoIDaoOdontologo.eliminar(id);
    }
}



