package com.manoelcampos.papersreview.report;

import org.apache.commons.lang3.StringUtils;

/**
 * @author <a href="https://about.me/manoelcampos">Manoel Campos da Silva Filho</a>
 */
public final class LatexTableRow extends AbstractTableRow {
    public LatexTableRow(TableGenerator generator){
        super(generator);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(getColumns().size()+2);
        sb.append(StringUtils.join(getColumns().toArray(), " &amp; "));
        sb.append(TableGenerator.NEWLINE).append("\t\t \\\\ \\hline ").append(TableGenerator.NEWLINE);

        return sb.toString();
    }
}
