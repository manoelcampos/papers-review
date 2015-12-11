package com.manoelcampos.papersreview.report;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="https://about.me/manoelcampos">Manoel Campos da Silva Filho</a>
 */
public interface TableRow {
    TableRow addColumn(final String data);
    TableRow addColumn(final List<String> data);
    List<String> getColumns();
}
