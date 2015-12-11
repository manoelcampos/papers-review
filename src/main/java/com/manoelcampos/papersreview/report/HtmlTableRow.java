package com.manoelcampos.papersreview.report;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author <a href="https://about.me/manoelcampos">Manoel Campos da Silva Filho</a>
 */
public final class HtmlTableRow implements TableRow {
    private List<String> columns;

    public HtmlTableRow(){
        this.columns = new ArrayList<String>();
    }

    @Override
    public TableRow addColumn(final String data) {
        this.columns.add(data.toString());
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
            if(s.indexOf(" ")!= -1)
                sb.append("<br/>");
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
        sb.append("\t\t<tr>\n\t\t\t\t");
        for(String c: columns)
            sb.append(String.format("<td>%s</td>", c));
        sb.append("\n\t\t</tr>\n");

        return sb.toString();
    }
}
