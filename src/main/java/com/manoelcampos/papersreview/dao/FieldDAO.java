package com.manoelcampos.papersreview.dao;

import com.manoelcampos.papersreview.model.Field;
import com.manoelcampos.papersreview.model.FieldGroup;
import com.manoelcampos.papersreview.model.Project;

import java.util.List;

/**
 *
 * @author Manoel Campos da Silva Filho <manoelcampos at gmail.com>
 */
public interface FieldDAO extends DAO<Field> {
    List<Field> listByProject(final Project project);
    List<Field> getNonSubjectiveFieldsToBeShownInReports(final Project project, final Field field);
    List<Field> getNonSubjectiveFieldsToBeShownInReports(final Project project, final FieldGroup fieldGroup);
    List<Field> getFieldsToBeShownInReports(final Project project);
}
