package com.manoelcampos.papersreview.report.databuilder;

import com.manoelcampos.papersreview.model.Field;
import com.manoelcampos.papersreview.model.Paper;
import com.manoelcampos.papersreview.report.ReportTableGenerator;
import com.manoelcampos.papersreview.report.TableRow;
import com.manoelcampos.papersreview.service.PapersSummaryTableService;

import java.util.List;

public class PapersSummaryRegularTableDataBuilder extends AbstractTableDataBuilder {
    private boolean useAbreviations;
    private final List<Field> fields;
    private PapersSummaryTableService service;

    public PapersSummaryRegularTableDataBuilder(PapersSummaryTableService service) {
        super();
        this.service = service;
        this.fields = service.getFieldDao().getFieldsToBeShownInReports(service.getProject());
    }

    @Override
    public void addColumnHeaders() {
        getGenerator()
                .setCaption("Comparison of Proposals for Virtual Machine Placement and Migration")
                .setTableId(tableId)
                //.addColumnHeader("#").addColumnHeader("Paper")
                .addColumnHeader("Paper");
                 //.addColumnHeader("Type");

        for (Field f : fields) {
            getGenerator().addColumnHeader(f.getDescription(useAbreviations));
        }
    }

    @Override
    public void addDataRows() {
        //Integer i = 0;
        for (Paper p : service.getPaperDao().listApprovedPapersInFinalPhaseWithDefinedTypeByProject(service.getProject())) {
            TableRow row = getGenerator().newRow();
            row
                //.addColumn((++i).toString()).addColumn(p.getTitle())
                .addColumnUnescaped(String.format("\\cite{%s}", p.getCitationKey()));
                //.addColumn(p.getPaperType().getAbbreviation());

            for (Field f : fields) {
                row.addColumn(p.getPaperFieldAnswers(f, useAbreviations));
            }

            getGenerator().addRow(row);
        }
    }


    public boolean isUseAbreviations() {
        return useAbreviations;
    }

    public PapersSummaryRegularTableDataBuilder setUseAbreviations(boolean useAbreviations) {
        this.useAbreviations = useAbreviations;
        return this;
    }
}