package com.manoelcampos.papersreview.service;

import com.manoelcampos.papersreview.dao.DAO;
import com.manoelcampos.papersreview.dao.PaperDAO;
import com.manoelcampos.papersreview.dao.ProjectDAO;
import com.manoelcampos.papersreview.dao.SearchSessionDAO;
import com.manoelcampos.papersreview.dto.PaperCountByStatusDTO;
import com.manoelcampos.papersreview.model.EndUser;
import com.manoelcampos.papersreview.model.Paper;
import com.manoelcampos.papersreview.model.Project;
import com.manoelcampos.papersreview.model.Repository;
import com.manoelcampos.papersreview.model.SearchSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

/**
 *
 * @author Manoel Campos da Silva Filho <manoelcampos at gmail.com>
 */
@Dependent
public class ProjectService {
    @Inject
    private ProjectDAO dao;
    
    @Inject
    private DAO<Repository> repositoriesDao;

    @Inject
    private PaperDAO paperDao;

    @Inject
    private SearchSessionDAO searchSessionDao;

    @Inject
    private DAO<EndUser> endUserDao;

    /**
     * @return the dao
     */
    public List<Project> list() {
        return dao.list();
    }
    
    public boolean remove(final Project project){
        return dao.remove(project);
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
    
    public Map<Repository, Map<SearchSession, List<Paper>>> listPapersGroupedByRepositoryAndSearchSession(final Project p){
        Map<Repository, Map<SearchSession, List<Paper>>> result = new HashMap<>();
        List<Repository> repositories = repositoriesDao.list();
        
        for(Repository r: repositories) {
            List<SearchSession> searchSessions = searchSessionDao.listByProjectAndRepository(p, r);
            Map<SearchSession, List<Paper>> papersMap = new HashMap<>();
            for(SearchSession s: searchSessions){
                papersMap.put(s, paperDao.listBySearchSession(s));
            }
            result.put(r, papersMap);
        }
        return result;
    }

    public Map<SearchSession, List<PaperCountByStatusDTO>> getPaperCountByStatusForEachSearchSession(final Project p) {
        final Map<SearchSession, List<PaperCountByStatusDTO>> map = new HashMap<>();

        final List<SearchSession> searchSessions = searchSessionDao.listByProject(p);

        for (SearchSession s : searchSessions) {
            map.put(s, searchSessionDao.getPaperCountByStatus(s));
        }

        return map;
    }
}
