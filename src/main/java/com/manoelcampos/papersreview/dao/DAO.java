package com.manoelcampos.papersreview.dao;

import java.util.List;


/**
 * Interface padrão a ser implementada por DAO's genéricos.
 * @author manoelcampos
 * @param <T> Objeto de negócio a ser manipulado
 * pela classe DAO
 */
public interface DAO<T extends Object> {
    boolean save(T o);
    boolean remove(T o);
    List<T> list();
    T findById(Long id);
    void flush();
    boolean saveWithoutFlush(T o);
}
