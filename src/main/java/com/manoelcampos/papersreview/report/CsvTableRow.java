package com.manoelcampos.papersreview.report;

import org.apache.commons.lang3.StringUtils;

/**
 * @author <a href="https://about.me/manoelcampos">Manoel Campos da Silva Filho</a>
 */
public final class CsvTableRow extends AbstractTableRow {
    public CsvTableRow(ReportTableGenerator generator) {
        super(generator);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(getColumns().size()+2);
        sb.append(StringUtils.join(getColumns().toArray(), ";"));
        sb.append("\n<br/>");
        return sb.toString();
    }
}
