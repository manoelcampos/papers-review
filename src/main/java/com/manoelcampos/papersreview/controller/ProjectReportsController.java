package com.manoelcampos.papersreview.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.interceptor.IncludeParameters;
import br.com.caelum.vraptor.jpa.extra.Load;
import br.com.caelum.vraptor.validator.Validator;
import com.manoelcampos.papersreview.dao.PaperSummaryTableDAO;
import com.manoelcampos.papersreview.dao.ProjectDAO;
import com.manoelcampos.papersreview.model.Project;
import com.manoelcampos.papersreview.report.HtmlReportTableGenerator;
import com.manoelcampos.papersreview.report.ReportTableGenerator;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author Manoel Campos da Silva Filho <manoelcampos at gmail.com>
 */
@Controller
public class ProjectReportsController extends BaseController  {
    @Inject
    private ProjectDAO dao;

    private ReportTableGenerator generator;

    @Inject
    private PaperSummaryTableDAO summary;
    
    @Inject
    private Validator validator;
    
    @Inject
    private Locale locale;

    @Inject
    private ResourceBundle bundle;

    public ProjectReportsController(){
        this.generator = new HtmlReportTableGenerator();
    }
        
    @Get("/project/reports/{project.id}")
    @IncludeParameters
    public void index(@NotNull @Load final Project project) {

    }

    @Get("/project/reports/papers-summary-table/{project.id}")
    @IncludeParameters
    public void papersSummaryTable(@NotNull @Load final Project project) {
        summary.setProject(project);
        result.include("paperList", summary.getPapersSummaryTable());
        result.include("fieldList", summary.getFieldList());
        result.include("gen", this.generator);
    }

    @Get("/project/reports/paper-count-by-status/{project.id}")
    @IncludeParameters
    public void  paperCountByStatus(@NotNull @Load final Project project) {
        result.include("list", dao.getPaperCountByStatus(project));
    }

    @Get("/project/reports/paper-count-by-type/{project.id}")
    @IncludeParameters
    public void  paperCountByType(@NotNull @Load final Project project) {
        result.include("list", dao.getPaperCountByType(project));
    }

    @Get("/project/reports/paper-count-by-repository/{project.id}")
    @IncludeParameters
    public void  paperCountByRepository(@NotNull @Load final Project project) {
        result.include("list", dao.getPaperCountByRepository(project));
    }
}