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
    @UniqueConstraint(name = "ix_FieldDescription", columnNames = {"project_id", "description"}),
    @UniqueConstraint(name = "ix_FieldAbbrev", columnNames = {"project_id", "abbreviation"})    
})
public class Field implements EntityInterface {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull @NotEmpty
    @Size(max = 120)
    private String description;

    @Size(max = 10)
    private String abbreviation;

    @ManyToOne(optional = false)
    private FieldType fieldType;

    @OneToMany(orphanRemoval = true, mappedBy = "field")
    final private List<PaperFieldAnswer> paperFieldAnswers = new ArrayList<>();

    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true, mappedBy = "field")
    final private List<FieldOption> fieldOptions = new ArrayList<>();
    
    @ManyToOne(optional = false)
    private Project project;

    public Field() {
    }

    public Field(Long id) {
        this.id = id;
    }

    public Field(String description) {
        this.description = description;
    }

    public Field(Long id, Long projectId) {
        this(projectId, "");
        this.id = id;
    }

    public Field(Long id, Project project) {
        this.id = id;
        this.project = project;
    }

    public Field(Long projectId, String description) {
        this.setProject(new Project(projectId));
        this.description = description;
    }
    
    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public FieldType getFieldType() {
        return fieldType;
    }

    public void setFieldType(FieldType fieldType) {
        this.fieldType = fieldType;
    }

    public List<PaperFieldAnswer> getPaperFieldAnswers() {
        return paperFieldAnswers;
    }

    public void addPaperFieldAnswer(PaperFieldAnswer paperFieldAnswer) {
        this.paperFieldAnswers.add(paperFieldAnswer);
    }

    public List<FieldOption> getFieldOptions() {
        return fieldOptions;
    }

    public void addFieldOption(FieldOption fieldOption) {
        this.fieldOptions.add(fieldOption);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Field)) {
            return false;
        }
        Field other = (Field) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        String proj = (project != null ? project.getDescription() : "");
        return String.format("%s[id=%d, project=%s, description=%s]", 
                getClass().getSimpleName(), id, proj, description);
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
    public final void setProject(Project project) {
        this.project = project;
    }
    
    public boolean isNotSubjective(){
        return !isSubjective();
    }

    public boolean isSubjective(){
        return "S".equalsIgnoreCase(fieldType.getAbbreviation());
    }

    public boolean isMultiple(){
        return "M".equalsIgnoreCase(fieldType.getAbbreviation());
    }
    
    public boolean isNotMultiple(){
        return !isMultiple();
    }

    public boolean isObjective(){
        return "O".equalsIgnoreCase(fieldType.getAbbreviation());
    }
}
