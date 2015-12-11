package com.manoelcampos.papersreview.dao;

import com.manoelcampos.papersreview.model.Field;
import com.manoelcampos.papersreview.model.Project;

import java.util.List;

/**
 *
 * @author Manoel Campos da Silva Filho <manoelcampos at gmail.com>
 */
public interface FieldDAO extends DAO<Field> {
    List<Field> listByProject(final Project project);
    List<Field> getNonSubjectiveFieldsToBeShownInReports(final Project project);
    List<Field> getFieldsToBeShownInReports(final Project project);
}
