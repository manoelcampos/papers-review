package com.manoelcampos.papersreview.dao;

import com.manoelcampos.papersreview.model.Field;
import com.manoelcampos.papersreview.model.Project;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Manoel Campos da Silva Filho <manoelcampos at gmail.com>
 */
public class FieldJpaDAO extends JpaDAO<Field> implements FieldDAO {

    public FieldJpaDAO(EntityManager em) {
        super(Field.class, em);
    }
    
    @Override
    public List<Field> listByProject(final Long projectId){
        final String jpql = 
                String.format("select o from %s o where o.project = :project", getGenericClassName());
        final TypedQuery<Field> qry = createQuery(jpql);
        qry.setParameter("project", new Project(projectId));
        return qry.getResultList();
    }
    
}
