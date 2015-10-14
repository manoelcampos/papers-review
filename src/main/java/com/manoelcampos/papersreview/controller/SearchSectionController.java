package com.manoelcampos.papersreview.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;
import com.manoelcampos.papersreview.model.SearchSection;
import com.manoelcampos.papersreview.service.SearchSectionService;
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
public class SearchSectionController  {
    @Inject
    private SearchSectionService service;
    
    @Inject 
    private Result result;
    
    @Inject
    private Validator validator;
    
    @Inject
    private Locale locale;

    @Inject
    private ResourceBundle bundle;    
        
    
    @Get()
    public void remove(@NotNull final Long id) {
        SearchSection s = service.remove(id);
        result.include("msg", "form.removed");
        result.redirectTo(ProjectController.class).view(s.getProject().getId());
    }

    @Get()
    public void edit(@NotNull final Long id) {
        SearchSection s = service.findById(id);
        result.include("o", s);
        result.redirectTo(this).form(0L);
    }

    @Get()
    public void form(@NotNull final Long projectId) {
        if(projectId > 0)
            result.include("o", new SearchSection(projectId));
        result.include("fieldTypes", service.listRepositories());
    }

    @Post()
    public void save(@Valid SearchSection o) {
        validator.onErrorRedirectTo(this).form(o.getId());
        service.save(o);
        result.include("msg", "form.saved");
        result.redirectTo(ProjectController.class).view(o.getProject().getId());
    }    
}