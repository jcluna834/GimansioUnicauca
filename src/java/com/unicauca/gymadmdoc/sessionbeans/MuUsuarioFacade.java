/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.gymadmdoc.sessionbeans;

import com.unicauca.gymadmdoc.entities.MuUsuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ROED26
 */
@Stateless
public class MuUsuarioFacade extends AbstractFacade<MuUsuario> {

    @PersistenceContext(unitName = "Gym_Adm_DocPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MuUsuarioFacade() {
        super(MuUsuario.class);
    }
    
    public List<MuUsuario> buscarPorIdUsuario(Long usuid) {
        Query query = getEntityManager().createNamedQuery("MuUsuario.findByUsuIdentificacion");
        query.setParameter("usuIdentificacion", usuid);
        List<MuUsuario> resultList = query.getResultList();
        return resultList;
    }
    public List<MuUsuario> busacarPorNombreEstudiante(String nombre) {
        Query query = getEntityManager().createNamedQuery("MuUsuario.findByUsuNombres");
        query.setParameter("usuNombres", "%" + nombre + "%");
        List<MuUsuario> resultList = query.getResultList();
        return resultList;
    }
    public List<MuUsuario> buscarPorFuncionario() {
        Query query = getEntityManager().createNamedQuery("MuUsuario.findByFuncionarios");
        query.setParameter("ocupacionD", "Docente");
        query.setParameter("ocupacionA", "Administrativo");
        List<MuUsuario> resultList = query.getResultList();

        return resultList;

    }
    public List<MuUsuario> buscarPorEstudiantes() {
        Query query = getEntityManager().createNamedQuery("MuUsuario.findByEstudiantes");
        query.setParameter("ocupacionE", "Estudiante");
        List<MuUsuario> resultList = query.getResultList();
        return resultList;

    }
    public List<MuUsuario> busacarPorNombreFuncionario(String nombre) {
        Query query = getEntityManager().createNamedQuery("MuUsuario.findByNameFuncionarios");
        query.setParameter("nombre", "%" + nombre + "%");
        List<MuUsuario> resultList = query.getResultList();
        return resultList;
    }
    public List<MuUsuario> busacarPorNombreFamiliar(String nombre) {
        Query query = getEntityManager().createNamedQuery("MuUsuario.findByNameFamiliar");
        query.setParameter("nombre", "%" + nombre + "%");
        List<MuUsuario> resultList = query.getResultList();
        return resultList;
    }
    public boolean buscarPorEmail(String usuemail) {
        Query query = getEntityManager().createNamedQuery("MuUsuario.findByUsuEmail");
        query.setParameter("usuEmail", usuemail);
        List<MuUsuario> resultList = query.getResultList();
        return !resultList.isEmpty();

    }
    public boolean buscarPorNombreUsuario(String nombreUsuario) {
        Query query = getEntityManager().createNamedQuery("MuUsuario.findByUsuNombreUsuario");
        query.setParameter("usuNombreUsuario", nombreUsuario);
        List<MuUsuario> resultList = query.getResultList();
        return !resultList.isEmpty();
    }
    
    public boolean buscarPorNumeroIdentificacion(Long numeroIdentificacion) {
        Query query = getEntityManager().createNamedQuery("MuUsuario.findByUsuIdentificacion");
        query.setParameter("usuIdentificacion", numeroIdentificacion);
        List<MuUsuario> resultList = query.getResultList();
        return !resultList.isEmpty();

    }
    public List<MuUsuario> buscarPorIdentificacionEstudiante(String usuidentificacion) {
        Query query = getEntityManager().createNamedQuery("MuUsuario.findByIdentiEstudiante");
        query.setParameter("usuIdentificacion", usuidentificacion+"%");
        List<MuUsuario> resultList = query.getResultList();
        return resultList;

    }

    public List<MuUsuario> buscarPorIdentificacionFamiliares(String usuidentificacion) {
        Query query = getEntityManager().createNamedQuery("Usuario.findByIdentiFamiliar");
        query.setParameter("usuIdentificacion", usuidentificacion+"%");
        List<MuUsuario> resultList = query.getResultList();
        return resultList;

    }

    public List<MuUsuario> buscarPorIdentificacionFuncionario(String usuidentificacion) {
        Query query = getEntityManager().createNamedQuery("Usuario.findByIdentiFuncionarios");
        query.setParameter("usuIdentificacion", usuidentificacion+"%");
        List<MuUsuario> resultList = query.getResultList();
        return resultList;
    }
    
      
    
    public List<MuUsuario> buscarUsuarioPorNombreUsuario(String nombreUsuario) {
        Query query = getEntityManager().createNamedQuery("MuUsuario.findByUsuNombreUsuario");
        query.setParameter("usuNombreUsuario", nombreUsuario);
        return query.getResultList();
    }

    

