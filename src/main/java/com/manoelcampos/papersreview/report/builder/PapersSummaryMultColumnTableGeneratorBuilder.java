package com.manoelcampos.papersreview.report.builder;

import com.manoelcampos.papersreview.model.*;
import com.manoelcampos.papersreview.report.TableGenerator;
import com.manoelcampos.papersreview.report.TableRow;
import com.manoelcampos.papersreview.service.PapersSummaryTableService;

import java.util.List;
import java.util.stream.Collectors;

public class PapersSummaryMultColumnTableGeneratorBuilder extends AbstractTableGeneratorBuilder {
    public static final String summaryTableId = "tab:papers-summary";
    private final List<Field> nonSubjectiveFields;
    private PapersSummaryTableService service;

    public PapersSummaryMultColumnTableGeneratorBuilder(PapersSummaryTableService service, FieldGroup fieldGroup) {
        super();
        this.service = service;
        this.nonSubjectiveFields =
                service.getFieldDao()
                        .getNonSubjectiveFieldsToBeShownInReports(service.getProject(), fieldGroup);
    }

    @Override
    public void addColumnHeaders() {
        getGenerator()
                //.addColumnHeader("#").addColumnHeader("Paper")
                .addColumnHeader("Work", "");
                 //.addColumnHeader("Type");

        for (Field f : nonSubjectiveFields) {
           getGenerator().addColumnGroup(f.getDescription(), f.getFieldOptionsToBeShownInReports().size());
           for(FieldOption fo: f.getFieldOptionsToBeShownInReports()){
               getGenerator().addColumnHeader(fo.getDescription(), true);
           }
        }
    }

    @Override
    public void addDataRows() {
        //Integer i = 0;
        List<Paper> papers = service.getPaperDao().listApprovedNonSurveyPapersInFinalPhaseWithDefinedTypeByProject(service.getProject());
        papers.sort((p1, p2) -> Integer.compare(p1.yearsSincePublication(), p2.yearsSincePublication()));
        for (Paper p : papers) {
            TableRow row = getGenerator().newRow();
            row
                //.addColumn((++i).toString()).addColumn(p.getTitle())
                .addColumnUnescaped(String.format("\\cite{%s}", p.getCitationKey()));
                //.addColumn(p.getPaperType().getAbbreviation());

            for (Field f : nonSubjectiveFields) {
                for(FieldOption fo: f.getFieldOptionsToBeShownInReports()){
                    List<PaperFieldAnswer> fieldAnswers =
                            p.getPaperFieldAnswers().stream().filter(a -> a.getField().equals(f))
                                    .collect(Collectors.toList());
                    if(fieldAnswers.stream().filter(a -> a.getFieldOption().equals(fo)).count()>0){
                        row.addColumnUnescaped(getGenerator().getTick());
                    }
                    else row.addColumn("");
                }
            }
            getGenerator().addRow(row);
        }

    }

    @Override
    public TableGeneratorBuilder setGenerator(TableGenerator generator) {
        if(generator.getTableId().isEmpty())
            generator.setTableId(summaryTableId);

        if(generator.getCaption().isEmpty())
            generator.setCaption("Comparison of Proposals for Virtual Machine Placement and Migration");

        return super.setGenerator(generator);
    }
}