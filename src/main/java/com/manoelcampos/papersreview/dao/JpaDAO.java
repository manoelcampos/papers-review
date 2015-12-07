package com.manoelcampos.papersreview.dao;

import com.manoelcampos.papersreview.model.EntityInterface;
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
public class JpaDAO<T extends EntityInterface> implements DAO<T> {
    @PersistenceContext()
    private final EntityManager em;

    private final Class<T> genericClass;
    
    public JpaDAO(Class<T> genericClass, EntityManager em){
        this.genericClass = genericClass;
        this.em = em;
    }
    
    public String getGenericClassName(){ return genericClass.getSimpleName(); }
    
    protected TypedQuery<T> createQuery(final String jpql){ 
        return getEm().createQuery(jpql, genericClass);
    }

    @Override
    public boolean save(T o) {
        saveWithoutFlush(o);
        getEm().flush();
        return true;
    }

    @Override
    public boolean saveWithoutFlush(T o){
        if(o.getId() == null || o.getId() == 0)
            getEm().persist(o);
        else getEm().merge(o);
        return true;
    }        
    
    @Override
    public boolean remove(T o) {
            getEm().remove(o);
            getEm().flush();
            return true;
    }

    @Override
    public List<T> list() {
        CriteriaQuery<T> query = getEm().getCriteriaBuilder().createQuery(genericClass);
        query.from(genericClass);
        return getEm().createQuery(query).getResultList();
    }

    @Override
    public T findById(Long id) {
        if(id == null || id <= 0)
            return null;
        
        return getEm().find(genericClass,id);
    }
    
    @Override
    public void flush(){
        getEm().flush();
    }

    protected EntityManager getEm() {
        return em;
    }
}
