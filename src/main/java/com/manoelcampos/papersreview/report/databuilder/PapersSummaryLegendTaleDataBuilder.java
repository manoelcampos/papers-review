package com.manoelcampos.papersreview.report.databuilder;

import com.manoelcampos.papersreview.model.Field;
import com.manoelcampos.papersreview.report.TableRow;
import com.manoelcampos.papersreview.service.PapersSummaryTableService;

public class PapersSummaryLegendTaleDataBuilder extends AbstractTableDataBuilder {
    private PapersSummaryTableService service;
    private Field field = Field.NULL;

    public PapersSummaryLegendTaleDataBuilder(PapersSummaryTableService service) {
        super();
        this.service = service;
    }

    @Override
    public void addColumnHeaders() {
        getGenerator()
            .setCaption("Papers Summary Table Legend")
            .addColumnHeader("Column")
            .addColumnHeader("Column Description")
            .addColumnHeader("Values for the Colum");

    }

    public String generate(final Field field) {
        setField(field);
        return super.generate();
    }

    private void setField(Field field) {
        if(field == null || field.getId() == null || field.getId().equals(0L))
            this.field = Field.NULL;
        else this.field = field;
    }

    @Override
    public void addDataRows() {
        addPaperTypesRow();

        for (Field f : service.getFieldDao().getNonSubjectiveFieldsToBeShownInReports(service.getProject(), field)) {
            TableRow row = getGenerator().newRow();
            row
                .addColumn(f.getAbbreviation())
                .addColumn(f.getDescription())
                .addColumn(f.getFieldOptionsThatHaveAbbreviation());

            getGenerator().addRow(row);
        }
    }

    private void addPaperTypesRow() {
        TableRow row = getGenerator().newRow();
        row
            .addColumn("T")
            .addColumn("Paper Type")
            .addColumn(service.getPaperTypeDao().listAsString());
        getGenerator().addRow(row);
    }

}