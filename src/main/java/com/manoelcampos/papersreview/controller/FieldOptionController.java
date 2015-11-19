package com.manoelcampos.papersreview.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.IncludeParameters;
import br.com.caelum.vraptor.jpa.extra.Load;
import br.com.caelum.vraptor.validator.Validator;
import com.manoelcampos.papersreview.model.Field;
import com.manoelcampos.papersreview.model.FieldOption;
import com.manoelcampos.papersreview.model.Project;
import com.manoelcampos.papersreview.service.FieldOptionService;
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
public class FieldOptionController  {
    @Inject
    private FieldOptionService service;
    
    @Inject 
    private Result result;
    
    @Inject
    private Validator validator;
    
    @Inject
    private Locale locale;

    @Inject
    private ResourceBundle bundle;    
    
    @Get("/fieldOption/remove/{fieldOption.id}")
    public void remove(@NotNull @Load FieldOption fieldOption) {
        service.remove(fieldOption);
        result.include("msg", "form.removed");
        result.redirectTo(ProjectController.class).fields(fieldOption.getField().getProject());
    }

    @Get("/fieldOption/edit/{fieldOption.id}")
    @IncludeParameters
    public void edit(@NotNull @Load FieldOption fieldOption) {
        result.redirectTo(this).form(fieldOption.getField());
    }

    @Get("/field/{field.id}/fieldOption/form")
    public void form(@NotNull @Load final Field field) {
        result.include("fieldOption", new FieldOption(field));
        result.include("fieldOptions", service.list(field));
    }

    @Post()
    public void save(@Valid FieldOption fieldOption, Project project) {
        validator.onErrorRedirectTo(this).form(fieldOption.getField());
        
        service.save(fieldOption);
        result.include("msg", "form.saved");
        
        result.redirectTo(FieldController.class).view(fieldOption.getField());
    }    

    private boolean redirectToProjectView(final Project project) {
        return project != null && project.getId() != null && project.getId() > 0;
    }
}