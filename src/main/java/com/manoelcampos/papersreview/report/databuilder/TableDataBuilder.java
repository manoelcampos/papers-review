package com.manoelcampos.papersreview.report.databuilder;

import com.manoelcampos.papersreview.model.Field;
import com.manoelcampos.papersreview.report.ReportTableGenerator;

/**
 * Uma classe para gerar dados a serem utilizados por um {@link com.manoelcampos.papersreview.report.ReportTableGenerator}
 * @author <a href="https://about.me/manoelcampos">Manoel Campos da Silva Filho</a>
 */
public interface TableDataBuilder {
    String generate();
    void addColumnHeaders();
    void addDataRows();
    TableDataBuilder setTableId(String tableId);
    String getTableId();
    TableDataBuilder setGenerator(ReportTableGenerator generator);
}
