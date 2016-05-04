package com.manoelcampos.papersreview.report.builder;

import com.manoelcampos.papersreview.report.TableGenerator;

/**
 * @author <a href="https://about.me/manoelcampos">Manoel Campos da Silva Filho</a>
 */
public abstract class AbstractTableGeneratorBuilder implements TableGeneratorBuilder {
    private TableGenerator generator;

    @Override
    public String generate() {
        addColumnHeaders();
        addDataRows();
        return generator.buildAndClear();
    }

    public TableGenerator getGenerator() {
        return generator;
    }

    @Override
    public TableGeneratorBuilder setGenerator(TableGenerator generator) {
        this.generator = generator;
        return this;
    }
}
