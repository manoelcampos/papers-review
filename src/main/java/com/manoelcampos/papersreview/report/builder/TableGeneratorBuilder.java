package com.manoelcampos.papersreview.report.builder;

import com.manoelcampos.papersreview.report.TableGenerator;

/**
 * Uma classe para gerar dados a serem utilizados por um {@link TableGenerator}
 * @author <a href="https://about.me/manoelcampos">Manoel Campos da Silva Filho</a>
 */
public interface TableGeneratorBuilder {
    String generate();
    void addColumnHeaders();
    void addDataRows();
    TableGeneratorBuilder setGenerator(TableGenerator generator);
}
