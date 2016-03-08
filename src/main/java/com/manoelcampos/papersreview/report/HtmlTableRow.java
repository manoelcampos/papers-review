package com.manoelcampos.papersreview.report;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author <a href="https://about.me/manoelcampos">Manoel Campos da Silva Filho</a>
 */
public final class HtmlTableRow extends AbstractTableRow {
    public HtmlTableRow(ReportTableGenerator generator){
        super(generator);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(getColumns().size()+2);
        sb.append("\t\t<tr>\n\t\t\t\t");
        for(String c: getColumns())
            sb.append(String.format("<td>%s</td>", c));
        sb.append("\n\t\t</tr>\n");

        return sb.toString();
    }
}
