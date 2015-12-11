package com.manoelcampos.papersreview.service;

import com.manoelcampos.papersreview.dao.*;
import com.manoelcampos.papersreview.dto.PaperCountByFieldOptionDTO;
import com.manoelcampos.papersreview.dto.PaperCountByRepositoryDTO;
import com.manoelcampos.papersreview.dto.PaperCountByStatusDTO;
import com.manoelcampos.papersreview.dto.PaperCountByTypeDTO;
import com.manoelcampos.papersreview.model.*;
import com.manoelcampos.papersreview.report.ReportTableGenerator;
import com.manoelcampos.papersreview.report.TableRow;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Dependent
public class PapersSummaryTableService {
    @Inject
    private ProjectDAO projectDao;

    @Inject
    private FieldDAO fieldDao;

    @Inject
    private PaperDAO paperDao;

    @Inject
    private PaperFieldAnswerDAO paperFieldAnswerDao;

    @Inject
    private PaperTypeDAO paperTypeDao;

    private Project project;

    private ReportTableGenerator generator;

    public PapersSummaryTableService(){
    }

    public PapersSummaryTableService setGenerator(final ReportTableGenerator generator){
        this.generator = generator;
        return this;
    }

    public PapersSummaryTableService setProject(final Project project){
        this.project = project;
        return this;
    }

    public String papersSummaryTable(final String tableId) {
        generator
            .setCaption("Papers Summary Table")
            .setTableId(tableId)
            .addColumnHeader("#")
            .addColumnHeader("Paper")
            .addColumnHeader("Key")
            .addColumnHeader("Type");

        for (Field f : fieldDao.getFieldsToBeShownInReports(project))
            generator.addColumnHeader(f.getAbbreviation());


        Integer i = 0;
        for (Paper p : paperDao.listPapersWithDefinedTypeByProject(project)) {
            TableRow row = generator.newRow();
            row
                .addColumn((++i).toString())
                .addColumn(p.getTitle())
                .addColumn(String.format("\\cite{%s}", p.getCitationKey()))
                .addColumn(p.getPaperType().getAbbreviation());

            for(Field f: fieldDao.getFieldsToBeShownInReports(project))
                row.addColumn(p.getPaperFieldAbbreviatedAnswers(f));

            generator.addRow(row);
        }

       return generator.buildAndClear();
    }


    public String papersSummaryLegendTable(final String tableId) {
        generator
                .setCaption("Papers Summary Table Legend")
                .setTableId(tableId)
                .addColumnHeader("Column")
                .addColumnHeader("Column Description")
                .addColumnHeader("Column's Values Descriptions");

        addPaperTypesRow();

        for (Field f : fieldDao.getNonSubjectiveFieldsToBeShownInReports(project)) {
            TableRow row = generator.newRow();
            row
               .addColumn(f.getAbbreviation())
               .addColumn(f.getDescription())
               .addColumn(f.getFieldOptionsThatHaveAbbreviation());

            generator.addRow(row);
        }

        return generator.buildAndClear();
    }

    private void addPaperTypesRow() {
        TableRow row = generator.newRow();
        row
                .addColumn("T")
                .addColumn("Paper Type")
                .addColumn(paperTypeDao.listAsString());
        generator.addRow(row);
    }

    public List<PaperCountByStatusDTO> getPaperCountByStatus(final Project project) {
        return projectDao.getPaperCountByStatus(project);
    }

    public List<PaperCountByRepositoryDTO> getPaperCountByRepository(final Project project){
        return projectDao.getPaperCountByRepository(project);
    }

    public List<PaperCountByTypeDTO> getPaperCountByType(final Project p){
        return projectDao.getPaperCountByType(p);
    }

    private Map<Field, List<PaperCountByFieldOptionDTO>> getPaperCountByFieldOption(){
        Map<Field, List<PaperCountByFieldOptionDTO>> map = new HashMap<>();

        fieldDao.getNonSubjectiveFieldsToBeShownInReports(project)
                .forEach(f -> map.put(f, paperFieldAnswerDao.listAnswersCountForFieldOptionByField(f)));

        return map;
    }

    public String getPaperCountByFieldOptionTable(final String tableId){
        generator
                .setCaption("Papers Count by defined Categories")
                .setTableId(tableId)
                .addColumnHeader("Category")
                .addColumnHeader("Value")
                .addColumnHeader("Number of classified Papers");

        final Map<Field, List<PaperCountByFieldOptionDTO>> map = getPaperCountByFieldOption();
        for(Field f: map.keySet()) {
            List<PaperCountByFieldOptionDTO> answers = map.get(f);
            addRowsToPaperCountByFieldOptionTable(f, answers);
        }

        return generator.buildAndClear();
    }

    private void addRowsToPaperCountByFieldOptionTable(final Field f, final List<PaperCountByFieldOptionDTO> answers) {
        for(PaperCountByFieldOptionDTO answer: answers) {
            addSingleRowToPaperCountByFieldOptionTable(
                    f.getDescription(),
                    answer.getFieldOption().getDescription(),
                    answer.getCount());
        }
    }

    private void addSingleRowToPaperCountByFieldOptionTable(final String field, final String fieldOption, final Long count) {
        TableRow row = generator.newRow();
        row
                .addColumn(field)
                .addColumn(fieldOption)
                .addColumn(count.toString());
        generator.addRow(row);

    }


}