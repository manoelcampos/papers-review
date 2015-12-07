package com.manoelcampos.papersreview.dto;


import com.manoelcampos.papersreview.model.Paper;

import java.util.ArrayList;
import java.util.List;

public class PaperCountByStatusDTO {
    private String status;
    private Integer count;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

}
