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
import com.manoelcampos.papersreview.service.PapersSummaryTableService;

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
        service.setGenerator(new HtmlReportTableGenerator()).setProject(project);
        result.include("summaryTable", service.approvedPapersInFinalPhaseSummaryTable(summaryTableId));
        result.include("legendTable", service.papersSummaryLegendTable(legendTableId));
        result.of(ProjectReportsController.class).papersSummaryTable(project);
    }

    @Get("/project/reports/papers-summary-table/{project.id}/latex")
    @IncludeParameters
    public void papersSummaryTableLatex(@NotNull @Load final Project project) {
        service.setGenerator(new LatexReportTableGenerator()).setProject(project);
        result.include("summaryTable", service.approvedPapersInFinalPhaseSummaryTable(summaryTableId));
        result.include("legendTable", service.papersSummaryLegendTable(legendTableId));
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


    @Get({
            "/project/reports/paper-count-by-user-fields/{project.id}/{field.id}",
            "/project/reports/paper-count-by-user-fields/{project.id}/{field.id}/html"
    })
    @Post()
    @IncludeParameters
    public void paperCountByFieldOption(@NotNull @Load final Project project, @Load final Field field) {
        service.setGenerator(new HtmlReportTableGenerator()).setProject(project);
        includeFieldList(project);
        result.include("table",
                service.generateApprovedPaperCountByFieldOptionRegularTable(paperCountByFieldOptionTableId, field));
    }

    private void includeFieldList(@NotNull @Load Project project) {
        result.include("fields", service.getFields(project));
    }

    @Get({
            "/project/reports/paper-count-by-user-fields/{project.id}",
            "/project/reports/paper-count-by-user-fields/{project.id}/html"
    })
    @Post()
    @IncludeParameters
    public void paperCountByFieldOption(@NotNull @Load final Project project) {
        result.include("field", Field.NULL);
        paperCountByFieldOption(project, Field.NULL);
        result.of(ProjectReportsController.class).paperCountByFieldOption(project, Field.NULL);
    }

    @Get("/project/reports/paper-count-by-user-fields/{project.id}/{field.id}/csv")
    @Post()
    @IncludeParameters
    public void paperCountByFieldOptionCsv(@NotNull @Load final Project project, @Load final Field field) {
        service.setGenerator(new CsvReportTableGenerator()).setProject(project);
        includeFieldList(project);
        result.include("table",
                service.generateApprovedPaperCountByFieldOptionPivotTable(paperCountByFieldOptionTableId, field));
        result.of(ProjectReportsController.class).paperCountByFieldOption(project, field);
    }

    @Get("/project/reports/paper-count-by-user-fields/{project.id}/csv")
    @Post()
    @IncludeParameters
    public void paperCountByFieldOptionCsv(@NotNull @Load final Project project) {
        result.include("field", Field.NULL);
        paperCountByFieldOptionCsv(project, Field.NULL);
    }

    @Get("/project/reports/paper-count-by-user-fields/{project.id}/{field.id}/latex")
    @Post()
    @IncludeParameters
    public void paperCountByFieldOptionLatex(@NotNull @Load final Project project, @Load final Field field) {
        service.setGenerator(new LatexReportTableGenerator()).setProject(project);
        includeFieldList(project);
        result.include("table",
                service.generateApprovedPaperCountByFieldOptionRegularTable(paperCountByFieldOptionTableId, field));
        result.of(ProjectReportsController.class).paperCountByFieldOption(project, field);
    }

    @Get("/project/reports/paper-count-by-user-fields/{project.id}/latex")
    @Post()
    @IncludeParameters
    public void paperCountByFieldOptionLatex(@NotNull @Load final Project project) {
        result.include("field", Field.NULL);
        paperCountByFieldOptionLatex(project, Field.NULL);
    }
}