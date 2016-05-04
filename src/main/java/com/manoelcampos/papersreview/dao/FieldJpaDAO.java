package com.manoelcampos.papersreview.dao;

import com.manoelcampos.papersreview.model.Field;
import com.manoelcampos.papersreview.model.FieldGroup;
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
    public List<Field> listByProject(final Project project){
        final String jpql = 
                String.format("select o from %s o where o.project = :project", getGenericClassName());
        final TypedQuery<Field> qry = createQuery(jpql);
        qry.setParameter("project", project);
        return qry.getResultList();
    }


    @Override
    public List<Field> getFieldsToBeShownInReports(final Project project) {
        final String jpql =
                "select f from Field f where f.project = :project and f.showInReports=true order by f.description";
        return createQuery(jpql).setParameter("project", project).getResultList();
    }


    @Override
    public List<Field> getNonSubjectiveFieldsToBeShownInReports(final Project project, final Field field) {
        String where = "";
        if(!field.equals(Field.NULL))
            where = " and f = :field ";

        final String jpql =
                " select f from Field f where f.project = :project and " +
                " f.fieldType.abbreviation <> 'S' and f.showInReports=true " +
                where + " order by f.description ";
        TypedQuery<Field> query = createQuery(jpql).setParameter("project", project);
        if(!where.isEmpty())
            query.setParameter("field", field);
        return query.getResultList();
    }

    @Override
    public List<Field> getNonSubjectiveFieldsToBeShownInReports(Project project, FieldGroup fieldGroup) {
        String where = "";
        if(!fieldGroup.equals(FieldGroup.NULL))
            where = " and f.fieldGroup = :fieldGroup ";

        final String jpql =
                " select f from Field f where f.project = :project and " +
                        " f.fieldType.abbreviation <> 'S' and f.showInReports=true " +
                        where + " order by f.description ";
        TypedQuery<Field> query = createQuery(jpql).setParameter("project", project);
        if(!where.isEmpty())
            query.setParameter("fieldGroup", fieldGroup);
        return query.getResultList();
    }

}
