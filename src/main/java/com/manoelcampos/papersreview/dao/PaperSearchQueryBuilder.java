package com.manoelcampos.papersreview.dao;

import com.manoelcampos.papersreview.model.EntityInterface;
import com.manoelcampos.papersreview.model.Paper;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Manoel Campos da Silva Filho <manoelcampos at gmail.com>
 */
public class PaperSearchQueryBuilder {
    private final Paper searchCriteria;
    private final StringBuilder jpql;
    private final Map<String, Object> params;
    
    public PaperSearchQueryBuilder(final Paper searchCriteria){
        this.jpql = new StringBuilder("select o from Paper o where 1=1 ");
        this.params = new HashMap<>();
        this.searchCriteria = searchCriteria;
        this.build();
    }

    private PaperSearchQueryBuilder build(){
        addBooleanSearchParam("acceptedOnExtractionPhase", searchCriteria.getAcceptedOnExtractionPhase());
        addBooleanSearchParam("acceptedOnSelectionPhase", searchCriteria.getAcceptedOnSelectionPhase());
        addBooleanSearchParam("survey", searchCriteria.getSurvey());
        
        addStringSearchParam("authors", searchCriteria.getAuthors());
        addStringSearchParam("citationKey", searchCriteria.getCitationKey());
        addStringSearchParam("doi", searchCriteria.getDoi());
        addStringSearchParam("title", searchCriteria.getTitle());
        
        addEntitySearchParam("paperType", searchCriteria.getPaperType());
        addEntitySearchParam("searchSession.project", searchCriteria.getSearchSession().getProject());
        addEntitySearchParam("searchSession.repository", 
            searchCriteria.getSearchSession().getRepository());

        addIntSearchParam("publicationYear", searchCriteria.getPublicationYear());
        
        return this;
    }
    
    private void addStringSearchParam(final String fieldName, final String value){
        if(value != null && !value.trim().isEmpty()) 
            addSearchParam(fieldName, "like", surroundStringValueWithSqlPercentWildcard(value));
    }
    
    private String surroundStringValueWithSqlPercentWildcard(final String value){
        return '%' + value.trim() + '%';
    }

    private void addBooleanSearchParam(final String fieldName, final Integer intBoolValue){
        if(intBoolValue!=null && intBoolValue >= 0) 
            addSearchParam(fieldName, intBoolValue);
    }

    private void addIntSearchParam(final String fieldName, final Integer value){
        if(value > 0) 
            addSearchParam(fieldName, value);
    }

    private void addEntitySearchParam(String fieldName, EntityInterface entity) {
        if(entity != null && entity.getId() > 0) 
            addSearchParam(fieldName, entity);
    }    

    private void addSearchParam(final String fieldName, final Object value) {
        addSearchParam(fieldName, "=", value);
    }
    
    private void addSearchParam(final String fieldName, final String operator, final Object value) {
        jpql.append(
                String.format(" and (o.%s %s :%s) ", 
                        fieldName, operator, createParamName(fieldName)));
        params.put(createParamName(fieldName), value);
    }
    
    private String createParamName(final String paramName){
        return paramName.replaceAll("\\.", "");
    }

    /**
     * @return the jpql
     */
    public String getJpql() {
        return jpql.toString();
    }

    /**
     * @return the params
     */
    public Map<String, Object> getParams() {
        return params;
    }
    
}
