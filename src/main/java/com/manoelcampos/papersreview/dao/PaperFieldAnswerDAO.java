package com.manoelcampos.papersreview.dao;

import com.manoelcampos.papersreview.model.Field;
import com.manoelcampos.papersreview.model.PaperFieldAnswer;
import java.util.List;

/**
 *
 * @author Manoel Campos da Silva Filho <manoelcampos at gmail.com>
 */
public interface PaperFieldAnswerDAO extends DAO<PaperFieldAnswer> {
    List<PaperFieldAnswer> listAnswersByField(final Field field);
}
