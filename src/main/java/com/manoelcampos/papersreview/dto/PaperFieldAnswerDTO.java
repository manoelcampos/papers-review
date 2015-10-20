package com.manoelcampos.papersreview.dto;

import com.manoelcampos.papersreview.controller.PaperController;
import com.manoelcampos.papersreview.model.Field;
import java.util.List;

/**
 * Classe auxiliar para obter dados enviados pela view {@link PaperController#save(com.manoelcampos.papersreview.model.Paper) }
 * para a action {@link PaperController#saveAnswers(java.util.List) }
 * @author Manoel Campos da Silva Filho <manoelcampos at gmail.com>
 */
public class PaperFieldAnswerDTO {
    private Field field;
    private String subjectiveAnswer;
    private List<Long> fieldOptions;
    
    @Override
    public String toString(){
        final String s = (fieldOptions != null ? "Options: "+fieldOptions.toString() : " Answer: "+subjectiveAnswer);
        return String.format("Field: %d, %s", field.getId(), s);
    }

    /**
     * @return the field
     */
    public Field getField() {
        return field;
    }

    /**
     * @param field the field to set
     */
    public void setField(Field field) {
        this.field = field;
    }

    /**
     * @return the subjectiveAnswer
     */
    public String getSubjectiveAnswer() {
        return subjectiveAnswer;
    }

    /**
     * @param subjectiveAnswer the subjectiveAnswer to set
     */
    public void setSubjectiveAnswer(String subjectiveAnswer) {
        this.subjectiveAnswer = subjectiveAnswer;
    }

    /**
     * @return the fieldOptions
     */
    public List<Long> getFieldOptions() {
        return fieldOptions;
    }

    /**
     * @param fieldOptions the fieldOptions to set
     */
    public void setFieldOptions(List<Long> fieldOptions) {
        this.fieldOptions = fieldOptions;
    }
}
