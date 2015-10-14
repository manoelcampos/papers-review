package com.manoelcampos.papersreview.dao;

import com.manoelcampos.papersreview.model.Paper;
import com.manoelcampos.papersreview.model.SearchSection;
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
    public List<Paper> listBySearchSection(SearchSection s) {
        final String jpql = 
                String.format(
                        "select o from %s o where  o.searchSection = :s", 
                        getGenericClassName());
        final TypedQuery<Paper> qry = createQuery(jpql);
        qry.setParameter("s", s);
        return qry.getResultList();
    }
    
    
}
