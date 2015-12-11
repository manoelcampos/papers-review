package com.manoelcampos.papersreview.report;

import java.util.ArrayList;
import java.util.List;

/**
 * @author manoelcampos
 */
public interface ReportTableGenerator {
    ReportTableGenerator setCaption(final String caption);
    String getCaption();
    ReportTableGenerator setTableId(final String id);
    String getTableId();
    ReportTableGenerator addColumnHeader(final String title);
    TableRow newRow();
    ReportTableGenerator addRow(TableRow row);
    String build();
    String buildAndClear();
    List<TableRow> getRows();
    void clear();
}
