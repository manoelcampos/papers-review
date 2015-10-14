package com.manoelcampos.papersreview.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
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
    
    @Get()
    public void remove(@NotNull final Long id) {
        dao.remove(dao.findById(id));
        result.include("msg", "form.removed");
        result.redirectTo(this).index();
        //result.use(Results.json()).withoutRoot().from(dao.remove(dao.findById(id))).serialize();
    }

    @Get()
    public void edit(@NotNull final Long id) {
        result.include("o", dao.findById(id));
        result.of(this).form();
        //result.use(Results.json()).withoutRoot().from(dao.findById(id)).serialize();
    }

    @Get()
    public void form() {}

    @Post()
    public void save(@Valid Repository o) {
        validator.onErrorRedirectTo(this).form();
        dao.save(o);
        //I18nMessage msg = new I18nMessage("success", bundle.getString("form.included"), Severity.INFO, "Repository");
        result.include("msg", "form.saved");
        result.redirectTo(this).index();
        
        //result.use(Results.json()).withoutRoot().from(dao.save(o)).serialize();
    }    
}