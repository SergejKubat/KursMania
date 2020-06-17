
package com.kursmania.sessions;

import com.kursmania.jpa.entities.Kupon;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
