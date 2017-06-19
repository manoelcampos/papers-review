package com.manoelcampos.papersreview.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Defines if the field requires a single object, multiple objective or subjective answer.
 * @author Manoel Campos da Silva Filho <manoelcampos at gmail.com>
 */
@Entity
@Table(uniqueConstraints={
    @UniqueConstraint(name = "ix_FieldTypeDesc", columnNames = {"description"}),
    @UniqueConstraint(name = "ix_FieldTypeAbbrev", columnNames = {"abbreviation"})
})
public class FieldType implements EntityInterface {
    public static final FieldType NULL = new FieldType(-1L);

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull @NotEmpty
    @Size(max = 50)
    private String description;

    @Size(max = 2)
    private String abbreviation;

    public FieldType() {}

    public FieldType(Long id) {
        this.id = id;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof FieldType)) {
            return false;
        }
        FieldType other = (FieldType) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return String.format("%s[id=%d, description=%s]", getClass().getSimpleName(), id, description);
    }
    
}
