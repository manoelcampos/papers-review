package com.manoelcampos.papersreview.service;

import com.manoelcampos.papersreview.dao.DAO;
import com.manoelcampos.papersreview.dao.PaperDAO;
import com.manoelcampos.papersreview.dao.ProjectDAO;
import com.manoelcampos.papersreview.dao.SearchSessionDAO;
import com.manoelcampos.papersreview.model.Paper;
import com.manoelcampos.papersreview.model.Repository;
import com.manoelcampos.papersreview.model.SearchSession;
import com.manoelcampos.papersreview.model.Project;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

/**
 *
 * @author Manoel Campos da Silva Filho <manoelcampos at gmail.com>
 */
@Dependent
public class SearchSessionService {
    @Inject
    private SearchSessionDAO dao;
    
    @Inject
    private DAO<Repository> repositoryDao;

    @Inject
    private ProjectDAO projectDao;

    @Inject
    private PaperDAO paperDao;
    /**
     * @return the dao
     */
    public List<SearchSession> list() {
        return dao.list();
    }
    
    public boolean remove(final SearchSession searchSession){
        return dao.remove(searchSession);
    }

    public boolean save(final SearchSession o){
        o.setRepository(repositoryDao.findById(o.getRepository().getId()));
        o.setProject(projectDao.findById(o.getProject().getId()));
        return dao.save(o);
    }
    
    public SearchSession findById(final Long id){
        return dao.findById(id);
    }
    
    public List<SearchSession> listByProject(final Project project){
        return dao.listByProject(project);
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
