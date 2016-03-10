package com.manoelcampos.papersreview.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.interceptor.IncludeParameters;
import br.com.caelum.vraptor.jpa.extra.Load;
import br.com.caelum.vraptor.validator.Validator;
import com.manoelcampos.papersreview.model.Field;
import com.manoelcampos.papersreview.model.Project;
import com.manoelcampos.papersreview.report.CsvReportTableGenerator;
import com.manoelcampos.papersreview.report.HtmlReportTableGenerator;
import com.manoelcampos.papersreview.report.LatexReportTableGenerator;
import com.manoelcampos.papersreview.report.ReportTableGenerator;
import com.manoelcampos.papersreview.report.databuilder.*;
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
    public static final String summaryTableId = "tab:papers-summary";
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

    @Get("/project/reports/papers-summary-table/{project.id}/html")
    @IncludeParameters
    public void papersSummaryTableHtml(@NotNull @Load final Project project) {
        createPaperSummaryTableGenerator(project, new HtmlReportTableGenerator());
        result.of(ProjectReportsController.class).papersSummaryTable(project);
    }

    private void createPaperSummaryTableGenerator(Project p, ReportTableGenerator gen) {
        service.setProject(p);
        PapersSummaryRegularTableDataBuilder builder1 = new PapersSummaryRegularTableDataBuilder(service);
        builder1.setUseAbreviations(true).setGenerator(gen).setTableId(summaryTableId);

        PapersSummaryLegendTaleDataBuilder builder2 = new PapersSummaryLegendTaleDataBuilder(service);
        builder2.setGenerator(gen).setTableId(summaryTableId);

        result.include("summaryTable", builder1.generate());
        result.include("legendTable", builder2.generate());
    }

    @Get("/project/reports/papers-summary-table/{project.id}/latex")
    @IncludeParameters
    public void papersSummaryTableLatex(@NotNull @Load final Project project) {
        createPaperSummaryTableGenerator(project, new LatexReportTableGenerator());
        result.of(ProjectReportsController.class).papersSummaryTable(project);
    }

    @Get("/project/reports/papers-summary-table/{project.id}")
    @IncludeParameters
    public void papersSummaryTable(@NotNull @Load final Project project) {
        papersSummaryTableHtml(project);
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
                        new PaperCountByFieldOptionRegularTableDataBuilder(),
                        field, new HtmlReportTableGenerator());
            break;
            case "latex":
                createPaperCountByFieldOptionDataBuilder(
                        new PaperCountByFieldOptionRegularTableDataBuilder(),
                        field, new LatexReportTableGenerator());
            break;
            case "csv":
                createPaperCountByFieldOptionDataBuilder(
                        new PaperCountByFieldOptionPivotTableDataBuilder(),
                        field, new CsvReportTableGenerator());
            break;
        }

    }

    private void createPaperCountByFieldOptionDataBuilder(
            PaperCountByFieldOptionTableDataBuilder builder, Field field, ReportTableGenerator gen) {
        builder.setService(service).setTableId(paperCountByFieldOptionTableId).setGenerator(gen);
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