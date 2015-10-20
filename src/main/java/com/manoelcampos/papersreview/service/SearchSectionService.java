package com.manoelcampos.papersreview.service;

import com.manoelcampos.papersreview.dao.DAO;
import com.manoelcampos.papersreview.dao.PaperDAO;
import com.manoelcampos.papersreview.dao.SearchSectionDAO;
import com.manoelcampos.papersreview.model.Paper;
import com.manoelcampos.papersreview.model.Repository;
import com.manoelcampos.papersreview.model.SearchSection;
import com.manoelcampos.papersreview.model.Project;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

/**
 *
 * @author Manoel Campos da Silva Filho <manoelcampos at gmail.com>
 */
@Dependent
public class SearchSectionService {
    @Inject
    private SearchSectionDAO dao;
    
    @Inject
    private DAO<Repository> repositoryDao;

    @Inject
    private DAO<Project> projectDao;

    @Inject
    private PaperDAO paperDao;
    /**
     * @return the dao
     */
    public List<SearchSection> list() {
        return dao.list();
    }
    
    public SearchSection remove(final Long id){
        SearchSection s = dao.findById(id);
        return (dao.remove(s) ? s : null);
    }

    public boolean save(final SearchSection o){
        o.setRepository(repositoryDao.findById(o.getRepository().getId()));
        o.setProject(projectDao.findById(o.getProject().getId()));
        return dao.save(o);
    }
    
    public SearchSection findById(final Long id){
        return dao.findById(id);
    }
    
    public List<SearchSection> listByProject(final Long projectId){
        return dao.listByProject(projectId);
    }
    
    public List<Repository> listRepositories(){
        return repositoryDao.list();
    }
    
    public Project getProject(final Long projectId){
        return projectDao.findById(projectId);
    }
    
    public boolean saveListOfPapers(List<Paper> list){
        return paperDao.saveListOfPapers(list);
    }
}
