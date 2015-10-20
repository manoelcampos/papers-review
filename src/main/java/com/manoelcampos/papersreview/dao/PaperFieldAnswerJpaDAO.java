package com.manoelcampos.papersreview.dao;

import com.manoelcampos.papersreview.model.Field;
import com.manoelcampos.papersreview.model.Paper;
import com.manoelcampos.papersreview.model.PaperFieldAnswer;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Manoel Campos da Silva Filho <manoelcampos at gmail.com>
 */
public class PaperFieldAnswerJpaDAO extends JpaDAO<PaperFieldAnswer> implements PaperFieldAnswerDAO {
    public PaperFieldAnswerJpaDAO(EntityManager em) {
        super(PaperFieldAnswer.class, em);
    }

    @Override
    public List<PaperFieldAnswer> listAnswersByPaperAndField(final Paper paper, final Field field) {
        final String jpql = 
                String.format(
                        "select o from %s o where o.paper = :p and o.field = :f", getGenericClassName());
        return createQuery(jpql).setParameter("p", paper).setParameter("f", field).getResultList();
    }
}
