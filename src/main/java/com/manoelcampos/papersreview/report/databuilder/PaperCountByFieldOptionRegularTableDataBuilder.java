package com.manoelcampos.papersreview.report.databuilder;

import com.manoelcampos.papersreview.dto.PaperCountByFieldOptionDTO;
import com.manoelcampos.papersreview.model.Field;
import com.manoelcampos.papersreview.report.ReportTableGenerator;
import com.manoelcampos.papersreview.report.TableRow;

import java.util.List;

public class PaperCountByFieldOptionRegularTableDataBuilder extends PaperCountByFieldOptionTableDataBuilder {
    public PaperCountByFieldOptionRegularTableDataBuilder(){
        super();
    }

    public PaperCountByFieldOptionRegularTableDataBuilder(String tableId){
        super();
        setTableId(tableId);
    }

    @Override
    public void addColumnHeaders() {
        getGenerator()
                .setCaption("Papers Count by User-defined Fields")
                .setTableId(getTableId())
                .addColumnHeader("Field")
                .addColumnHeader("Value")
                .addColumnHeader("Number of classified Papers");
    }

    @Override
    protected void addDataRows(Field currentField, final List<PaperCountByFieldOptionDTO> answers) {
        for (PaperCountByFieldOptionDTO answer : answers) {
            addDataRow(
                    currentField,
                    answer.getFieldOption().getDescription(),
                    answer.getCount());
        }
    }

    private void addDataRow(Field currentField, final String fieldOption, final Long count) {
        TableRow row = getGenerator().newRow();
        row.addColumn(currentField.getDescription()).addColumn(fieldOption).addColumn(count.toString());
        getGenerator().addRow(row);
    }
}