    public List<MuUsuario> buscarPorFamiliares() {
        Query query = getEntityManager().createNamedQuery("MuUsuario.findByFamiliares");
        query.setParameter("ocupacionHD", "Hijo de docente");
        query.setParameter("ocupacionED", "Esposo de docente");
        query.setParameter("ocupacionEAD", "Esposa de docente");
        query.setParameter("ocupacionAD", "Acompañante de docente");
        query.setParameter("ocupacionHA", "Hijo de administrativo");
        query.setParameter("ocupacionEA", "Esposo de administrativo");
        query.setParameter("ocupacionEAA", "Esposa de administrativo");
        query.setParameter("ocupacionAA", "Acompañante de administrativo");
        List<MuUsuario> resultList = query.getResultList();
        return resultList;
    }

   
    public MuUsuario buscarUsuarioPorEmail(String usuemail) {
        Query query = getEntityManager().createNamedQuery("MuUsuario.findByUsuEmail");
        query.setParameter("usuemail", usuemail);
        List<MuUsuario> listado = query.getResultList();
        if (listado.isEmpty()) {
            return null;
        }
        return listado.get(0);
    }

    public List<MuUsuario> buscarTodos() {
        Query query = getEntityManager().createNamedQuery("MuUsuario.findAll");
        List<MuUsuario> resultList = query.getResultList();
        return resultList;
    }
    
    public List<MuUsuario> buscarPorNombresApellidos(String nombresApellidos) {
        Query query = getEntityManager().createNamedQuery("MuUsuario.findByNombresApellidos");
        query.setParameter("nombresApellidos", "%" + nombresApellidos + "%");
        List<MuUsuario> resultList = query.getResultList();
        return resultList;
    }
    /*public List<MuUsuario> buscarPorNombresApellidos(String nombresApellidos) {
        Query query = getEntityManager().createNamedQuery("MuUsuario.findByNombresApellidos");
        query.setParameter("nombresApellidos", "%" + nombresApellidos + "%");
        query.setParameter("ocupacionD", "Docente");
        query.setParameter("ocupacionA", "Administrativo");
        List<MuUsuario> resultList = query.getResultList();
        return resultList;
    }*/
    
    public List<MuUsuario> buscarPorNombresApellidosFuncionarios(String nombresApellidos) {
        Query query = getEntityManager().createNamedQuery("MuUsuario.findByNombresApellidosFuncionarios");
        query.setParameter("nombresApellidos", "%" + nombresApellidos + "%");
        query.setParameter("ocupacionD", "Docente");
        query.setParameter("ocupacionA", "Administrativo");
        List<MuUsuario> resultList = query.getResultList();
        return resultList;
    }
    public List<MuUsuario> buscarPorNombresApellidosEstudiantes(String nombresApellidos) {
        Query query = getEntityManager().createNamedQuery("MuUsuario.findByNombresApellidosEstudiantes");
        query.setParameter("nombresApellidos", "%" + nombresApellidos + "%");
        query.setParameter("ocupacionE", "Estudiante");
        List<MuUsuario> resultList = query.getResultList();
        return resultList;
    }
    public List<MuUsuario> buscarPorNombresApellidosFamiliares(String nombresApellidos) {
        Query query = getEntityManager().createNamedQuery("MuUsuario.findByNombresApellidosFamiliares");
        query.setParameter("nombresApellidos", "%" + nombresApellidos + "%");
        query.setParameter("ocupacionHD", "Hijo de docente");
        query.setParameter("ocupacionED", "Esposo de docente");
        query.setParameter("ocupacionEAD", "Esposa de docente");
        query.setParameter("ocupacionAD", "Acompañante de docente");
        query.setParameter("ocupacionHA", "Hijo de administrativo");
        query.setParameter("ocupacionEA", "Esposo de administrativo");
        query.setParameter("ocupacionEAA", "Esposa de administrativo");
        query.setParameter("ocupacionAA", "Acompañante de administrativo");
        List<MuUsuario> resultList = query.getResultList();
        return resultList;
    }
    
    public List<MuUsuario> buscarPorContenidoNombres(String nombres) {
        Query query = getEntityManager().createNamedQuery("MuUsuario.findByContainNombres");
        query.setParameter("nombres", "%" + nombres + "%");
        List<MuUsuario> resultList = query.getResultList();
        return resultList;
    }
    
    public List<MuUsuario> buscarPorContenidoApellidos(String apellido1) {
        Query query = getEntityManager().createNamedQuery("MuUsuario.findByContainApellidos");
        query.setParameter("apellido", "%" + apellido1 + "%");
        List<MuUsuario> resultList = query.getResultList();
        return resultList;
    }
}
