package com.manoelcampos.papersreview.model;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Manoel Campos da Silva Filho <manoelcampos at gmail.com>
 */
@Entity
@Table(uniqueConstraints={
    @UniqueConstraint(name = "ix_PaperFieldAnswerOption", columnNames = {"paper_id", "field_id", "fieldOption_id"}),
    @UniqueConstraint(name = "ix_PaperFieldAnswerSubjectAnswer", columnNames = {"paper_id", "field_id", "subjectiveAnswer"})
})
public class PaperFieldAnswer implements EntityInterface {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Size(max = 120)
    private String subjectiveAnswer;
    
    @NotNull
    @ManyToOne(optional = false)
    private Paper paper;
    
    @NotNull
    @ManyToOne(optional = false)
    private Field field;
    
    @ManyToOne
    private FieldOption fieldOption;

    public PaperFieldAnswer() {
        this.paper = Paper.NULL;
        this.field = Field.NULL;
        this.fieldOption = FieldOption.NULL;
    }
    
    public PaperFieldAnswer(final Paper paper, final Field field) {
        this();
        this.paper = paper;
        this.field = field;
    }
    
    public PaperFieldAnswer(final Paper paper, final FieldOption fieldOption) {
        this(paper, fieldOption.getField());
        this.fieldOption = fieldOption;
    }

    public PaperFieldAnswer(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubjectiveAnswer() {
        return subjectiveAnswer;
    }

    public void setSubjectiveAnswer(String subjectiveAnswer) {
        this.subjectiveAnswer = subjectiveAnswer;
    }

    public Paper getPaper() {
        return paper;
    }

    public void setPaper(Paper paper) {
        this.paper = paper;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public FieldOption getFieldOption() {
        return fieldOption;
    }

    public void setFieldOption(FieldOption fieldOption) {
        this.fieldOption = fieldOption;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PaperFieldAnswer)) {
            return false;
        }
        PaperFieldAnswer other = (PaperFieldAnswer) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        final String paperTitle = (paper != null ? "paper="+paper.getTitle()+"," : "");
        final String fieldDesc = (field != null ? "field="+field.getDescription() +"," : "");
        final String option = (fieldOption != null  ? fieldOption.getId().toString() : "");
        final String answer = (subjectiveAnswer != null && !subjectiveAnswer.isEmpty() ? subjectiveAnswer : "");
        return String.format("%s[id=%d, %s %s option=%s answer=%s]", 
                getClass().getSimpleName(), id, paperTitle, fieldDesc, option, answer);
    }
    
    /**
     * Obtém a resposta subjetiva (caso o campo seja subjetivo) ou a opção escolhida (caso o 
     * campo seja objetivo ou múltipla escolha).
     * @return 
     */
    public String getAnswer(){
        return (field.isSubjective() ? subjectiveAnswer : fieldOption.getDescription());
    }

    public String getAnswer(boolean abbreviated){
        return (field.isSubjective() ? subjectiveAnswer : fieldOption.getDescription(abbreviated));
    }

}
