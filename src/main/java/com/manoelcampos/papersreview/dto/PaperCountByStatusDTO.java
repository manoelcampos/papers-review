package com.manoelcampos.papersreview.dto;


import com.manoelcampos.papersreview.model.PaperStatus;

import java.io.Serializable;

public class PaperCountByStatusDTO implements Serializable{
    private PaperStatus status;
    private long count;

    public PaperCountByStatusDTO(){
        this(new PaperStatus(), 0);
    }

    public PaperCountByStatusDTO(final PaperStatus status, final long count) {
        this.setStatus(status);
        this.setCount(count);
    }

    public String getStatus() {
        return ( status != null  ? status.toString() : "Not Classified");
    }

    public long getCount() {
        return count;
    }

    final private void setCount(final long count) {
        this.count = count;
    }

    final private void setStatus(final PaperStatus status) {
        this.status = status;
    }

    public String getAcceptedStr(){
        return (status == null ? "Not Defined" : (status.isAccepted() ? "Accepted" : "Rejected"));
    }

    public Integer getAccepted(){
        return (status == null ? -1 : (status.isAccepted() ? 1 : 0));
    }
}
