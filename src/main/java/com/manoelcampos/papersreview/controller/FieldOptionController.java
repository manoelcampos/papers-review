package com.manoelcampos.papersreview.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;
import com.manoelcampos.papersreview.model.Field;
import com.manoelcampos.papersreview.model.FieldOption;
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
    public void remove(@NotNull final Long id) {
        FieldOption o = service.remove(id);
        result.include("msg", "form.removed");
        result.redirectTo(ProjectController.class).view(o.getField().getProject().getId());
    }

    @Get()
    public void edit(@NotNull final Long id) {
        FieldOption field = service.findById(id);
        result.include("o", field);
        result.redirectTo(this).form(0L, 0L);
    }

    @Get()
    public void form(@NotNull final Long fieldId, @NotNull final Long projectId) {
        if(fieldId > 0)
            result.include("o", new FieldOption(new Field(fieldId, projectId)));
    }

    @Post()
    public void save(@Valid FieldOption o) {
        validator.onErrorRedirectTo(this).form(o.getField().getId(), o.getField().getProject().getId());
        service.save(o);
        result.include("msg", "form.saved");
        result.redirectTo(ProjectController.class).view(o.getField().getProject().getId());
    }    
}