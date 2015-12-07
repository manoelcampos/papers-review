package com.manoelcampos.papersreview.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.interceptor.IncludeParameters;
import br.com.caelum.vraptor.jpa.extra.Load;
import br.com.caelum.vraptor.validator.Validator;
import com.manoelcampos.papersreview.dto.PaperFieldAnswerDTO;
import com.manoelcampos.papersreview.model.Paper;
import com.manoelcampos.papersreview.model.PaperFieldAnswer;
import com.manoelcampos.papersreview.model.Project;
import com.manoelcampos.papersreview.service.PaperService;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Manoel Campos da Silva Filho <manoelcampos at gmail.com>
 */
@Controller
@Stateless
@Dependent
public class PaperController extends BaseController {
    @Inject
    private PaperService service;
       
    @Inject
    private Validator validator;
    
    @Inject
    private Locale locale;

    @Inject
    private ResourceBundle bundle;  

    @Get("/paper/{paper.id}")
    public Paper view(@NotNull @Load final Paper paper) {
        return paper;
    }
    
    @Get("/paper/remove/{paper.id}")
    public void remove(@NotNull @Load final Paper paper) {
        service.remove(paper);
        result.include("msg", "form.removed");
        result.redirectTo(ProjectController.class).view(paper.getSearchSession().getProject());
    }

    @Get("/paper/removeAnswer/{paperFieldAnswer.id}")
    public void removeAnswer(@NotNull @Load PaperFieldAnswer paperFieldAnswer) {
        service.removeAnswer(paperFieldAnswer);
        
        result.include("msg", "form.removed");
        result.redirectTo(this).view(paperFieldAnswer.getPaper());
    }

    @Get("/paper/edit/{paper.id}")
    @IncludeParameters
    public void edit(@NotNull @Load final Paper paper) {
        result.redirectTo(this).form();
    }

    @Get("/paper/answers/{paper.id}")
    public Paper answers(@NotNull @Load final Paper paper) {
        result.include("answersMap", service.listAnswersForAllFields(paper));
        return paper;
    }
    
    @Post()
    public void saveAnswers(@NotNull @Load final Paper paper, final List<PaperFieldAnswerDTO> answers) {
        service.saveAnswers(paper, answers);
        //result.include("msg", answers.toString());
        result.redirectTo(this).view(paper);
    }     

    @Get()
    public void form() {
        includePaperTypeAndStatusLists();
    }

    private void includePaperTypeAndStatusLists() {
        result.include("paperTypes", service.listPaperTypes());
        result.include("status", service.listStatus());
    }

    @Post()
    public void save(@Valid Paper paper) {
        validator.onErrorRedirectTo(this).form();
        service.save(paper);
        result.include("msg", "form.saved");
        result.redirectTo(PaperController.class).view(paper);
    }    
    
    
    @Post()
    public void doSearch(final Paper paper) {
        result.include("list", service.search(paper));
        result.include("paper", paper);
        result.redirectTo(this).search(paper.getSearchSession().getProject());
    }    
    
    @Get("/project/{project.id}/paper/search")
    public void search(@Load Project project) {
        includePaperTypeAndStatusLists();
        result.include("projects", service.listProjects());
        result.include("repositories", service.listRepositories());
        if(project != null && project.getId() > 0)
            result.include("project", project);
    }    

    @Get("/paper/search")
    public void search() {
        search(null);
    }    
    
}