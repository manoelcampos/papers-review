package com.manoelcampos.papersreview.report;

import java.util.ArrayList;
import java.util.List;

/**
 * @author manoelcampos
 */
public interface ReportTableGenerator {
    String openTable();
    String caption(final String title);
    String openHead();
    String columnHeader(final String title);
    String closeHead();
    String openBody();
    String openRow();
    String column(final String data);
    String column(final ArrayList<String> data);
    String closeRow();
    String closeBody();
    String closeTable();
}
