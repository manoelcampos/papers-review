package com.manoelcampos.papersreview.dao;

import com.manoelcampos.papersreview.dto.PaperCountByRepositoryDTO;
import com.manoelcampos.papersreview.dto.PaperCountByStatusDTO;
import com.manoelcampos.papersreview.dto.PaperCountByTypeDTO;
import com.manoelcampos.papersreview.model.EndUser;
import com.manoelcampos.papersreview.model.Project;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Manoel Campos da Silva Filho <manoelcampos at gmail.com>
 */
public class ProjectJpaDAO extends JpaDAO<Project> implements ProjectDAO {

    public ProjectJpaDAO(EntityManager em) {
        super(Project.class, em);
    }

    @Override
    public List<Project> listByEndUser(EndUser endUser) {
        final String jpql = 
                String.format("select o from %s o where o.endUser = :endUser", getGenericClassName());
        final TypedQuery<Project> qry = createQuery(jpql);
        qry.setParameter("endUser", endUser);
        return qry.getResultList();
    }

    @Override
    public List<PaperCountByStatusDTO> getPaperCountByStatus(final Project p) {
        final String jpql =
                "select new com.manoelcampos.papersreview.dto.PaperCountByStatusDTO(status, count(p)) " +
                " from Paper p left join p.status status " +
                " where p.searchSession.project = :project group by status";
        return
                getEm().createQuery(jpql, PaperCountByStatusDTO.class)
                        .setParameter("project", p)
                        .getResultList();
    }

    @Override
    public List<PaperCountByRepositoryDTO> getPaperCountByRepository(final Project p) {
        final String jpql =
                "select new com.manoelcampos.papersreview.dto.PaperCountByRepositoryDTO(p.searchSession.repository.description, count(p)) " +
                " from Paper p  " +
                " where p.searchSession.project = :project group by p.searchSession.repository";
        return
                getEm().createQuery(jpql, PaperCountByRepositoryDTO.class)
                        .setParameter("project", p)
                        .getResultList();
    }

    @Override
    public List<PaperCountByTypeDTO> getPaperCountByType(final Project p) {
        final String jpql =
                "select new com.manoelcampos.papersreview.dto.PaperCountByTypeDTO(paperType, coalesce(p.survey, -1), count(p)) " +
                " from Paper p left join p.paperType paperType " +
                " where p.searchSession.project = :project group by paperType, p.survey";
        return
                getEm().createQuery(jpql, PaperCountByTypeDTO.class)
                        .setParameter("project", p)
                        .getResultList();

    }
}
