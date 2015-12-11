package com.manoelcampos.papersreview.report;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author <a href="https://about.me/manoelcampos">Manoel Campos da Silva Filho</a>
 */
public abstract class AbstractReportTableGenerator implements ReportTableGenerator {
    private String caption;
    private List<String> columnHeaders;
    private List<TableRow> rows;
    private StringBuilder sb;
    private String tableId;

    public AbstractReportTableGenerator(){
        clear();
    }

    @Override
    public ReportTableGenerator setTableId(final String id) {
        this.tableId = id;
        return this;
    }

    @Override
    public String getTableId() {
        return tableId;
    }

    public String escape(String data){
        data = StringUtils.replace(data, "$", "\\$");
        data = StringUtils.replace(data, "#", "\\#");
        data = StringUtils.replace(data, "_", "\\_");
        data = StringUtils.replace(data, "\\", "\\\\");
        return data.replaceAll("&", "\\&");
    }

    protected List<String> getColumnHeaders(){
        return columnHeaders;
    }

    protected String getColumnHeadersAsString(final String separator){
        return StringUtils.join(columnHeaders.toArray(), separator);
    }

    @Override
    public List<TableRow> getRows() {
        return rows;
    }

    @Override
    public String getCaption() {
        return caption;
    }

    @Override
    public void clear() {
        this.caption = "";
        this.columnHeaders = new ArrayList<>();
        this.rows = new ArrayList<>();
        this.sb = new StringBuilder();
    }

    @Override
    public ReportTableGenerator addColumnHeader(final String title) {
        this.columnHeaders.add(title);
        return this;
    }


    protected AbstractReportTableGenerator appendLine(final String text){
        return append(String.format("%s\n", text));
    }

    protected AbstractReportTableGenerator append(final String text){
        sb.append(text);
        return this;
    }

    protected abstract void openTable();

    protected abstract void closeTable();

    protected abstract void insertCaption();

    protected abstract void insertColumnHeaders();

    @Override
    public ReportTableGenerator setCaption(final String caption) {
        this.caption = caption;
        return this;
    }

    @Override
    public ReportTableGenerator addRow(TableRow row) {
        this.rows.add(row);
        return this;
    }

    @Override
    public String build() {
        this.sb = new StringBuilder();
        openTable();
        insertCaption();
        insertColumnHeaders();
        insertRows();
        closeTable();

        return sb.toString();
    }

    @Override
    public String buildAndClear() {
        final String result = build();
        clear();
        return result;
    }

    protected abstract void insertRows();
}
