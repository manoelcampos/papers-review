package com.manoelcampos.papersreview.dao;

import com.manoelcampos.papersreview.model.EndUser;
import com.manoelcampos.papersreview.model.Project;
import java.util.List;

/**
 *
 * @author Manoel Campos da Silva Filho <manoelcampos at gmail.com>
 */
public interface ProjectDAO extends DAO<Project> {
    List<Project> listByEndUser(final EndUser endUser);
}
