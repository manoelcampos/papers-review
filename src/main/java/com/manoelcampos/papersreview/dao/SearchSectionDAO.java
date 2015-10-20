package com.manoelcampos.papersreview.dao;

import com.manoelcampos.papersreview.model.Project;
import com.manoelcampos.papersreview.model.Repository;
import com.manoelcampos.papersreview.model.SearchSection;
import java.util.List;

/**
 *
 * @author Manoel Campos da Silva Filho <manoelcampos at gmail.com>
 */
public interface SearchSectionDAO extends DAO<SearchSection> {
    List<SearchSection> listByProjectAndRepository(final Project p, final Repository r);
    List<SearchSection> listByProject(final Long projectId);
}
