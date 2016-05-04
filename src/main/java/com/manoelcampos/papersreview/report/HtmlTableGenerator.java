package com.manoelcampos.papersreview.report;

import org.apache.commons.lang3.StringEscapeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author <a href="https://about.me/manoelcampos">Manoel Campos da Silva Filho</a>
 */
public final class HtmlTableGenerator extends AbstractTableGenerator {

    protected void openTable() {
        appendLine(String.format("<table id='%s' class='table-striped table-bordered'>", getTableId()));
    }

    protected void closeTable() {
        appendLine("</table>");
    }

    protected void insertCaption(){
        appendLine(String.format("\t<caption>%s</caption>", getCaption()));
    }

    protected void insertColumnHeaders(String groupHeaders){
        appendLine(String.format(
                "\t<thead>%s<tr>%s</tr></thread>",
                groupHeaders, getListAsString(getColumnHeaders(), "")));
    }

    @Override
    protected String generateGroupHeaders() {
        List<String> groups = new ArrayList<>();
        int i = 0;
        for(String g: getColumnGroups().keySet())
            groups.add(getFormattedGroup(g, i++));
        return String.format("<tr>%s</tr>%s", getListAsString(groups, ""), NEWLINE);
    }

    @Override
    protected String getFormattedColumnHeader(String title, boolean rotate) {
        String style=(rotate ? " style='transform:rotate(270deg); white-space: wrap;  height: 250px'" : "");
        return String.format("<th%s>%s</th>", style, escape(title));
    }

    @Override
    public TableRow newRow() {
        return new HtmlTableRow(this);
    }

    @Override
    public TableGenerator addRow(TableRow row) {
        this.getRows().add(row);
        return this;
    }

    protected void insertRows() {
        appendLine("\t<tbody>");
        getRows().forEach(row -> appendLine(String.format("%s", row)));
        appendLine("\t</tbody>");
    }

    @Override
    protected String getFormattedGroup(String group, int index){
        Integer groupSize = getColumnGroups().get(group);
        if(groupSize > 1)
            return String.format("<th colspan='%d' style='text-align: center'>%s</th>", groupSize, group);

        return String.format("<th>%s</th>", group);
    }

    @Override
    final public String escape(String data){
        return StringEscapeUtils.escapeHtml4(data);
    }

    @Override
    public String getTick() {
        return "x";
    }

}
