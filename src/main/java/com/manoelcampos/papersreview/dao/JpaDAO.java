package com.manoelcampos.papersreview.dao;

import com.manoelcampos.papersreview.model.EntityClass;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;

/**
 * Implementa um DAO genérico por meio de JPA.
 * @author manoelcampos
 * @param <T>
 */
@Dependent
@Transactional() 
public class JpaDAO<T extends EntityClass> implements DAO<T> {
    @PersistenceContext()
    private EntityManager em;

    private final Class<T> genericClass;
    
    public JpaDAO(Class<T> genericClass, EntityManager em){
        this.genericClass = genericClass;
        this.em = em;
    }
    
    public String getGenericClassName(){ return genericClass.getSimpleName(); }
    
    protected TypedQuery<T> createQuery(final String jpql){ 
        return em.createQuery(jpql, genericClass);
    }

    @Override
    public boolean save(T o) { 
        if(o.getId() == null || o.getId() == 0)
            em.persist(o);
        else em.merge(o);
        em.flush();
        return true;
    }
    
    @Override
    public boolean remove(T o) {
            em.remove(o);
            em.flush();
            return true;
    }

    @Override
    public List list() {
        CriteriaQuery query = em.getCriteriaBuilder().createQuery(genericClass);
        query.from(genericClass);
        return em.createQuery(query).getResultList();
    }

    @Override
    public T findById(Long id) {
        return em.find(genericClass,id);
    }
}
