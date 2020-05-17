/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kursmania.sessions;

import com.kursmania.jpa.entities.Kupon;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Andrej Kubat
 */
@Stateless
public class KuponFacade extends AbstractFacade<Kupon> {

    @PersistenceContext(unitName = "KursManiaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public KuponFacade() {
        super(Kupon.class);
    }
    
}
