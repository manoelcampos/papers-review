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
    public TableRow addColumn(List<String> data) {
        StringBuilder sb = new StringBuilder(data.size());
        Iterator<String> i = columns.iterator();
        while(i.hasNext()){
            String s = i.next();
            if(i.hasNext())
                s = String.format("%s, ", s);
            if(s.contains(" "))
                s = s + "<br/>";
            sb.append(s);
        }

        addColumn(sb.toString());
        return this;
    }

    @Override
    public List<String> getColumns() {
        return columns;
    }
}
