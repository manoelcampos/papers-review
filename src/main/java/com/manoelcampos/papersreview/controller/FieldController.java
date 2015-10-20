package com.manoelcampos.papersreview.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;
import com.manoelcampos.papersreview.service.FieldService;
import com.manoelcampos.papersreview.model.Field;
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
public class FieldController  {
    @Inject
    private FieldService service;
    
    @Inject 
    private Result result;
    
    @Inject
    private Validator validator;
    
    @Inject
    private Locale locale;

    @Inject
    private ResourceBundle bundle;    
        
    @Get()
    public List<Field> index() {
        return service.list();
    }
    
    @Get()
    public void remove(@NotNull final Long id) {
        Field f = service.remove(id);
        result.include("msg", "form.removed");
        result.redirectTo(ProjectController.class).fields(f.getProject().getId());
    }

    @Get()
    public void edit(@NotNull final Long id) {
        Field field = service.findById(id);
        result.include("o", field);
        result.redirectTo(this).form(0L);
    }

    @Get()
    public void form(@NotNull final Long projectId) {
        if(projectId > 0)
            result.include("o", new Field(projectId, ""));
        result.include("fieldTypes", service.listFieldTypes());
    }

    @Post()
    public void save(@Valid Field o) {
        validator.onErrorRedirectTo(this).form(o.getId());
        service.save(o);
        result.include("msg", "form.saved");
        result.redirectTo(ProjectController.class).fields(o.getProject().getId());
    }    
}