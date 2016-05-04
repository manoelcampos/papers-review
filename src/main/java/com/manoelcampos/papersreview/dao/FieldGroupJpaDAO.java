package com.manoelcampos.papersreview.dao;

import com.manoelcampos.papersreview.model.Field;
import com.manoelcampos.papersreview.model.FieldGroup;
import com.manoelcampos.papersreview.model.Project;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author <a href="https://about.me/manoelcampos">Manoel Campos da Silva Filho</a>
 */
public class FieldGroupJpaDAO  extends JpaDAO<FieldGroup> implements FieldGroupDAO {
    public FieldGroupJpaDAO(EntityManager em) {
        super(FieldGroup.class, em);
    }

    @Override
    public List<FieldGroup> listByProject(Project project) {
        final String jpql =
                "select fg from FieldGroup fg where fg.project = :project order by fg.description";
        return createQuery(jpql).setParameter("project", project).getResultList();
    }
}
