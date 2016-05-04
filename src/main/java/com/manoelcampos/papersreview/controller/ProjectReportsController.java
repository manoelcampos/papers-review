package com.manoelcampos.papersreview.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.interceptor.IncludeParameters;
import br.com.caelum.vraptor.jpa.extra.Load;
import br.com.caelum.vraptor.validator.Validator;
import com.manoelcampos.papersreview.model.Field;
import com.manoelcampos.papersreview.model.FieldGroup;
import com.manoelcampos.papersreview.model.Project;
import com.manoelcampos.papersreview.report.*;
import com.manoelcampos.papersreview.report.builder.*;
import com.manoelcampos.papersreview.service.*;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author Manoel Campos da Silva Filho <manoelcampos at gmail.com>
 */
@Controller()
public class ProjectReportsController extends BaseController  {
    public static final String legendTableId = "tab:papers-summary-legend";
    public static final String paperCountByFieldOptionTableId = "tab:paper-count-by-field-option";

    @Inject
    private PapersSummaryTableService service;

    @Inject
    private Validator validator;
    
    @Inject
    private Locale locale;

    @Inject
    private ResourceBundle bundle;

    public ProjectReportsController(){
    }
        
    @Get("/project/reports/{project.id}")
    @IncludeParameters
    public void index(@NotNull @Load final Project project) {

    }

    private void createPaperSummaryTableGenerator(Project p, TableGenerator gen, FieldGroup fieldGroup) {
        service.setProject(p);
        PapersSummaryMultColumnTableGeneratorBuilder builder1 =
                new PapersSummaryMultColumnTableGeneratorBuilder(service, fieldGroup);
        gen.setCaption(fieldGroup.getNotes()).setTableId(fieldGroup.getTableId());
        builder1.setGenerator(gen);

        //PapersSummaryLegendTaleGeneratorBuilder builder2 = new PapersSummaryLegendTaleGeneratorBuilder(service);
        //builder2.setGenerator(gen).setTableId(summaryTableId);

        result.include("summaryTable", builder1.generate());
        //result.include("legendTable",  builder2.generate());
    }

    @Get("/project/reports/papers-summary-table/{project.id}/{dataFormat}")
    @IncludeParameters
    public void papersSummaryTable(@NotNull String dataFormat, @NotNull @Load Project project) {
        papersSummaryTable(dataFormat, project, FieldGroup.NULL);
    }

    @Get("/project/reports/papers-summary-table/{project.id}/{fieldGroup.id}/{dataFormat}")
    @IncludeParameters
    public void papersSummaryTable(@NotNull String dataFormat, @NotNull @Load Project project, @NotNull @Load FieldGroup fieldGroup) {
        result.include("fieldGroups", service.listFieldGroups(project));
        switch (dataFormat){
            case "latex":
                createPaperSummaryTableGenerator(project, new LatexTableGenerator(), fieldGroup);
                break;
            default:
                createPaperSummaryTableGenerator(project, new HtmlTableGenerator(), fieldGroup);
                break;
        }
    }

    @Post()
    public void filterSummary(@NotNull String dataFormat, @NotNull @Load Project project, @NotNull @Load FieldGroup fieldGroup){
        result.redirectTo(ProjectReportsController.class).papersSummaryTable(dataFormat, project, fieldGroup);
    }

    @Get("/project/reports/paper-count-by-status/{project.id}")
    @Post()
    @IncludeParameters
    public void  paperCountByStatus(@NotNull @Load final Project project) {
        result.include("list", service.getPaperCountByStatus(project));
    }

    @Get("/project/reports/paper-count-by-type/{project.id}")
    @Post()
    @IncludeParameters
    public void  paperCountByType(@NotNull @Load final Project project) {
        result.include("list", service.getPaperCountByType(project));
    }

    @Get("/project/reports/paper-count-by-repository/{project.id}")
    @Post()
    @IncludeParameters
    public void  paperCountByRepository(@NotNull @Load final Project project) {
        result.include("list", service.getPaperCountByRepository(project));
    }


    @Get("/project/reports/paper-count-by-user-fields/{dataFormat}/{project.id}/{field.id}")
    @IncludeParameters
    public void paperCountByFieldOption(@NotNull String dataFormat, @NotNull @Load Project project, @Load Field field) {
        includeFieldsToBeShownInReports(project);
        service.setProject(project);
        switch(dataFormat){
            case "html":
                createPaperCountByFieldOptionDataBuilder(
                        new PaperCountByFieldOptionRegularTableGeneratorBuilder(),
                        field, new HtmlTableGenerator());
            break;
            case "latex":
                createPaperCountByFieldOptionDataBuilder(
                        new PaperCountByFieldOptionRegularTableGeneratorBuilder(),
                        field, new LatexTableGenerator());
            break;
            case "csv":
                createPaperCountByFieldOptionDataBuilder(
                        new PaperCountByFieldOptionPivotTableGeneratorBuilder(),
                        field, new CsvTableGenerator());
            break;
        }

    }

    private void createPaperCountByFieldOptionDataBuilder(
            PaperCountByFieldOptionTableGeneratorBuilder builder, Field field, TableGenerator gen) {
        gen.setTableId(paperCountByFieldOptionTableId);
        builder.setService(service).setGenerator(gen);
        result.include("table", builder.generate(field));
    }

    @Get("/project/reports/paper-count-by-user-fields/{dataFormat}/{project.id}")
    @IncludeParameters
    public void paperCountByFieldOption(@NotNull String dataFormat, @NotNull @Load final Project project) {
        result.include("field", Field.NULL);
        paperCountByFieldOption(dataFormat, project, Field.NULL);
    }

    @Post()
    public void reload(@NotNull String dataFormat, @NotNull @Load  Project project, @NotNull @Load Field field){
        result.redirectTo(ProjectReportsController.class).paperCountByFieldOption(dataFormat,project, field);
    }

    private void includeFieldsToBeShownInReports(@NotNull @Load Project project) {
        result.include("fields", service.getFieldsToBeShownInReports(project));
    }

}