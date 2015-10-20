package com.manoelcampos.papersreview.dao;

import com.manoelcampos.papersreview.model.Paper;
import com.manoelcampos.papersreview.model.SearchSection;
import java.util.List;

/**
 *
 * @author Manoel Campos da Silva Filho <manoelcampos at gmail.com>
 */
public interface PaperDAO extends DAO<Paper> {
    List<Paper> listBySearchSection(final SearchSection s);
    boolean saveListOfPapers(final List<Paper> list);
}
