package com.manoelcampos.papersreview.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author <a href="https://about.me/manoelcampos">Manoel Campos da Silva Filho</a>
 */
@Entity
@Table(uniqueConstraints={
    @UniqueConstraint(name = "ix_description", columnNames = {"project_id", "description"}),
})
public class FieldGroup implements EntityInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull @NotEmpty @Size(max = 120)
    private String description;

    @NotNull @NotEmpty
    private String notes;

    @NotNull @NotEmpty  @Size(max = 80)
    private String tableId;

    @ManyToOne(optional = false)
    private Project project;

    public FieldGroup(){
        this.id = 0L;
        this.project = new Project();
        this.description = "";
        this.notes = "";
        this.tableId = "";
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static final FieldGroup NULL = new FieldGroup();

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }
}
