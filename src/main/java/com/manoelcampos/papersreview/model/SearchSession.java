package com.manoelcampos.papersreview.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Manoel Campos da Silva Filho <manoelcampos at gmail.com>
 */
@Entity
public class SearchSession implements EntityInterface {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull @NotEmpty @Size(max = 200)
    private String searchString;
    
    @NotNull @ManyToOne(optional = false)
    private Project project;
    
    @NotNull @ManyToOne(optional = false)
    private Repository repository;
    
    @NotNull @Temporal(TemporalType.DATE)
    private Date searchDate;

    public SearchSession(){
        this.project = new Project();
        this.repository = new Repository();
    }

    public SearchSession(long id){
        this();
        this.id = id;
    }
    
    public SearchSession(final Project project){
        this();
        this.project = project;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("%s[id=%d, repository=%s]", getClass().getSimpleName(), id, repository.getDescription());
    }

    /**
     * @return the searchString
     */
    public String getSearchString() {
        return searchString;
    }

    /**
     * @param searchString the searchString to set
     */
    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    /**
     * @return the project
     */
    public Project getProject() {
        return project;
    }

    /**
     * @param project the project to set
     */
    public void setProject(Project project) {
        this.project = project;
    }

    /**
     * @return the repository
     */
    public Repository getRepository() {
        return repository;
    }

    /**
     * @param repository the repository to set
     */
    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    /**
     * @return the searchDate
     */
    public Date getSearchDate() {
        return searchDate;
    }

    /**
     * @param searchDate the searchDate to set
     */
    public void setSearchDate(Date searchDate) {
        this.searchDate = searchDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof SearchSession)) {
            return false;
        }
        SearchSession other = (SearchSession) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

}
