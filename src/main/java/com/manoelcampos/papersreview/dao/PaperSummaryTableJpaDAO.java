package com.manoelcampos.papersreview.dao;

import com.manoelcampos.papersreview.model.Field;
import com.manoelcampos.papersreview.model.Paper;
import com.manoelcampos.papersreview.model.Project;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * http://stackoverflow.com/questions/12076238/return-a-subset-of-a-jpa-entity-as-a-array-of-maps-from-a-jpql-query
 * http://docs.jboss.org/hibernate/orm/4.1/manual/en-US/html/ch16.html#queryhql-select
 */
public class PaperSummaryTableJpaDAO implements PaperSummaryTableDAO {
    private final EntityManager em;
    private Project p;

    public PaperSummaryTableJpaDAO(final EntityManager em) {
        this.em = em;
    }

    public void setProject(final Project p){
        this.p = p;
    }

    @Override
    public List<Paper> getPapersSummaryTable() {
        List<Field> result = getFieldList();

        final String jpql =
                " select p " +
                " from Paper p join p.paperType " +
                " where p.searchSession.project = :project";

        return em.createQuery(jpql, Paper.class).setParameter("project", p).getResultList();
    }

    @Override
    public List<Field> getFieldList() {
        final String jpql = "select f from Field f where f.project = :project order by f.description";
        return em.createQuery(jpql, Field.class).setParameter("project", p).getResultList();
    }
}