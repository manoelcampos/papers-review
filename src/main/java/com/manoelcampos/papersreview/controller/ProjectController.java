package com.manoelcampos.papersreview.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;
import com.manoelcampos.papersreview.service.ProjectService;
import com.manoelcampos.papersreview.model.Project;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Manoel Campos da Silva Filho <manoelcampos at gmail.com>
 */
@Controller
public class ProjectController  {
    @Inject
    private ProjectService service;
    
    @Inject 
    private Result result;
    
    @Inject
    private Validator validator;
    
    @Inject
    private Locale locale;

    @Inject
    private ResourceBundle bundle;    
        
    @Get()
    public List<Project> index() {
        return service.list();
    }
    
    @Get()
    public void remove(@NotNull final Long id) {
        service.remove(id);
        result.include("msg", "form.removed");
        result.redirectTo(this).index();
    }

    @Get()
    public void edit(@NotNull final Long id) {
        result.include("o", service.findById(id));
        //result.of(this).form();
        result.redirectTo(this).form();
    }

    @Get()
    public void form() {
        result.include("endUsers", service.listEndUsers());
    }

    @Get()
    public void fields(@NotNull final Long id) {
        result.include("o", service.findById(id));
    }

    @Get()
    public void view(@NotNull final Long id) {
        Project project = service.findById(id);
        result.include("o", project);
        result.include("repositoriesMap", service.listPapersGroupedByRepositoryAndSearchSection(project));
    }

    @Post()
    public void save(@Valid Project o) {
        validator.onErrorRedirectTo(this).form();
        service.save(o);
        result.include("msg", "form.saved");
        result.redirectTo(this).index();
    }    
}