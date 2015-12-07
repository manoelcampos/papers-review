package com.manoelcampos.papersreview.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.interceptor.IncludeParameters;
import br.com.caelum.vraptor.jpa.extra.Load;
import br.com.caelum.vraptor.validator.Validator;
import com.manoelcampos.papersreview.model.Paper;
import com.manoelcampos.papersreview.model.Repository;
import com.manoelcampos.papersreview.model.SearchSession;
import com.manoelcampos.papersreview.service.ProjectService;
import com.manoelcampos.papersreview.model.Project;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Manoel Campos da Silva Filho <manoelcampos at gmail.com>
 */
@Controller
public class ProjectController extends BaseController  {
    @Inject
    private ProjectService service;
    
    @Inject
    private Validator validator;
    
    @Inject
    private Locale locale;

    @Inject
    private ResourceBundle bundle;    
        
    @Get("/project")
    public List<Project> index() {
        return service.list();
    }
    
    @Get("/project/remove/{project.id}")
    public void remove(@NotNull @Load final Project project) {
        service.remove(project);
        result.include("msg", "form.removed");
        result.redirectTo(this).index();
    }

    @Get("/project/edit/{project.id}")
    @IncludeParameters
    public void edit(@NotNull @Load final Project project) {
        result.redirectTo(this).form();
    }

    @Get()
    public void form() {
        result.include("endUsers", service.listEndUsers());
    }

    @Get("/project/{project.id}/fields")
    public Project fields(@NotNull @Load Project project) {
        return project;
    }

    @Get("/project/{project.id}")
    public Project view(@NotNull @Load Project project) {
        final Map<Repository, Map<SearchSession, List<Paper>>> repositoriesMap =
                service.listPapersGroupedByRepositoryAndSearchSession(project);
        result.include("repositoriesMap", repositoriesMap);

        return project;
    }

    @Post()
    public void save(@Valid Project project) {
        validator.onErrorRedirectTo(this).form();
        service.save(project);
        result.include("msg", "form.saved");
        result.redirectTo(this).index();
    }    
}