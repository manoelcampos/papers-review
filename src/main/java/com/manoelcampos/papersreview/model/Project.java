package com.manoelcampos.papersreview.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Manoel Campos da Silva Filho <manoelcampos at gmail.com>
 */
@Entity
@Table(uniqueConstraints={
    @UniqueConstraint(name = "ix_Project", columnNames = {"description", "endUser_id"})
})
public class Project extends EntityClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull @NotEmpty
    @Size(max = 100)
    private String description;   
    
    @NotNull
    @ManyToOne(optional = false)
    private EndUser endUser;
    
    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true, mappedBy = "project")
    final private List<Field> fields;

    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true, mappedBy = "project")
    final private List<SearchSection> searchSections;

    public Project() {
        this.fields = new ArrayList<>();
        this.searchSections = new ArrayList<>();
    }

    public Project(final Long id) {
        this();
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Project)) {
            return false;
        }
        Project other = (Project) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return String.format("%s[id=%d, description=%s]", getClass().getSimpleName(), id, description);
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the endUser
     */
    public EndUser getEndUser() {
        return endUser;
    }

    /**
     * @param endUser the endUser to set
     */
    public void setEndUser(EndUser endUser) {
        this.endUser = endUser;
    }

    /**
     * @return the fields
     */
    public List<Field> getFields() {
        return fields;
    }

    /**
     * @param field the field to added
     */
    public void addField(Field field) {
        this.fields.add(field);
    }

    /**
     * @return the searchSections
     */
    public List<SearchSection> getSearchSections() {
        return searchSections;
    }
    
    public void addSearchSection(final SearchSection searchSection){
        searchSections.add(searchSection);
    }
    
}
