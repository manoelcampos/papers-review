package com.manoelcampos.papersreview.dao;

import com.manoelcampos.papersreview.model.FieldGroup;
import com.manoelcampos.papersreview.model.Project;

import java.util.List;

/**
 * @author <a href="https://about.me/manoelcampos">Manoel Campos da Silva Filho</a>
 */
public interface FieldGroupDAO extends DAO<FieldGroup>{
    List<FieldGroup> listByProject(final Project project);
}
