package com.manoelcampos.papersreview.controller;

import com.manoelcampos.papersreview.model.EntityClass;
import java.util.List;
import javax.validation.Valid;

/**
 * Basic interface for CRUD (Create, Read, Update, Delete) controllers.
 * @author Manoel Campos da Silva Filho <manoelcampos at gmail.com>
 * @param <T>
 */
public interface CrudController <T extends EntityClass> {
    public List<T> index();
    public void edit(final Long id);
    public void remove(final Long id);
    public void save(@Valid T o);
}
