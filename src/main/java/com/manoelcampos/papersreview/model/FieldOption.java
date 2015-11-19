package com.manoelcampos.papersreview.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
    @UniqueConstraint(name = "ix_FieldOptionDesc", columnNames = {"field_id", "description"}),
    @UniqueConstraint(name = "ix_FieldOptionAbbrev", columnNames = {"field_id", "abbreviation"})
})
public class FieldOption implements EntityInterface {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull @NotEmpty
    @Size(max = 120)
    private String description;

    @Size(max = 20)
    private String abbreviation;

    @ManyToOne(optional = false)
    private Field field;

    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    private FieldOption parentFieldOption;
    
    @Column(nullable = true)
    private String notes;    
    
    public FieldOption() {
        this.parentFieldOption = null;
    }

    public FieldOption(Long id) {
        this();
        this.id = id;
    }

    public FieldOption(Long id, String description) {
        this(id);
        this.description = description;
    }

    public FieldOption(final Field field) {
        this();
        this.field = field;
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
    
    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof FieldOption)) {
            return false;
        }
        FieldOption other = (FieldOption) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return String.format("%s[id=%d, field=%s, description=%s]", getClass().getSimpleName(), id, field.getDescription(), description);
    }

    /**
     * @return the parentFieldOption
     */
    public FieldOption getParentFieldOption() {
        return parentFieldOption;
    }

    /**
     * @param parentFieldOption the parentFieldOption to set
     */
    public void setParentFieldOption(FieldOption parentFieldOption) {
        this.parentFieldOption = parentFieldOption;
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
    
}
