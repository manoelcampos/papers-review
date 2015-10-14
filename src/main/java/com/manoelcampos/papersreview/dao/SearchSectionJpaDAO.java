package com.manoelcampos.papersreview.dao;

import com.manoelcampos.papersreview.model.Project;
import com.manoelcampos.papersreview.model.SearchSection;
import com.manoelcampos.papersreview.model.Repository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Manoel Campos da Silva Filho <manoelcampos at gmail.com>
 */
public class SearchSectionJpaDAO extends JpaDAO<SearchSection> implements SearchSectionDAO {
    public SearchSectionJpaDAO(EntityManager em) {
        super(SearchSection.class, em);
    }
    
    @Override
    public List<SearchSection> listByProjectAndRepository(final Project p, final Repository r){
        final String jpql = 
                String.format(
                        "select o from %s o where o.repository = :r and o.project = :p", 
                        getGenericClassName());
        final TypedQuery<SearchSection> qry = createQuery(jpql);
        qry.setParameter("r", r);
        qry.setParameter("p", p);
        return qry.getResultList();
    }
    
}
