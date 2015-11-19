package com.manoelcampos.papersreview.dao;

import com.manoelcampos.papersreview.model.EndUser;
import com.manoelcampos.papersreview.model.Field;
import com.manoelcampos.papersreview.model.Project;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Manoel Campos da Silva Filho <manoelcampos at gmail.com>
 */
public class ProjectJpaDAO extends JpaDAO<Project> implements ProjectDAO {

    public ProjectJpaDAO( EntityManager em) {
        super(Project.class, em);
    }

    @Override
    public List<Project> listByEndUser(EndUser endUser) {
        final String jpql = 
                String.format("select o from %s o where o.endUser = :endUser", getGenericClassName());
        final TypedQuery<Project> qry = createQuery(jpql);
        qry.setParameter("endUser", endUser);
        return qry.getResultList();
    }
    
}
