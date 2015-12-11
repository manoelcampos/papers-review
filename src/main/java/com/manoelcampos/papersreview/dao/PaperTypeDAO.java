package com.manoelcampos.papersreview.dao;

import com.manoelcampos.papersreview.model.PaperType;

import java.util.List;

/**
 * @author <a href="https://about.me/manoelcampos">Manoel Campos da Silva Filho</a>
 */
public interface PaperTypeDAO extends DAO<PaperType> {
    List<String> listAsString();
}
