package com.manoelcampos.papersreview.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
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
    @UniqueConstraint(name = "ix_PaperTitle", columnNames = {"searchSection_id", "title"}),
    @UniqueConstraint(name = "ix_PaperCitationKey", columnNames = {"searchSection_id", "citationKey"})
})
public class Paper implements EntityInterface {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 1, max = 120)
    private String title;
    
    @NotNull @NotEmpty
    @Size(max = 400)
    private String authors;
    
    @NotNull
    private int publicationYear;

    @Size(max = 50)
    private String doi;

    @Size(max = 200)
    private String url;

    @NotNull @NotEmpty
    @Size(max = 50)
    private String citationKey;

    @ManyToOne(optional = true)
    private PaperType paperType;
    
    @NotNull
    @ManyToOne(optional = false)
    private SearchSection searchSection;

    @NotNull
    private boolean survey;

    @Column(nullable = true)
    private boolean acceptedOnSelectionPhase;
    @Column(nullable = true)
    private boolean acceptedOnExtractionPhase;

    @OrderBy(value = "field")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paper", fetch = FetchType.EAGER)
    final private List<PaperFieldAnswer> paperFieldAnswers = new ArrayList<>();

    public Paper() {
        this.searchSection = new SearchSection();
    }

    public Paper(Long id) {
        this();
        this.id = id;
    }

    public Paper(Long id, String title, int year, String citationKey, PaperType paperType, boolean survey) {
        this(id);
        this.title = title;
        this.publicationYear = year;
        this.citationKey = citationKey;
        this.paperType = paperType;
        this.survey = survey;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getCitationKey() {
        return citationKey;
    }

    public void setCitationKey(String citationKey) {
        this.citationKey = citationKey;
    }

    public PaperType getPaperType() {
        return paperType;
    }

    public void setPaperType(PaperType paperType) {
        this.paperType = paperType;
    }

    public boolean getSurvey() {
        return survey;
    }

    public void setSurvey(boolean survey) {
        this.survey = survey;
    }

    public List<PaperFieldAnswer> getPaperFieldAnswers() {
        return paperFieldAnswers;
    }

    public void clearPaperFieldAnswers() {
        paperFieldAnswers.clear();
    }
    
    public PaperFieldAnswer removePaperFieldAnswer(final PaperFieldAnswer paperFieldAnswer) {        
        this.paperFieldAnswers.remove(paperFieldAnswer);
        return paperFieldAnswer;
    }
    
    
    public void addPaperFieldAnswer(final PaperFieldAnswer paperFieldAnswer) {        
        this.paperFieldAnswers.add(paperFieldAnswer);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Paper)) {
            return false;
        }
        Paper other = (Paper) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        String s = "";
        if(searchSection != null)
            s=searchSection.getId().toString();
        return String.format("%s[id=%d, searchSection=%s, title=%s]", 
                getClass().getSimpleName(), id, 
                s, title);
    }

    /**
     * @return the authors
     */
    public String getAuthors() {
        return authors;
    }

    /**
     * @param authors the authors to set
     */
    public void setAuthors(String authors) {
        this.authors = authors;
    }

    /**
     * @return the acceptedOnSelectionPhase
     */
    public boolean isAcceptedOnSelectionPhase() {
        return acceptedOnSelectionPhase;
    }

    /**
     * @param acceptedOnSelectionPhase the acceptedOnSelectionPhase to set
     */
    public void setAcceptedOnSelectionPhase(boolean acceptedOnSelectionPhase) {
        this.acceptedOnSelectionPhase = acceptedOnSelectionPhase;
    }

    /**
     * @return the acceptedOnExtractionPhase
     */
    public boolean isAcceptedOnExtractionPhase() {
        return acceptedOnExtractionPhase;
    }

    /**
     * @param acceptedOnExtractionPhase the acceptedOnExtractionPhase to set
     */
    public void setAcceptedOnExtractionPhase(boolean acceptedOnExtractionPhase) {
        this.acceptedOnExtractionPhase = acceptedOnExtractionPhase;
    }

    /**
     * @return the searchSection
     */
    public SearchSection getSearchSection() {
        return searchSection;
    }

    /**
     * @param searchSection the searchSection to set
     */
    public void setSearchSection(SearchSection searchSection) {
        this.searchSection = searchSection;
    }

    /**
     * @return the doi
     */
    public String getDoi() {
        return doi;
    }

    /**
     * @param doi the doi to set
     */
    public void setDoi(String doi) {
        this.doi = doi;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the survey
     */
    public boolean isSurvey() {
        return survey;
    }
    
}
