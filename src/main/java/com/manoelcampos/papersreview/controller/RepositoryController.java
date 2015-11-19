package com.manoelcampos.papersreview.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.IncludeParameters;
import br.com.caelum.vraptor.jpa.extra.Load;
import br.com.caelum.vraptor.validator.Validator;
import com.manoelcampos.papersreview.dao.DAO;
import com.manoelcampos.papersreview.model.Repository;
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
public class RepositoryController  {
    @Inject
    private DAO<Repository> dao;
    
    @Inject 
    private Result result;
    
    @Inject
    private Validator validator;
    
    @Inject
    private Locale locale;

    @Inject
    private ResourceBundle bundle;    
        
    @Get()
    public List<Repository> index() {
        return dao.list();
        //result.use(Results.json()).from(dao.list()).serialize();
    }
    
    @Get("/repository/remove/{repository.id}")
    public void remove(@NotNull @Load final Repository repository) {
        dao.remove(repository);
        result.include("msg", "form.removed");
        result.redirectTo(this).index();
    }

    @Get("/repository/edit/{repository.id}")
    @IncludeParameters
    public void edit(@NotNull @Load final Repository repository) {
        result.of(this).form();
    }

    @Get("/repository/form")
    public void form() {}

    @Post()
    public void save(@Valid Repository repository) {
        validator.onErrorRedirectTo(this).form();
        dao.save(repository);
        //I18nMessage msg = new I18nMessage("success", bundle.getString("form.included"), Severity.INFO, "Repository");
        result.include("msg", "form.saved");
        result.redirectTo(this).index();
    }    
}