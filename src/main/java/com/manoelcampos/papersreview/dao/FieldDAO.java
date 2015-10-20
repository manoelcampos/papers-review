package com.manoelcampos.papersreview.dao;

import com.manoelcampos.papersreview.model.Field;
import java.util.List;

/**
 *
 * @author Manoel Campos da Silva Filho <manoelcampos at gmail.com>
 */
public interface FieldDAO extends DAO<Field> {
    List<Field> listByProject(final Long projectId);
}
