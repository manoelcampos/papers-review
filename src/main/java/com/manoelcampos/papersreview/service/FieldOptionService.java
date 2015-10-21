package com.manoelcampos.papersreview.service;

import com.manoelcampos.papersreview.dao.FieldDAO;
import com.manoelcampos.papersreview.dao.FieldOptionDAO;
import com.manoelcampos.papersreview.model.Field;
import com.manoelcampos.papersreview.model.FieldOption;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Manoel Campos da Silva Filho <manoelcampos at gmail.com>
 */
@Dependent
public class FieldOptionService {
    @Inject
    private FieldOptionDAO dao;
    
    @Inject
    private FieldDAO fieldDao;

    /**
     * @param fieldId
     * @return 
     */
    public List<FieldOption> list(final @NotNull Long fieldId) {
        return dao.listByField(fieldId);
    }
    
    public FieldOption remove(final Long id){
        FieldOption f = dao.findById(id);
        return (dao.remove(f) ? f : null);
    }

    public boolean save(final FieldOption o){
        o.setField(fieldDao.findById(o.getField().getId()));
        return dao.save(o);
    }
    
    public FieldOption findById(final Long id){
        return dao.findById(id);
    }    
    
    public Field findFieldById(final Long id){
        return fieldDao.findById(id);
    }    
}
