package com.manoelcampos.papersreview.report;

/**
 * @author <a href="https://about.me/manoelcampos">Manoel Campos da Silva Filho</a>
 */
public final class HtmlTableRow extends AbstractTableRow {
    public HtmlTableRow(TableGenerator generator){
        super(generator);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(getColumns().size()+2);
        sb.append("\t\t<tr>").append(TableGenerator.NEWLINE).append("\t\t\t\t");
        for(String c: getColumns())
            sb.append(String.format("<td>%s</td>", c));
        sb.append(TableGenerator.NEWLINE).append("\t\t</tr>").append(TableGenerator.NEWLINE);

        return sb.toString();
    }
}
