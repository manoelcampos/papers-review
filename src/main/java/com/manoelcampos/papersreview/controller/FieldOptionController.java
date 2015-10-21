package com.manoelcampos.papersreview.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
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
    
    @Get()
    public void remove(@NotNull final Long id, final Long projectId) {
        FieldOption o = service.remove(id);
        result.include("msg", "form.removed");
        if(redirectToProjectView(projectId))
            result.redirectTo(ProjectController.class).fields(projectId);
        else result.redirectTo(FieldController.class).view(o.getField().getId());
    }

    @Get()
    public void edit(@NotNull final Long id, final Long projectId) {
        FieldOption field = service.findById(id);
        result.include("o", field);
        result.redirectTo(this).form(0L, projectId);
    }

    @Get()
    public void form(@NotNull final Long fieldId, final Long projectId) {
        Project project = new Project();
        if(redirectToProjectView(projectId)) {
            project = new Project(projectId);
            result.include("projectId", projectId);
        }
        else if(fieldId > 0) 
            project = service.findFieldById(fieldId).getProject();

        if(fieldId > 0) 
            result.include("o", new FieldOption(new Field(fieldId, project)));
    }

    @Post()
    public void save(@Valid FieldOption o) {
        final boolean redirToProject = redirectToProjectView(o.getField().getProject().getId());
        if(redirToProject)
            validator.onErrorRedirectTo(this).form(o.getField().getId(), o.getField().getProject().getId());
        else validator.onErrorRedirectTo(FieldController.class).view(o.getField().getId());
        service.save(o);
        result.include("msg", "form.saved");
        if(redirToProject)
            result.redirectTo(ProjectController.class).fields(o.getField().getProject().getId());
        else result.redirectTo(FieldController.class).view(o.getField().getId());
    }    

    private static boolean redirectToProjectView(final Long projectId) {
        return projectId != null && projectId > 0;
    }
}