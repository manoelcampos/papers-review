package com.manoelcampos.papersreview.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.observer.upload.UploadedFile;
import br.com.caelum.vraptor.validator.Validator;
import com.manoelcampos.papersreview.model.BibTexReader;
import com.manoelcampos.papersreview.model.Paper;
import com.manoelcampos.papersreview.model.SearchSection;
import com.manoelcampos.papersreview.service.SearchSectionService;
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
public class SearchSectionController  {
    @Inject
    private SearchSectionService service;
    
    @Inject 
    private Result result;
    
    
    @Inject
    private Validator validator;
    
    @Inject
    private Locale locale;

    @Inject
    private ResourceBundle bundle;    
        
    @Get()
    public void index(@NotNull final Long projectId) {
        result.include("list",service.listByProject(projectId));
        result.include("project", service.getProject(projectId));
    }
    
    @Get()
    public void importPapers(@NotNull final Long id) {
        result.include("o", service.findById(id));
    }
    
    @Post()
    public void saveImportedPapers(@NotNull final SearchSection o, final UploadedFile bibTexFile) {
        List<Paper> list = createPaperListFromBibTexFile(o, bibTexFile);
        service.saveListOfPapers(list);
        result.include("list", list);
        result.include("msg", "form.papersIncluded");
        result.redirectTo(SearchSectionController.class).importPapers(o.getId());
    }

    private List<Paper> createPaperListFromBibTexFile(final SearchSection o, final UploadedFile bibTexFile) throws NumberFormatException {
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
            p.setSearchSection(o);
            list.add(p);
        }
        return list;
    }

    @Get()
    public void remove(@NotNull final Long id) {
        SearchSection s = service.remove(id);
        result.include("msg", "form.removed");
        result.redirectTo(SearchSectionController.class).index(s.getProject().getId());
    }

    @Get()
    public void edit(@NotNull final Long id) {
        SearchSection o = service.findById(id);
        result.include("o", o);
        result.redirectTo(this).form(0L);
    }

    @Get()
    public void form(@NotNull final Long projectId) {
        if(projectId > 0)
            result.include("o", new SearchSection(projectId));
        result.include("repositories", service.listRepositories());
    }

    @Post()
    public void save(@Valid SearchSection o) {
        validator.onErrorRedirectTo(this).form(o.getId());
        service.save(o);
        result.include("msg", "form.saved");
        result.redirectTo(SearchSectionController.class).index(o.getProject().getId());
    }    
}