package com.manoelcampos.papersreview.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.IncludeParameters;
import br.com.caelum.vraptor.jpa.extra.Load;
import br.com.caelum.vraptor.observer.upload.UploadedFile;
import br.com.caelum.vraptor.validator.Validator;
import com.manoelcampos.papersreview.model.BibTexReader;
import com.manoelcampos.papersreview.model.Paper;
import com.manoelcampos.papersreview.model.Project;
import com.manoelcampos.papersreview.model.SearchSession;
import com.manoelcampos.papersreview.service.SearchSessionService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.jbibtex.BibTeXEntry;

/**
 *
 * @author Manoel Campos da Silva Filho <manoelcampos at gmail.com>
 */
@Controller
@Stateless
@Dependent
public class SearchSessionController  {
    @Inject
    private SearchSessionService service;
    
    @Inject 
    private Result result;
    
    @Inject
    private Validator validator;
    
    @Inject
    private Locale locale;

    @Inject
    private ResourceBundle bundle;    
        
    @Get("/project/{project.id}/searchSection")
    @IncludeParameters 
    public List<SearchSession> index(@NotNull @Load final Project project) {
        return service.listByProject(project);
    }
    
    @Get("/searchSection/importPapers/{searchSession.id}")
    public SearchSession importPapers(@NotNull @Load final SearchSession searchSession) {
        return searchSession;
    }
    
    @Post()
    public void saveImportedPapers(@NotNull @Load final SearchSession searchSession, final UploadedFile bibTexFile) {
        try {
            List<Paper> paperList = createPaperListFromBibTexFile(searchSession, bibTexFile);
            service.saveListOfPapers(paperList);
            result.include("paperList", paperList);
            result.include("msg", "form.papersIncluded");
        } catch (IOException e) {
            result.include("msg", "form.bibFileAccessError");
        }
        result.redirectTo(SearchSessionController.class).importPapers(searchSession);
    }

    private List<Paper> createPaperListFromBibTexFile(final SearchSession o, final UploadedFile bibTexFile) throws NumberFormatException, IOException {
        BibTexReader bibTexReader = new BibTexReader(bibTexFile.getFile());
        List<Paper> list = new ArrayList<>();
        for(BibTeXEntry b: bibTexReader.getEntriesCollection()){
            Paper p = new Paper();
            p.setAuthors(bibTexReader.getFieldValue(b, BibTeXEntry.KEY_AUTHOR));
            p.setCitationKey(b.getKey().toString());
            p.setDoi(bibTexReader.getFieldValue(b, BibTeXEntry.KEY_DOI));
            p.setPublicationYear(Integer.parseInt(bibTexReader.getFieldValue(b, BibTeXEntry.KEY_YEAR)));
            p.setTitle(bibTexReader.getFieldValue(b, BibTeXEntry.KEY_TITLE));
            p.setUrl(bibTexReader.getFieldValue(b, BibTeXEntry.KEY_URL));
            p.setSearchSession(o);
            //p.setPaperAbstract(bibTexReader.getFieldValue(b, BibTeXEntry.KEY_ABSTRACT));
            list.add(p);
        }
        return list;
    }

    @Get("/searchSection/remove/{searchSession.id}")
    public void remove(@NotNull @Load final SearchSession searchSession) {
        service.remove(searchSession);
        result.include("msg", "form.removed");
        result.redirectTo(SearchSessionController.class).index(searchSession.getProject());
    }

    @Get("/searchSection/edit/{searchSession.id}")
    @IncludeParameters
    public void edit(@NotNull @Load final SearchSession searchSession) {
        result.redirectTo(this).form(searchSession);
    }

    @Get("/project/{project.id}/searchSection/form")
    public void form(@NotNull final Project project) {
        includeSearchSessionAndRepositoriesInResult(new SearchSession(project));
    }

    @Get("/project/{searchSession.project.id}/searchSection/form/{searchSession.id}")
    public void form(@NotNull final SearchSession searchSession) {
        includeSearchSessionAndRepositoriesInResult(searchSession);
    }

    private void includeSearchSessionAndRepositoriesInResult(final SearchSession searchSession) {
        result.include("searchSession", searchSession);
        result.include("repositories", service.listRepositories());
    }

    @Post()
    public void save(@Valid SearchSession searchSession) {
        validator.onErrorRedirectTo(this).form(searchSession.getProject());
        service.save(searchSession);
        result.include("msg", "form.saved");
        result.redirectTo(SearchSessionController.class).index(searchSession.getProject());
    }    
}