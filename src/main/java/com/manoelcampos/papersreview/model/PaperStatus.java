package com.manoelcampos.papersreview.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Manoel Campos da Silva Filho <manoelcampos at gmail.com>
 */
@Entity
@Table(uniqueConstraints={
    @UniqueConstraint(name = "ix_PaperStatus", columnNames = {"reviewPhase_id", "accepted"})
})
public class PaperStatus implements EntityInterface {
    public static final PaperStatus NULL = new PaperStatus(-1);
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @ManyToOne()
    private ReviewPhase reviewPhase;
    
    @NotNull
    private boolean accepted;

    public PaperStatus() {
        this.reviewPhase = new ReviewPhase();
    }

    public PaperStatus(long id) {
        this();
        this.id = id;
    }
    
    @Override
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return (accepted ? "Accepted on " : "Rejected on ") + reviewPhase.getDescription();
    }

    /**
     * @return the reviewPhase
     */
    public ReviewPhase getReviewPhase() {
        return reviewPhase;
    }

    /**
     * @param reviewPhase the reviewPhase to set
     */
    public void setReviewPhase(ReviewPhase reviewPhase) {
        this.reviewPhase = reviewPhase;
    }

    /**
     * @return the accepted
     */
    public boolean isAccepted() {
        return accepted;
    }

    /**
     * @param accepted the accepted to set
     */
    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
    
    public boolean acceptedOnSelectionPhase(){
        return (reviewPhase.getId() == 1 && accepted);
    }
    
    public boolean acceptedOnExtractionPhase(){
        return reviewPhase.getId() == 2 && accepted;
    }
    
    public boolean rejectedOnSelectionPhase(){
        return (reviewPhase.getId() == 1 && !accepted);
    }
    
    public boolean rejectedOnExtractionPhase(){
        return reviewPhase.getId() == 2 && !accepted;
    }
    
    public boolean rejectedOnAnyPhase(){
        return rejectedOnSelectionPhase() || rejectedOnExtractionPhase();
    }
}
