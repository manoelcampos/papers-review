package com.manoelcampos.papersreview.dto;


import com.manoelcampos.papersreview.model.PaperStatus;

import java.io.Serializable;

public class PaperCountByRepositoryDTO implements Serializable{
    private String repository;
    private long count;

    public PaperCountByRepositoryDTO(){
        this("", 0);
    }

    public PaperCountByRepositoryDTO(final String repository, final long count){
        this.repository = repository;
        this.count = count;
    }

    public String getRepository() {
        return repository;
    }

    public void setRepository(String repository) {
        this.repository = repository;
    }

    public long getCount() {
        return count;
    }

    public void setCount(final long count) {
        this.count = count;
    }

}
