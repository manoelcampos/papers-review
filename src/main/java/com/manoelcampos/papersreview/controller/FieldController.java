package com.manoelcampos.papersreview.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.interceptor.IncludeParameters;
import br.com.caelum.vraptor.validator.Validator;
import com.manoelcampos.papersreview.service.FieldService;
import com.manoelcampos.papersreview.model.Field;
import com.manoelcampos.papersreview.model.Project;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import br.com.caelum.vraptor.jpa.extra.Load;

/**
 *
 * @author Manoel Campos da Silva Filho <manoelcampos at gmail.com>
 */
@Controller
@Stateless
@Dependent
public class FieldController extends BaseController {
    @Inject
    private FieldService service;
        
    @Inject
    private Validator validator;
    
    @Inject
    private Locale locale;

    @Inject
    private ResourceBundle bundle;    
        
    @Get("/field/view/{field.id}")
    public Field view(@NotNull @Load final Field field) {
        result.include("project", field.getProject());
        return field;
    }
    
    @Get("/field/remove/{field.id}")
    public void remove(@NotNull @Load final Field field) {
        service.remove(field);
        result.include("msg", "form.removed");
        result.redirectTo(ProjectController.class).fields(field.getProject()); 
    }

    @Get("/field/edit/{field.id}")
    @IncludeParameters 
    public void edit(@NotNull @Load final Field field) {
        result.redirectTo(this).form(field.getProject());
    }
    
    @Get("/project/{project.id}/field/form/")
    @IncludeParameters 
    public void form(@NotNull final Project project) {
        result.include("fieldGroups", service.listFieldGroups(project));
        result.include("fieldTypes", service.listFieldTypes());
    }
    

    @Post()
    public void save(@Valid Field field) {
        validator.onErrorRedirectTo(this).form(field.getProject());
        service.save(field);
        result.include("msg", "form.saved");
        result.redirectTo(FieldController.class).view(field);
    }    
}