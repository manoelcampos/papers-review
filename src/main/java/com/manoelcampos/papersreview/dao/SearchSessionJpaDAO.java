package com.manoelcampos.papersreview.dao;

import com.manoelcampos.papersreview.dto.PaperCountByStatusDTO;
import com.manoelcampos.papersreview.model.Project;
import com.manoelcampos.papersreview.model.SearchSession;
import com.manoelcampos.papersreview.model.Repository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Manoel Campos da Silva Filho <manoelcampos at gmail.com>
 */
public class SearchSessionJpaDAO extends JpaDAO<SearchSession> implements SearchSessionDAO {
    public SearchSessionJpaDAO(EntityManager em) {
        super(SearchSession.class, em);
    }
    
    @Override
    public List<SearchSession> listByProjectAndRepository(final Project p, final Repository r){
        final String jpql = 
                String.format(
                        "select o from %s o where o.repository = :r and o.project = :p", 
                        getGenericClassName());
        final TypedQuery<SearchSession> qry = createQuery(jpql);
        qry.setParameter("r", r);
        qry.setParameter("p", p);
        return qry.getResultList();
    }

    @Override
    public List<SearchSession> listByProject(final Project project) {
        final String jpql = 
                String.format(
                        "select o from %s o where o.project = :p", 
                        getGenericClassName());
        final TypedQuery<SearchSession> qry = createQuery(jpql);
        qry.setParameter("p", project);
        return qry.getResultList();
    }

    @Override
    public List<PaperCountByStatusDTO> getPaperCountByStatus(SearchSession s) {
        final String jpql = "select case when p.status.id in (1,3) then 'Rejected' " +
                "  when p.status.id = 2 then 'Accepted on Selection' " +
                "  when p.status.id = 4 then 'Accepted on Extraction' " +
                "  else 'Not Classified' " +
                " end, count(p) " +
                " from Paper p where p.searchSession = :searchSession group by 1";

        return
                getEm().createQuery(jpql, PaperCountByStatusDTO.class)
                    .setParameter("searchSession", s)
                    .getResultList();
    }

}
