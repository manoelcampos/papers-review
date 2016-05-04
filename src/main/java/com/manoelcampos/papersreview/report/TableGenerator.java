package com.manoelcampos.papersreview.report;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author manoelcampos
 */
public interface TableGenerator {
    static final String NEWLINE = System.getProperty("line.separator");

    TableGenerator setCaption(final String caption);
    String getCaption();
    TableGenerator setTableId(final String id);
    String getTableId();
    TableGenerator addColumnHeader(final String title);
    TableGenerator addColumnHeader(final String title, boolean rotate);

    /**
     * Add a header and a group with just one element (groupSize = 1)
     * @param group
     * @return
     */
    TableGenerator addColumnHeader(final String title, final String group);

    TableGenerator addColumnGroup(String group, Integer groupSize);

    Map<String, Integer> getColumnGroups();

    TableRow newRow();
    TableGenerator addRow(TableRow row);
    String build();
    String buildAndClear();
    List<TableRow> getRows();
    void clear();
    String escape(String data);

    /**
     *
     * @return A String to represent a tick (such as an X) in the
     * format of the current generator. For instance,
     * a HTML generator may return an image for
     * a tick (such as the image of a ticked checkbox)
     * while a Latex generator may return
     * a \bullet command.
     */
    String getTick();
}
