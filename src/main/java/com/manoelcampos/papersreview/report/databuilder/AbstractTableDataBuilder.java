package com.manoelcampos.papersreview.report.databuilder;

import com.manoelcampos.papersreview.model.Field;
import com.manoelcampos.papersreview.report.ReportTableGenerator;

/**
 * @author <a href="https://about.me/manoelcampos">Manoel Campos da Silva Filho</a>
 */
public abstract class AbstractTableDataBuilder implements TableDataBuilder {
    protected String tableId = "";
    private ReportTableGenerator generator;

    @Override
    public String getTableId() {
        return tableId;
    }

    @Override
    final public TableDataBuilder setTableId(String tableId) {
        this.tableId = tableId;
        return this;
    }

    @Override
    public String generate() {
        addColumnHeaders();
        addDataRows();
        return generator.buildAndClear();
    }

    public ReportTableGenerator getGenerator() {
        return generator;
    }

    @Override
    public TableDataBuilder setGenerator(ReportTableGenerator generator) {
        this.generator = generator;
        return this;
    }
}
