package com.manoelcampos.papersreview.report;

import java.util.Iterator;
import java.util.List;

/**
 * @author <a href="https://about.me/manoelcampos">Manoel Campos da Silva Filho</a>
 */
public final class CsvTableRow extends AbstractTableRow {
    public CsvTableRow(ReportTableGenerator generator) {
        super(generator);
    }

    @Override
    protected String formatColumn(int index) {
        if(index < getColumns().size()-1)
            return String.format("%s;", getColumns().get(index));

        return getColumns().get(index);
    }
}
