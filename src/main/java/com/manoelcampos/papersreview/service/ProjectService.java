package com.manoelcampos.papersreview.service;

import com.manoelcampos.papersreview.dao.DAO;
import com.manoelcampos.papersreview.dao.PaperDAO;
import com.manoelcampos.papersreview.dao.SearchSectionDAO;
import com.manoelcampos.papersreview.model.EndUser;
import com.manoelcampos.papersreview.model.Paper;
import com.manoelcampos.papersreview.model.Project;
import com.manoelcampos.papersreview.model.Repository;
import com.manoelcampos.papersreview.model.SearchSection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

/**
 *
 * @author Manoel Campos da Silva Filho <manoelcampos at gmail.com>
 */
@Dependent
public class ProjectService {
    @Inject
    private DAO<Project> dao;
    
    @Inject
    private DAO<Repository> repositoriesDao;

    @Inject
    private PaperDAO paperDao;

    @Inject
    private SearchSectionDAO searchSectionDao;

    @Inject
    private DAO<EndUser> endUserDao;

    /**
     * @return the dao
     */
    public List<Project> list() {
        return dao.list();
    }
    
    public boolean remove(final Long id){
        return dao.remove(dao.findById(id));
    }

    public boolean save(final Project o){
        o.setEndUser(endUserDao.findById(o.getEndUser().getId()));
        return dao.save(o);
    }
    
    public Project findById(final Long id){
        return dao.findById(id);
    }
    
    public List<EndUser> listEndUsers(){
        return endUserDao.list();
    }
    
    public Map<Repository, Map<SearchSection, List<Paper>>> listPapersGroupedByRepositoryAndSearchSection(final Project p){
        Map<Repository, Map<SearchSection, List<Paper>>> result = new HashMap<>();
        List<Repository> repositories = repositoriesDao.list();
        
        for(Repository r: repositories) {
            List<SearchSection> searchSections = searchSectionDao.listByProjectAndRepository(p, r);
            Map<SearchSection, List<Paper>> papersMap = new HashMap<>();
            for(SearchSection s: searchSections){
                papersMap.put(s, paperDao.listBySearchSection(s));
            }
            result.put(r, papersMap);
        }
        return result;
    }
    
}
