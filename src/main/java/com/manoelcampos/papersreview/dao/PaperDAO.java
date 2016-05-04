package com.manoelcampos.papersreview.dao;

import com.manoelcampos.papersreview.model.Paper;
import com.manoelcampos.papersreview.model.Project;
import com.manoelcampos.papersreview.model.SearchSession;
import java.util.List;

/**
 *
 * @author Manoel Campos da Silva Filho <manoelcampos at gmail.com>
 */
public interface PaperDAO extends DAO<Paper> {
    List<Paper> listBySearchSession(final SearchSession s);
    List<Paper> search(final Paper searchCriteria);
    List<Paper> listApprovedNonSurveyPapersInFinalPhaseWithDefinedTypeByProject(final Project project);
}
