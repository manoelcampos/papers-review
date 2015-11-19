package com.manoelcampos.papersreview.service;

import com.manoelcampos.papersreview.dao.DAO;
import com.manoelcampos.papersreview.dao.FieldDAO;
import com.manoelcampos.papersreview.dao.ProjectDAO;
import com.manoelcampos.papersreview.model.FieldType;
import com.manoelcampos.papersreview.model.Field;
import com.manoelcampos.papersreview.model.Project;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

/**
 *
 * @author Manoel Campos da Silva Filho <manoelcampos at gmail.com>
 */
@Dependent
public class FieldService {
    @Inject
    private FieldDAO dao;
    
    @Inject
    private DAO<FieldType> fieldTypeDao;

    @Inject
    private ProjectDAO projectDao;

    /**
     * @return the dao
     */
    public List<Field> list() {
        return dao.list();
    }
    
    public boolean remove(final Field field){
        return dao.remove(field);
    }

    public boolean save(final Field o){
        o.setFieldType(fieldTypeDao.findById(o.getFieldType().getId()));
        o.setProject(projectDao.findById(o.getProject().getId()));
        return dao.save(o);
    }
    
    public Field findById(final Long id){
        return dao.findById(id);
    }
    
    public List<FieldType> listFieldTypes(){
        return fieldTypeDao.list();
    }
    
    public List<Field> listByProject(final Long projectId){
        return dao.listByProject(projectId);
    }
}
