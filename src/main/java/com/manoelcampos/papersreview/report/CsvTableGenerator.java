package com.manoelcampos.papersreview.report;

/**
 * @author <a href="https://about.me/manoelcampos">Manoel Campos da Silva Filho</a>
 */
public final class CsvTableGenerator extends AbstractTableGenerator {
    @Override
    protected void openTable() {}

    @Override
    protected void closeTable() {}

    @Override
    protected void insertCaption() {}

    @Override
    protected void insertColumnHeaders(String groupHeaders) {
        appendLine(getListAsString(getColumnHeaders(), ";"));
        appendLine("<br/>");
    }

    @Override
    protected String generateGroupHeaders() {
        return "";
    }

    @Override
    protected String getFormattedColumnHeader(String title, boolean rotate) {
        return escape(title);
    }

    @Override
    protected void insertRows() {
        getRows().forEach(row -> appendLine(row.toString()));
    }

    @Override
    protected String getFormattedGroup(String group, int index) {
        return "";
    }

    @Override
    public TableRow newRow() {
        return new CsvTableRow(this);
    }

    @Override
    final public String escape(String data) {
        return data;
    }

    @Override
    public String getTick() {
        return "x";
    }
}
