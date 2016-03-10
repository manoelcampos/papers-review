package com.manoelcampos.papersreview.model;

/**
 * @author <a href="https://about.me/manoelcampos">Manoel Campos da Silva Filho</a>
 */
public class Util {
    public static final String getNotNullString(String str){
        return (str == null ? "" : str);
    }

    public static final String firstNotEmpty(String value1, String value2){
        value1 = getNotNullString(value1);
        value2 = getNotNullString(value2);

        return (value1.trim().isEmpty() ? value2 : value1);
    }

    public static final String getDescription(String description, String abbreviation, boolean abbreviated){
        return (abbreviated ? firstNotEmpty(abbreviation, description) : firstNotEmpty(description, abbreviation));
    }
}
