package org.e7.clinica.dao.impl;

import org.e7.clinica.dao.IDaoOdontologo;
import org.e7.clinica.model.Turno;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TurnoMemoria implements IDaoOdontologo<Turno> {

    private static final Logger logger = LoggerFactory.getLogger(OdontologoDaoH2.class);
    private List<Turno> turnos = new ArrayList<>();

    @Override
    public Turno guardar(Turno turno) {
        turno.setId(turnos.size()+1);
        turnos.add(turno);
        logger.info("Truno agregado" + turno);
        return turno;
    }

    @Override
    public Turno buscarPorId(Integer id) {
        Turno turnoARetornar = null;
        for (Turno t: turnos){
            if (t.getId().equals(id)){
                turnoARetornar = t;
                logger.info("el turno es: " + turnoARetornar);
            }else {
                return turnoARetornar;
            }
        }
        return null;
    }

    @Override
    public List<Turno> listar() {
        logger.info("Turnos:" + turnos);
        return turnos;
    }

    @Override
    public void modificar(Turno turno) {
        for (Turno t: turnos){
            if (t.getId().equals(turno.getId())){
                t.setOdontologo(turno.getOdontologo());
                t.setPaciente(turno.getPaciente());
                t.setFecha(turno.getFecha());
                logger.info("Turno Modificado:" + t);
            }
        }
    }

    @Override
    public void eliminar(Integer id) {
        for (Turno t: turnos){
            if (t.getId().equals(id)){
                logger.info("Turno Eliminado:" + t);
                turnos.remove(t);
                break;
            }
        }
    }
}
