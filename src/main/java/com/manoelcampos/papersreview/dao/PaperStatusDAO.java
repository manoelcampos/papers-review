package com.manoelcampos.papersreview.dao;

import com.manoelcampos.papersreview.model.PaperStatus;

import java.util.List;

/**
 * @author <a href="https://about.me/manoelcampos">Manoel Campos da Silva Filho</a>
 */
public interface PaperStatusDAO extends DAO<PaperStatus> {
    PaperStatus getLastAcceptedStatusPhase();
}
