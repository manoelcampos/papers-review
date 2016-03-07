package com.manoelcampos.papersreview.dao;

import com.manoelcampos.papersreview.model.PaperStatus;

import javax.persistence.EntityManager;

/**
 * @author <a href="https://about.me/manoelcampos">Manoel Campos da Silva Filho</a>
 */
public class PaperStatusJpaDAO extends JpaDAO<PaperStatus> implements PaperStatusDAO {
    public static final String LAST_ACCEPTED_STATUS_PHASE_JPQL =
            "select s from PaperStatus s where s.accepted = true and s.reviewPhase.id = " +
            "(select max(r.id) from ReviewPhase  r)";

    public PaperStatusJpaDAO(EntityManager em){
        super(PaperStatus.class, em);
    }


    @Override
    public PaperStatus getLastAcceptedStatusPhase() {
        return createQuery(LAST_ACCEPTED_STATUS_PHASE_JPQL).getSingleResult();
    }
}
