package com.manoelcampos.papersreview.report;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author <a href="https://about.me/manoelcampos">Manoel Campos da Silva Filho</a>
 */
public class HtmlReportTableGenerator implements ReportTableGenerator {
    @Override
    public String openTable() {
        return "<table class='table-striped table-bordered'>";
    }

    @Override
    public String caption(final String title) {
        return String.format("<caption>%s</caption>", title);
    }

    @Override
    public String openHead() {
        return "<thead>";
    }

    @Override
    public String columnHeader(final String title) {
        return String.format("<th>%s</th>", title);
    }

    @Override
    public String closeHead() {
        return "</thead>";
    }

    @Override
    public String openBody() {
        return "<tbody>";
    }

    @Override
    public String openRow() {
        return "<tr>";
    }

    @Override
    public String column(final String data) {
        return String.format("<td>%s</td>", data);
    }

    @Override
    public String column(final ArrayList<String> data) {
        StringBuilder sb = new StringBuilder();
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

        return column(sb.toString());
    }

    @Override
    public String closeRow() {
        return "</tr>";
    }

    @Override
    public String closeBody() {
        return "</tbody>";
    }

    @Override
    public String closeTable() {
        return "</table>";
    }
}
