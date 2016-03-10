package com.manoelcampos.papersreview.report.databuilder;

import com.manoelcampos.papersreview.dto.PaperCountByFieldOptionDTO;
import com.manoelcampos.papersreview.model.Field;
import com.manoelcampos.papersreview.model.FieldOption;
import com.manoelcampos.papersreview.report.ReportTableGenerator;
import com.manoelcampos.papersreview.report.TableRow;

import java.util.List;

public class PaperCountByFieldOptionPivotTableDataBuilder extends PaperCountByFieldOptionTableDataBuilder {
    public PaperCountByFieldOptionPivotTableDataBuilder(){
        super();
    }

    public PaperCountByFieldOptionPivotTableDataBuilder(String tableId){
        super();
        setTableId(tableId);
    }
    /**
     * Each field option of every existin field will be defined as a table column.
     * If there is 3 fields with 2 columns each, they will be created 6 columns.
     *
     */
    @Override
    public void addColumnHeaders() {
        getGenerator()
                .setCaption("Papers Count by User-defined Fields")
                .setTableId(tableId)
                .addColumnHeader("Field");

        getAnswers().keySet().stream().forEach(f -> addColumnHeader(f));

        //total com a soma das colunas em cada linha
        getGenerator().addColumnHeader("Total");
    }

    private void addColumnHeader(Field f) {
        f.getFieldOptions().stream().forEach(
                fo -> getGenerator().addColumnHeader(fo.getDescription()));
    }

    /**
     * Adds a single row to the pivot table.
     * Each row has in fact the columns of all existing {@link Field fields}.
     *
     * @param currentField The current field to add a line to the pivot table
     * @param fieldAnswers the answers for that field
     */
    @Override
    protected void addDataRows(Field currentField, List<PaperCountByFieldOptionDTO> fieldAnswers) {
        TableRow row = getGenerator().newRow();
        row.addColumn(currentField.getDescription());
        Long totalRespostasField = 0L;
        for (Field f : getAnswers().keySet()) {
            for (FieldOption fo : f.getFieldOptions()) {
                if (f.equals(currentField)) {
                    totalRespostasField += getService().findAnswerForFieldOption(fieldAnswers, row, fo);
                } else row.addColumn("");
            }
        }

        //soma dos valores da coluna da linha atual
        row.addColumn(totalRespostasField.toString());

        getGenerator().addRow(row);
    }

}