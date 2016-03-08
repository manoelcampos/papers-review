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
import java.util.Optional;

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

    public String approvedPapersInFinalPhaseSummaryTable(final String tableId) {
        generator
            .setCaption("Papers Summary Table")
            .setTableId(tableId)
            //.addColumnHeader("#").addColumnHeader("Paper")
            .addColumnHeader("Paper")
            .addColumnHeader("Type");

        for (Field f : fieldDao.getFieldsToBeShownInReports(project))
            generator.addColumnHeader(f.getAbbreviation());


        Integer i = 0;
        for (Paper p : paperDao.listApprovedPapersInFinalPhaseWithDefinedTypeByProject(project)) {
            TableRow row = generator.newRow();
            row
                //.addColumn((++i).toString()).addColumn(p.getTitle())
                .addColumnUnescaped(String.format("\\cite{%s}", p.getCitationKey()))
                .addColumn(p.getPaperType().getAbbreviation());

            for(Field f: fieldDao.getFieldsToBeShownInReports(project))
                row.addColumn(p.getPaperFieldAbbreviatedAnswers(f));

            generator.addRow(row);
        }

       return generator.buildAndClear();
    }


    public String papersSummaryLegendTable(final String tableId, final Field field) {
        generator
                .setCaption("Papers Summary Table Legend")
                .setTableId(tableId)
                .addColumnHeader("Column")
                .addColumnHeader("Column Description")
                .addColumnHeader("Values for the Colum");

        addPaperTypesRow();

        for (Field f : fieldDao.getNonSubjectiveFieldsToBeShownInReports(project, field)) {
            TableRow row = generator.newRow();
            row
               .addColumn(f.getAbbreviation())
               .addColumn(f.getDescription())
               .addColumn(f.getFieldOptionsThatHaveAbbreviation());

            generator.addRow(row);
        }

        return generator.buildAndClear();
    }

    public String papersSummaryLegendTable(final String tableId) {
        return papersSummaryLegendTable(tableId, Field.NULL);
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

    private Map<Field, List<PaperCountByFieldOptionDTO>> getApprovedPaperCountByFieldOption(final Field field){
        Map<Field, List<PaperCountByFieldOptionDTO>> map = new HashMap<>();

        fieldDao.getNonSubjectiveFieldsToBeShownInReports(project, field)
                .forEach(f -> map.put(f, paperFieldAnswerDao.listAnswersCountOfApprovedPapersGroupedByFieldOption(f)));

        return map;
    }

    public String generateApprovedPaperCountByFieldOptionRegularTable(final String tableId, final Field field){
        addColumnHeadersForRegularTable(tableId);
        addDataRowsForRegularTable(field);
        return generator.buildAndClear();
    }

    private void addDataRowsForRegularTable(final Field field) {
        final Map<Field, List<PaperCountByFieldOptionDTO>> map = getApprovedPaperCountByFieldOption(field);
        map.keySet().stream().forEach(f -> addDataRowsForRegularTable(f, map.get(f)));
    }

    private void addColumnHeadersForRegularTable(String tableId) {
        generator
                .setCaption("Papers Count by defined Categories")
                .setTableId(tableId)
                .addColumnHeader("Category")
                .addColumnHeader("Value")
                .addColumnHeader("Number of classified Papers");
    }

    public String generateApprovedPaperCountByFieldOptionPivotTable(final String tableId, Field field){
            final Map<Field, List<PaperCountByFieldOptionDTO>> map = getApprovedPaperCountByFieldOption(field);
        addColumnHeadersForPivotTable(tableId, map);
        addDataRowsForPivotTable(map);
        return generator.buildAndClear();
    }

    private void addDataRowsForPivotTable(final Map<Field, List<PaperCountByFieldOptionDTO>> map) {
        for(Field f: map.keySet()) {
            List<PaperCountByFieldOptionDTO> fieldAnswers = map.get(f);
            addDataRowForPivotTable(map, f, fieldAnswers);
        }
    }

    /**
     * Adds a single row to the pivot table.
     * Each row has in fact the columns of all existing {@link Field fields}.
     *
     * @param map the map with all answers of all fields.
     * @param currentField The current field to add a line to the pivot table
     * @param fieldAnswers the answers for that field
     */
    private void addDataRowForPivotTable(Map<Field, List<PaperCountByFieldOptionDTO>> map, Field currentField, List<PaperCountByFieldOptionDTO> fieldAnswers) {
        TableRow row = generator.newRow();
        row.addColumn(currentField.getDescription());
        for(Field f: map.keySet()) {
            for(FieldOption fo: f.getFieldOptions()) {
                if(f.equals(currentField)){
                    findAnswerForFieldOption(fieldAnswers, row, fo);
                }
                else row.addColumn("");
            }
        }
        generator.addRow(row);
    }

    private void findAnswerForFieldOption(List<PaperCountByFieldOptionDTO> fieldAnswers, TableRow row, FieldOption fo) {
        Optional<PaperCountByFieldOptionDTO> obj =
                fieldAnswers.stream().filter(a -> a.getFieldOption().equals(fo)).findFirst();
        if(obj.isPresent())
            row.addColumn(obj.get().getCount().toString());
        else  row.addColumn("");
    }

    /**
     * Each field option of every existin field will be defined as a table column.
     * If there is 3 fields with 2 columns each, they will be created 6 columns.
     * @param tableId
     */
    private void addColumnHeadersForPivotTable(String tableId, final Map<Field, List<PaperCountByFieldOptionDTO>> map) {
        generator
                .setCaption("Papers Count by defined Categories")
                .setTableId(tableId)
                .addColumnHeader("Category");

        map.keySet().stream().forEach(f -> addFieldOptionsAsColumnHeaderForPivotTable(f));
    }

    private void addFieldOptionsAsColumnHeaderForPivotTable(Field f) {
        f.getFieldOptions().stream().forEach(fo -> generator.addColumnHeader(fo.getDescription()));
    }


    private void addDataRowsForRegularTable(final Field f, final List<PaperCountByFieldOptionDTO> answers) {
        for(PaperCountByFieldOptionDTO answer: answers) {
            addDataRowForRegularTable(
                    f.getDescription(),
                    answer.getFieldOption().getDescription(),
                    answer.getCount());
        }
    }

    private void addDataRowForRegularTable(final String field, final String fieldOption, final Long count) {
        TableRow row = generator.newRow();
        row.addColumn(field).addColumn(fieldOption).addColumn(count.toString());
        generator.addRow(row);
    }

    public List<Field> getFields(final Project project){
        return fieldDao.listByProject(project);
    }

    public Field getField(final Field field){
        if(field == null || field.getId() == null || field.getId() <= 0)
            return Field.NULL;

        return fieldDao.findById(field.getId());
    }

    public Project getProject(final Project project){
        if(project == null || project.getId() == null || project.getId() <= 0)
            return Project.NULL;

        return projectDao.findById(project.getId());
    }


}