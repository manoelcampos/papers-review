package com.manoelcampos.papersreview.dto;


import com.manoelcampos.papersreview.model.FieldOption;

import java.io.Serializable;

public class PaperCountByFieldOptionDTO implements Serializable{
    private FieldOption fieldOption;
    private Long count;

    public PaperCountByFieldOptionDTO(){
        this(new FieldOption(), 0L);
    }

    public PaperCountByFieldOptionDTO(final FieldOption fieldOption, final Long count){
        this.fieldOption = fieldOption;
        this.count = count;
    }

    public FieldOption getFieldOption() {
        return fieldOption;
    }

    public Long getCount() {
        return count;
    }

}
