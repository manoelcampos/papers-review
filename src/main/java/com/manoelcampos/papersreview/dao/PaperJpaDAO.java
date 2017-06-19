package com.manoelcampos.papersreview.dao;

import com.manoelcampos.papersreview.model.*;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Manoel Campos da Silva Filho <manoelcampos at gmail.com>
 */
public class PaperJpaDAO extends JpaDAO<Paper> implements PaperDAO {

    public PaperJpaDAO(EntityManager em) {
        super(Paper.class, em);
    }

    @Override
    public boolean save(Paper o) {
        if(o.getPaperType() == PaperType.NULL){
            o.setPaperType(null);
        }

        if(o.getStatus() == PaperStatus.NULL){
            o.setStatus(null);
        }

        return super.save(o);
    }

    @Override
    public List<Paper> listBySearchSession(SearchSession s) {
        final String jpql = 
                String.format(
                        "select o from %s o where  o.searchSession = :s", 
                        getGenericClassName());
        final TypedQuery<Paper> qry = createQuery(jpql);
        qry.setParameter("s", s);
        return qry.getResultList();
    }

    @Override
    public List<Paper> search(final Paper searchCriteria) {
        PaperSearchQueryBuilder builder = new PaperSearchQueryBuilder(searchCriteria);
        TypedQuery<Paper> qry = createQuery(builder.getJpql());
        for(String paramName: builder.getParams().keySet())
            qry.setParameter(paramName, builder.getParams().get(paramName));
        
        return qry.getResultList();
    }

    @Override
    public List<Paper> listApprovedNonSurveyPapersInFinalPhaseWithDefinedTypeByProject(final Project project) {
        final String jpql =
            String.format(
            " select p from Paper p join p.paperType " +
            " where p.searchSession.project = :project" +
            " and p.survey <> 1 and p.status = (%s)", PaperStatusJpaDAO.LAST_ACCEPTED_STATUS_PHASE_JPQL);

        return createQuery(jpql).setParameter("project", project).getResultList();
    }


}
