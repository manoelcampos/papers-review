package com.manoelcampos.papersreview.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.interceptor.IncludeParameters;
import br.com.caelum.vraptor.jpa.extra.Load;
import br.com.caelum.vraptor.validator.Validator;
import com.manoelcampos.papersreview.dto.PaperFieldAnswerDTO;
import com.manoelcampos.papersreview.model.*;
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

    @Get("/paper/{paper.id}")
    public Paper view(@NotNull @Load final Paper paper) {
        return paper;
    }

    @Get()
    public void form() {
        includeLists();
    }

    @Get("/searchSession/{searchSession.id}/paper/")
    public void form(@NotNull @Load final SearchSession searchSession) {
        final Paper paper = new Paper();
        paper.setSearchSession(searchSession);
        result.include("paper", paper);
        result.include("searchSessions", service.listSearchSessions(searchSession.getProject()));
        includeLists();
    }

    @Get("/paper/edit/{paper.id}")
    @IncludeParameters
    public void edit(@NotNull @Load final Paper paper) {
        result.include("searchSessions", service.listSearchSessions(paper.getSearchSession().getProject()));
        result.redirectTo(this).form();
    }

    private void includeLists() {
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
    
    
    @Get("/project/{project.id}/paper/search")
    public void search(final Project project) {
        includeLists();
        result.include("projects", service.listProjects());
        result.include("repositories", service.listRepositories());
        if(!EntityInterface.isNull(project)) {
            result.include("project", project);
        }
    }

    @Get("/paper/search")
    public void search() {
        search(null);
    }

    @Post()
    public void doSearch(final Paper paper) {
        result.include("list", service.search(paper));
        result.include("paper", paper);
        Project project = paper.getSearchSession().getProject();
        result.redirectTo(this).search(project);
    }

}