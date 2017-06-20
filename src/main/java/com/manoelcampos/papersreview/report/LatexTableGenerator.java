package com.manoelcampos.papersreview.report;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author <a href="https://about.me/manoelcampos">Manoel Campos da Silva Filho</a>
 */
public final class LatexTableGenerator extends AbstractTableGenerator {
    @Override
    protected AbstractTableGenerator appendLine(String text) {
        return super.appendLine(String.format("%s<br/>", text));
    }

    protected void openTable() {
        //http://tex.stackexchange.com/questions/94078/resize-table-and-caption
        appendLine("\\begin{minipage}[t]{\\textwidth}\\begin{center}\\begin{adjustbox}{width=1.0\\textwidth} %requires packages: adjustbox, caption");
        append("\\begin{tabular}{|");
        getColumnHeaders().forEach(c -> append("l|"));
        appendLine("}");
    }

    protected void closeTable() {
        appendLine("\\end{tabular}");
        appendLine("\\end{adjustbox}");
        insertCaption();
        appendLine("\\end{center}\\end{minipage}");
    }

    protected void insertCaption(){
        appendLine(String.format("\\captionof{table}{%s}", getCaption()));
        appendLine(String.format("\\label{%s}", getTableId()));
    }

    @Override
    protected String getFormattedGroup(String group, int index){
        Integer groupSize = getColumnGroups().get(group);
        if(groupSize > 1){
            String fmt;
            if(index == 0)
                fmt = "c";
            else fmt = (index < getColumnGroups().size()-1 ? "|c" : "|c|");

            return String.format(" \\multicolumn{%d}{%s}{\\textbf{%s}} ", groupSize, fmt, group);
        }

        return group;
    }

    protected void insertColumnHeaders(String groupHeaders){
        appendLine("%Group Headers");
        appendLine(groupHeaders);

        appendLine("%Column Headers");
        appendLine(String.format("\t\\hline %s \\\\", getListAsString(getFormattedColumnHeaders(), " &amp; ")));
        appendLine("\t\\hline");
    }

    protected String generateGroupHeaders() {
        List<String> groups = new ArrayList<>();
        int i = 0;
        for(String g: getColumnGroups().keySet())
            groups.add(getFormattedGroup(g, i++));
        return String.format("\t\\hline %s \\\\%s", getListAsString(groups, " &amp; "), NEWLINE);
    }

    private List<String> getFormattedColumnHeaders(){
        List<String> list = new ArrayList<>();
        for(String header: getColumnHeaders()){
            list.add(String.format("%s", header));
        }
        return list;
    }

    @Override
    protected String getFormattedColumnHeader(String title, boolean rotate) {
        title = String.format("%s", escape(title));
        if(rotate)
            title = String.format("\\begin{turn}{-90} %s \\end{turn}", title);

        return title;
    }

    @Override
    public TableRow newRow() {
        return new LatexTableRow(this);
    }

    protected void insertRows() {
        getRows().forEach(row -> appendLine(row.toString()));
    }

    @Override
    final public String escape(String data){
        data = StringUtils.replace(data, "\\", "\\\\");

        data = StringUtils.replace(data, "$", "\\$");
        data = StringUtils.replace(data, "#", "\\#");
        data = StringUtils.replace(data, "_", "\\_");

        return StringUtils.replace(data, "&", "\\&");
    }

    @Override
    public String getTick() {
        return "$\\bullet$";
    }


}
