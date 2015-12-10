package com.manoelcampos.papersreview.dao;

import com.manoelcampos.papersreview.model.Field;
import com.manoelcampos.papersreview.model.Paper;
import com.manoelcampos.papersreview.model.Project;

import java.util.List;

/**
 * Created by manoelcampos on 08/12/15.
 */
public interface PaperSummaryTableDAO {
    List<Paper> getPapersSummaryTable();
    List<Field> getFieldList();
    void setProject(final Project p);
}
