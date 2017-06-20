package com.manoelcampos.papersreview.report;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 *
 * @author <a href="https://about.me/manoelcampos">Manoel Campos da Silva Filho</a>
 */
public abstract class AbstractTableGenerator implements TableGenerator {
    private String caption;
    private List<String> columnHeaders;
    private Map<String, Integer> columnGroups;

    private List<TableRow> rows;
    private StringBuilder sb;
    private String tableId;


    public AbstractTableGenerator(){
        clear();
    }

    @Override
    public TableGenerator setTableId(final String id) {
        this.tableId = id;
        return this;
    }

    @Override
    public String getTableId() {
        return tableId;
    }

    protected List<String> getColumnHeaders(){
        return columnHeaders;
    }

    protected String getListAsString(final List<String> list, final String separator){
        return StringUtils.join(list.toArray(), separator);
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
    public TableGenerator setCaption(final String caption) {
        this.caption = caption;
        return this;
    }

    @Override
    public void clear() {
        this.caption = "";
        this.columnHeaders = new ArrayList<>();
        this.rows = new ArrayList<>();
        this.sb = new StringBuilder();
        this.columnGroups = new TreeMap<>();
    }

    protected abstract String getFormattedColumnHeader(String title, boolean rotate);

    @Override
    public TableGenerator addColumnHeader(final String title, boolean rotate) {
        this.columnHeaders.add(getFormattedColumnHeader(title, rotate));
        return this;
    }

    @Override
    public TableGenerator addColumnHeader(final String title) {
        this.columnHeaders.add(getFormattedColumnHeader(title, false));
        return this;
    }

    @Override
    public TableGenerator addColumnHeader(final String title, final String group) {
        addColumnHeader(title);
        addColumnGroup(group, 1);
        return this;
    }

    @Override
    public Map<String, Integer> getColumnGroups() {
        return columnGroups;
    }

    @Override
    public TableGenerator addColumnGroup(String group, Integer groupSize) {
        columnGroups.put(escape(group), groupSize);
        return this;
    }

    protected AbstractTableGenerator appendLine(final String text){
        return append(String.format("%s%s", text, NEWLINE));
    }

    protected AbstractTableGenerator append(final String text){
        sb.append(text);
        return this;
    }

    protected abstract void openTable();

    protected abstract void closeTable();

    protected abstract void insertCaption();

    protected abstract void insertColumnHeaders(String groupHeaders);

    protected abstract String generateGroupHeaders();

    @Override
    public TableGenerator addRow(TableRow row) {
        this.rows.add(row);
        return this;
    }

    @Override
    public String build() {
        this.sb = new StringBuilder();
        openTable();
        insertColumnHeaders(generateGroupHeaders());
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

    protected abstract String getFormattedGroup(String group, int index);
}
