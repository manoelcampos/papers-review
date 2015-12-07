package com.manoelcampos.papersreview.dao;

import com.manoelcampos.papersreview.dto.PaperCountByStatusDTO;
import com.manoelcampos.papersreview.model.Project;
import com.manoelcampos.papersreview.model.Repository;
import com.manoelcampos.papersreview.model.SearchSession;
import java.util.List;

/**
 *
 * @author Manoel Campos da Silva Filho <manoelcampos at gmail.com>
 */
public interface SearchSessionDAO extends DAO<SearchSession> {
    List<SearchSession> listByProjectAndRepository(final Project p, final Repository r);
    List<SearchSession> listByProject(final Project project);
    List<PaperCountByStatusDTO> getPaperCountByStatus(final SearchSession s);
}
