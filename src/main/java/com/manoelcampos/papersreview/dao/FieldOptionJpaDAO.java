package com.manoelcampos.papersreview.dao;

import com.manoelcampos.papersreview.model.Field;
import com.manoelcampos.papersreview.model.FieldOption;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Manoel Campos da Silva Filho <manoelcampos at gmail.com>
 */
public class FieldOptionJpaDAO extends JpaDAO<FieldOption> implements FieldOptionDAO {

    public FieldOptionJpaDAO(EntityManager em) {
        super(FieldOption.class, em);
    }
    
    @Override
    public List<FieldOption> listByField(final Field field){
        final String jpql = 
                String.format("select o from %s o where o.field = :field", getGenericClassName());
        final TypedQuery<FieldOption> qry = createQuery(jpql);
        qry.setParameter("field", field);
        return qry.getResultList();
    }

    @Override
    public boolean save(FieldOption o) {
        if(o.getParentFieldOption() == FieldOption.NULL){
            o.setParentFieldOption(null);
        }
        return super.save(o);
    }
}
