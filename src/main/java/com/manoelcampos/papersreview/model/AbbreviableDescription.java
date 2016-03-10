package com.manoelcampos.papersreview.model;

/**
 * @author <a href="https://about.me/manoelcampos">Manoel Campos da Silva Filho</a>
 */
public interface AbbreviableDescription {
    String getDescription();
    void setDescription(String description);
    String getAbbreviation();
    void setAbbreviation(String abbreviation);
    String getDescription(boolean abbreviated);
}
