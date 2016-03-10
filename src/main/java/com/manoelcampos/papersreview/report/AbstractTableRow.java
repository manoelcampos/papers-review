package com.manoelcampos.papersreview.report;

import java.util.ArrayList;
import java.util.Iterator;
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


    @Override
    public TableRow addColumn(List<String> dataList) {
        StringBuilder sb = new StringBuilder(dataList.size());
        Iterator<String> i = dataList.iterator();
        while(i.hasNext()){
            String data = generator.escape(i.next()), column = data;
            if(i.hasNext())
                column = String.format("%s, ", data);
            if(data.contains(" "))
                column = String.format("%s<br/>", column);
            sb.append(column);
        }

        addColumnUnescaped(sb.toString());
        return this;
    }

    @Override
    public List<String> getColumns() {
        return columns;
    }
}
