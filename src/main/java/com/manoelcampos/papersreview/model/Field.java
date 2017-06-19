package com.manoelcampos.papersreview.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.StringUtils;
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
public class Field implements EntityInterface,AbbreviableDescription {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull @NotEmpty
    @Size(max = 120)
    private String description;

    @Size(max = 10)
    private String abbreviation;

    private boolean showInReports = true;

    @ManyToOne(optional = false)
    private FieldType fieldType;

    @ManyToOne(optional = true)
    private FieldGroup fieldGroup;
    
    @Column(nullable = true)
    private String notes;        

    @OneToMany(orphanRemoval = true, mappedBy = "field")
    private final List<PaperFieldAnswer> paperFieldAnswers;

    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true, mappedBy = "field", fetch = FetchType.EAGER)
    private final List<FieldOption> fieldOptions;
    
    @ManyToOne(optional = false)
    private Project project;

    public Field() {
        this.project = new Project();
        this.paperFieldAnswers = new ArrayList<>();
        this.fieldOptions = new ArrayList<>();
    }

    public Field(Long id) {
        this();
        this.id = id;
    }

    public Field(String description) {
        this();
        this.description = description;
    }

    public Field(Long id, final Long projectId) {
        this(new Project(projectId));
        this.id = id;
    }

    public Field(Long id, final Project project) {
        this(project);
        this.id = id;
    }

    public Field(final Project project) {
        this();
        this.setProject(project);
    }
    
    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getAbbreviation() {
        return abbreviation;
    }

    @Override
    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    @Override
    public String getDescription(boolean abbreviated) {
        return Util.getDescription(description, abbreviation, abbreviated);
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

    public List<FieldOption> getFieldOptionsToBeShownInReports() {
        return fieldOptions.stream().filter(fo -> fo.isShowInReports()==true).collect(Collectors.toList());
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

    /**
     * @return the notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * @param notes the notes to set
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean isShowInReports() {
        return showInReports;
    }

    public void setShowInReports(boolean showInReports) {
        this.showInReports = showInReports;
    }

    public List<String> getFieldOptionsThatHaveAbbreviation() {
        final List<String> list = new ArrayList<>();
        fieldOptions.stream()
                .filter(o -> StringUtils.isNotEmpty(o.getAbbreviation()))
                .forEach(o -> list.add(String.format("%s = %s", o.getAbbreviation(), o.getDescription())));
        return list;
    }

    public static final Field NULL = new Field(0L, Project.NULL);

    public FieldGroup getFieldGroup() {
        return fieldGroup;
    }

    public void setFieldGroup(FieldGroup fieldGroup) {
        this.fieldGroup = fieldGroup;
    }
}
