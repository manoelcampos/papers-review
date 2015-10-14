package com.manoelcampos.papersreview.dao;

import java.util.List;


/**
 * Interface padrão a ser implementada por DAO's genéricos.
 * @author manoelcampos
 * @param <T> Objeto de negócio a ser manipulado
 * pela classe DAO
 */
public interface DAO<T extends Object> {
    public boolean save(T o);
    public boolean remove(T o);
    public List list();
    public T findById(Long id);
}
