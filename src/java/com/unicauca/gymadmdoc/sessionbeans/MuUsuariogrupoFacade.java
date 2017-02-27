/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.gymadmdoc.sessionbeans;

import com.unicauca.gymadmdoc.entities.MuUsuariogrupo;
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
public class MuUsuariogrupoFacade extends AbstractFacade<MuUsuariogrupo> {

    @PersistenceContext(unitName = "Gym_Adm_DocPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MuUsuariogrupoFacade() {
        super(MuUsuariogrupo.class);
    }
    
    public List<MuUsuariogrupo> buscarPorNombreUsuario(String usunombreusuario)
    {
        Query query = getEntityManager().createNamedQuery("MuUsuariogrupo.findByUsuNombre");
        query.setParameter("usunombre", usunombreusuario);
        List<MuUsuariogrupo> resultList = query.getResultList();
        return resultList;
    }
    public int actualizarNombreUsuario(String gruid,Long usuid,String usunombreusuario)
    {
        Query query = getEntityManager().createQuery(
        "UPDATE Usuariogrupo u SET u.usunombreusuario = "+"'"+usunombreusuario+"'"+
        " WHERE u.usuariogrupoPK.usuid = :usuid AND u.usuariogrupoPK.gruid = :gruid");
        query.setParameter("usuid", usuid);
        query.setParameter("gruid", gruid);
        return query.executeUpdate();
          
    }
}
