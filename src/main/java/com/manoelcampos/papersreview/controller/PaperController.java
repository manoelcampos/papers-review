package com.manoelcampos.papersreview.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;
import com.manoelcampos.papersreview.dto.PaperFieldAnswerDTO;
import com.manoelcampos.papersreview.model.Paper;
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
public class PaperController  {
    @Inject
    private PaperService service;
   
    @Inject 
    private Result result;
    
    @Inject
    private Validator validator;
    
    @Inject
    private Locale locale;

    @Inject
    private ResourceBundle bundle;  

    @Get()
    public void view(@NotNull final Long id) {
        Paper o = service.findById(id);
        result.include("o", o);
    }
    
    @Get()
    public void remove(@NotNull final Long id) {
        Paper o = service.remove(id);
        result.include("msg", "form.removed");
        result.redirectTo(ProjectController.class).view(o.getSearchSection().getProject().getId());
    }

    @Get()
    public void removeAnswer(@NotNull final Long paperFieldAnswerId) {
        Paper paper = service.removeAnswer(paperFieldAnswerId);
        
        result.include("msg", "form.removed");
        result.redirectTo(this).view(paper.getId());
    }

    @Get()
    public void edit(@NotNull final Long id) {
        Paper o = service.findById(id);
        result.include("o", o);
        result.redirectTo(this).form();
    }

    @Get()
    public void answers(@NotNull final Long paperId) {
        Paper paper = service.findById(paperId);
        result.include("paper", paper);
        result.include("answersMap", service.listAnswersForAllFields(paper));
    }
    
    @Post()
    public void saveAnswers(@NotNull final Long paperId, final List<PaperFieldAnswerDTO> answers) {
        service.saveAnswers(paperId, answers);
        //result.include("msg", answers.toString());
        result.redirectTo(this).view(paperId);
    }     

    @Get()
    public void form() {
        result.include("paperTypes", service.listPaperTypes());
    }

    @Post()
    public void save(@Valid Paper o) {
        validator.onErrorRedirectTo(this).form();
        service.save(o);
        result.include("msg", "form.saved");
        result.redirectTo(ProjectController.class).view(o.getSearchSection().getProject().getId());
    }    
}