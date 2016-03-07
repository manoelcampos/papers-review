package com.manoelcampos.papersreview.report;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="https://about.me/manoelcampos">Manoel Campos da Silva Filho</a>
 */
public abstract class AbstractTableRow implements TableRow {
    private final ReportTableGenerator generator;
    private List<String> columns;

    public AbstractTableRow(ReportTableGenerator generator){
        this.generator = generator;
        this.columns = new ArrayList<>();
    }

    @Override
    public TableRow addColumn(final String data) {
        this.columns.add(generator.escape(data));
        return this;
    }

    @Override
    public TableRow addColumnUnescaped(String data) {
        this.columns.add(data);
        return this;
    }

    protected abstract String formatColumn(final int index);

    @Override
    public TableRow addColumn(List<String> data) {
        StringBuilder sb = new StringBuilder(data.size());
        for(int i = 0; i < columns.size(); i++){
            sb.append(formatColumn(i));
        }

        addColumn(sb.toString());
        return this;

    }

    @Override
    public List<String> getColumns() {
        return columns;
    }
}
