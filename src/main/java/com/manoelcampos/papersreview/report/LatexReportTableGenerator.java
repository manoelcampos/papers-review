package com.manoelcampos.papersreview.report;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author <a href="https://about.me/manoelcampos">Manoel Campos da Silva Filho</a>
 */
public final class LatexReportTableGenerator extends AbstractReportTableGenerator {
    @Override
    protected AbstractReportTableGenerator appendLine(String text) {
        return super.appendLine(String.format("%s<br/>", text));
    }

    protected void openTable() {
        append("\\begin{longtable}{|");
        getColumnHeaders().forEach(c -> append("l|"));
        appendLine("}");
    }

    protected void closeTable() {
        appendLine("\\end{longtable}");
    }

    protected void insertCaption(){
        appendLine(String.format("\t\\caption{%s\\label{%s}}\\\\", getCaption(), getTableId()));
    }

    protected void insertColumnHeaders(){
        appendLine(
                String.format("\t\\hline %s \\\\",
                    getColumnHeadersAsString(" &amp; ")));

        appendLine("\t\\hline").appendLine("\t\\endhead");
    }

    @Override
    public ReportTableGenerator addColumnHeader(final String title) {
        return super.addColumnHeader(String.format("\\textbf{%s}", escape(title)));
    }

    @Override
    public TableRow newRow() {
        return new LatexTableRow(this);
    }

    protected void insertRows() {
        getRows().forEach(row -> appendLine(row.toString()));
    }

}
