package com.manoelcampos.papersreview.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
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

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Manoel Campos da Silva Filho <manoelcampos at gmail.com>
 */
@Entity
@Table(uniqueConstraints={
    @UniqueConstraint(name = "ix_PaperTitle", columnNames = {"searchSession_id", "title"}),
    @UniqueConstraint(name = "ix_PaperCitationKey", columnNames = {"searchSession_id", "citationKey"})
})
public class Paper implements EntityInterface {
    public static Paper NULL = new Paper(-1);
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull @NotEmpty @Size(max = 240)
    private String title;

    @Size(min = 0, max = 100)
    private String proposalName;

    @NotNull @NotEmpty @Size(max = 400)
    private String authors;

    @Size(max = 100)
    private String bibliographicAuthorNames;

    @NotNull
    private int publicationYear;

    @Size(max = 120)
    private String doi;

    @Size(max = 300)
    private String url;

    @NotNull @NotEmpty @Size(max = 50)
    private String citationKey;

    @ManyToOne(optional = true)
    private PaperType paperType;
    
    @NotNull @ManyToOne(optional = false)
    private SearchSession searchSession;

    @Column(nullable = true)
    private Integer survey;

    @ManyToOne(optional = true)
    private PaperStatus status;
    
    @Column(nullable = true)
    private String paperAbstract;   
    
    @Column(nullable = true)
    private String notes;

    @OrderBy(value = "field")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paper", fetch = FetchType.EAGER)
    private final List<PaperFieldAnswer> paperFieldAnswers;

    public Paper() {
        this.paperType = new PaperType();
        this.searchSession = new SearchSession();
        this.status = new PaperStatus();
        this.paperFieldAnswers = new ArrayList<>();
    }

    public Paper(long id) {
        this();
        this.id = id;
    }

    public Paper(Long id, String title, int year, String citationKey, PaperType paperType, Integer survey) {
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

    public int yearsSincePublication() {
        return LocalDate.now().minusYears(publicationYear).getYear();
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
        if(paperType != null && (paperType.getId() == null || paperType.getId()<=0))
            paperType = null;
        this.paperType = paperType;
    }

    public Integer getSurvey() {
        return survey;
    }

    public void setSurvey(Integer survey) {
        if(survey < 0)
            survey = null;
        this.survey = survey;
    }

    public List<PaperFieldAnswer> getPaperFieldAnswers() {
        return paperFieldAnswers;
    }

    private List<PaperFieldAnswer> getPaperFieldAnswersInternal(final Field field) {
        return paperFieldAnswers.stream().filter(
                a -> a.getField().getId().equals(field.getId()))
                .collect(Collectors.toList());
    }

    public List<String> getPaperFieldAnswers(final Field field) {
        return getPaperFieldAnswers(field, false);
    }

    public List<String> getPaperFieldAnswers(final Field field, boolean abbreviated) {
        List<String> result = new ArrayList<>();
        getPaperFieldAnswersInternal(field).forEach(answer -> result.add(answer.getAnswer(abbreviated)));
        return result;
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
        if(searchSession != null && searchSession.getId()!=null)
            s=searchSession.getId().toString();
        return String.format("%s[id=%d, searchSession=%s, title=%s]", 
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
     * @return the searchSession
     */
    public SearchSession getSearchSession() {
        return searchSession;
    }

    /**
     * @param searchSession the searchSession to set
     */
    public void setSearchSession(SearchSession searchSession) {
        this.searchSession = searchSession;
    }

    /**
     * @return the doi
     */
    public String getDoi() {
        return doi;
    }
    
    public String getDoiLink(){
        if(doi == null)
            return "";
        
        String link = doi.trim();
        if(link.toLowerCase(Locale.getDefault()).startsWith("http://"))
            link = link.substring(7);

        if(!link.toLowerCase(Locale.getDefault()).startsWith("dx.doi.org/"))
            link = String.format("dx.doi.org/%s", link);

        if(!link.toLowerCase(Locale.getDefault()).startsWith("http://"))
            link = String.format("http://%s", link);
        
        return link;
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
     * @return the paperAbstract
     */
    public String getPaperAbstract() {
        return paperAbstract;
    }

    /**
     * @param paperAbstract the paperAbstract to set
     */
    public void setPaperAbstract(String paperAbstract) {
        this.paperAbstract = paperAbstract;
    }

    /**
     * @return the status
     */
    public PaperStatus getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(PaperStatus status) {
        if(status != null && (status.getId() == null || status.getId()<=0))
            status = null;

        this.status = status;
    }

    /**
     * @return the notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * @param notes the notes to set
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }


    public String getProposalName() {
        return proposalName;
    }

    public void setProposalName(String proposalName) {
        this.proposalName = proposalName;
    }

    public String getBibliographicAuthorNames() {
        if(StringUtils.isEmpty(bibliographicAuthorNames)){
            return "";
        }

        return  bibliographicAuthorNames.endsWith(".") ? bibliographicAuthorNames : bibliographicAuthorNames + ".";
    }

    public String getBibliographicAuthorNamesAndYear() {
        return getBibliographicAuthorNames().isEmpty() ? "" : String.format("%s (%d)", getBibliographicAuthorNames(), publicationYear);
    }


    public void setBibliographicAuthorNames(String bibliographicAuthorNames) {
        this.bibliographicAuthorNames = bibliographicAuthorNames;
    }
}
