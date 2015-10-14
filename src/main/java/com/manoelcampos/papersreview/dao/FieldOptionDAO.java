package com.manoelcampos.papersreview.dao;

import com.manoelcampos.papersreview.model.FieldOption;
import java.util.List;

/**
 *
 * @author Manoel Campos da Silva Filho <manoelcampos at gmail.com>
 */
public interface FieldOptionDAO extends DAO<FieldOption> {
    List<FieldOption> listByField(final Long fieldId);
}
