package com.manoelcampos.papersreview.report;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author <a href="https://about.me/manoelcampos">Manoel Campos da Silva Filho</a>
 */
public final class LatexTableRow implements TableRow {
    private final LatexReportTableGenerator generator;
    private List<String> columns;

    public LatexTableRow(final LatexReportTableGenerator generator){
        this.generator = generator;
        this.columns = new ArrayList<String>();
    }

    @Override
    public TableRow addColumn(final String data) {
        this.columns.add(generator.escape(data));
        return this;
    }

    @Override
    public TableRow addColumn(final List<String> data) {
        StringBuilder sb = new StringBuilder(data.size());
        String s;
        Iterator<String> i = data.iterator();
        while(i.hasNext()){
            s = i.next();
            sb.append(s);
            if(i.hasNext())
                sb.append(", ");
        }

        addColumn(sb.toString());
        return this;
    }

    public List<String> getColumns() {
        return columns;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(columns.size()+2);
        sb.append(StringUtils.join(columns.toArray(), " &amp; "));
        sb.append("\n\t\t \\\\ \\hline \n");

        return sb.toString();
    }
}
