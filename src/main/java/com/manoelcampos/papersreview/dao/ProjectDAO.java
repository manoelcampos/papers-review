package com.manoelcampos.papersreview.dao;

import com.manoelcampos.papersreview.dto.PaperCountByRepositoryDTO;
import com.manoelcampos.papersreview.dto.PaperCountByStatusDTO;
import com.manoelcampos.papersreview.dto.PaperCountByTypeDTO;
import com.manoelcampos.papersreview.model.EndUser;
import com.manoelcampos.papersreview.model.Project;

import java.util.List;

/**
 *
 * @author Manoel Campos da Silva Filho <manoelcampos at gmail.com>
 */
public interface ProjectDAO extends DAO<Project> {
    List<Project> listByEndUser(final EndUser endUser);
    List<PaperCountByStatusDTO> getPaperCountByStatus(Project p);
    List<PaperCountByRepositoryDTO> getPaperCountByRepository(final Project p);
    List<PaperCountByTypeDTO> getPaperCountByType(final Project p);
}
