package com.manoelcampos.papersreview.report;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author <a href="https://about.me/manoelcampos">Manoel Campos da Silva Filho</a>
 */
public final class HtmlReportTableGenerator extends AbstractReportTableGenerator {

    protected void openTable() {
        appendLine(String.format("<table id='%s' class='table-striped table-bordered'>", getTableId()));
    }

    protected void closeTable() {
        appendLine("</table>");
    }

    protected void insertCaption(){
        appendLine(String.format("\t<caption>%s</caption>", getCaption()));
    }

    protected void insertColumnHeaders(){
        appendLine(String.format("\t<thead><tr>%s</tr></thread>", getColumnHeadersAsString("")));
    }

    @Override
    public ReportTableGenerator addColumnHeader(final String title) {
        return super.addColumnHeader(String.format("<th>%s</th>", title));
    }

    @Override
    public TableRow newRow() {
        return new HtmlTableRow();
    }

    @Override
    public ReportTableGenerator addRow(TableRow row) {
        this.getRows().add(row);
        return this;
    }

    protected void insertRows() {
        appendLine("\t<tbody>");
        getRows().forEach(row -> appendLine(String.format("%s", row)));
        appendLine("\t</tbody>");
    }

}
