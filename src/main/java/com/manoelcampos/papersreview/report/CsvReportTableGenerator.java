package com.manoelcampos.papersreview.report;

/**
 * @author <a href="https://about.me/manoelcampos">Manoel Campos da Silva Filho</a>
 */
public final class CsvReportTableGenerator extends AbstractReportTableGenerator {
    @Override
    protected void openTable() {}

    @Override
    protected void closeTable() {}

    @Override
    protected void insertCaption() {}

    @Override
    protected void insertColumnHeaders() {
        appendLine(getColumnHeadersAsString(";"));
    }

    @Override
    protected void insertRows() {
        getRows().forEach(row -> appendLine(row.toString()));
    }

    @Override
    public TableRow newRow() {
        return new CsvTableRow(this);
    }

    @Override
    public String escape(String data) {
        return data;
    }
}
