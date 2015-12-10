package com.manoelcampos.papersreview.dto;


import com.manoelcampos.papersreview.model.PaperStatus;
import com.manoelcampos.papersreview.model.PaperType;

import java.io.Serializable;

public class PaperCountByTypeDTO implements Serializable{
    private String paperType;
    private Integer survey;
    private long count;

    public PaperCountByTypeDTO(){
        this(new PaperType(), -1, 0);
    }

    public PaperCountByTypeDTO(final PaperType paperType, final Integer survey, final long count){
        this.setPaperType(paperType);
        this.setCount(count);
        this.setSurvey(survey);
    }

    public String getPaperType() {
        return paperType;
    }

    final private void setPaperType(final PaperType paperType) {
        this.paperType = ( paperType != null  ? paperType.getDescription() : "Not Defined");
    }

    public long getCount() {
        return count;
    }

    final private void setCount(final long count) {
        this.count = count;
    }

    public Integer getSurvey(){ return survey; }

    public String getSurveyStr() {
        return (survey == null || survey < 0 ? "Not Defined" : (survey == 1 ? "Yes" : "No"));
    }

    final private void setSurvey(Integer survey) {
        this.survey = survey;
    }
}
