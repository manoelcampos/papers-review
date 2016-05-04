package com.manoelcampos.papersreview.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.interceptor.IncludeParameters;
import br.com.caelum.vraptor.jpa.extra.Load;
import br.com.caelum.vraptor.validator.Validator;
import com.manoelcampos.papersreview.dao.DAO;
import com.manoelcampos.papersreview.dao.FieldGroupDAO;
import com.manoelcampos.papersreview.model.Field;
import com.manoelcampos.papersreview.model.FieldGroup;
import com.manoelcampos.papersreview.model.Project;
import com.manoelcampos.papersreview.service.FieldService;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author Manoel Campos da Silva Filho <manoelcampos at gmail.com>
 */
@Controller
@Stateless
@Dependent
public class FieldGroupController extends BaseController {
    @Inject
    private FieldGroupDAO dao;

    @Inject
    private Validator validator;
    
    @Inject
    private Locale locale;

    @Inject
    private ResourceBundle bundle;    
        
    @Get("/field-group/remove/{fieldGroup.id}")
    public void remove(@NotNull @Load final FieldGroup fieldGroup) {
        dao.remove(fieldGroup);
        result.include("msg", "form.removed");
        result.redirectTo(ProjectController.class).fieldGroups(fieldGroup.getProject());
    }

    @Get("/field-group/edit/{fieldGroup.id}")
    @IncludeParameters 
    public void edit(@NotNull @Load final FieldGroup fieldGroup) {
        result.redirectTo(this).form(fieldGroup.getProject());
    }
    
    @Get("/project/{project.id}/field-group/form/")
    @IncludeParameters 
    public void form(@NotNull final Project project) {
    }

    @Post()
    public void save(@Valid FieldGroup fieldGroup) {
        validator.onErrorRedirectTo(this).form(fieldGroup.getProject());
        dao.save(fieldGroup);
        result.include("msg", "form.saved");
        result.redirectTo(ProjectController.class).fieldGroups(fieldGroup.getProject());
    }    
}