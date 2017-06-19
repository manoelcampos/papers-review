package com.manoelcampos.papersreview.service;

import com.manoelcampos.papersreview.dao.FieldDAO;
import com.manoelcampos.papersreview.dao.FieldOptionDAO;
import com.manoelcampos.papersreview.model.EntityInterface;
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
     * @param field
     * @return 
     */
    public List<FieldOption> list(final @NotNull Field field) {
        return dao.listByField(field);
    }
    
    public boolean remove(final FieldOption fieldOption){
        return dao.remove(fieldOption) ;
    }

    public boolean save(final FieldOption o){
        o.setField(fieldDao.findById(o.getField().getId()));
        if(!EntityInterface.isNull(o.getParentFieldOption())) {
            o.setParentFieldOption(findById(o.getParentFieldOption().getId()));
        }
        return dao.save(o);
    }
    
    public FieldOption findById(final Long id){
        return dao.findById(id);
    }    
    
    public Field findFieldById(final Long id){
        return fieldDao.findById(id);
    }    
}
