package com.manoelcampos.papersreview.dao;

import com.manoelcampos.papersreview.model.PaperType;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="https://about.me/manoelcampos">Manoel Campos da Silva Filho</a>
 */
public class PaperTypeJpaDAO extends JpaDAO<PaperType> implements PaperTypeDAO {
    public PaperTypeJpaDAO(EntityManager em){
        super(PaperType.class, em);
    }

    @Override
    public List<String> listAsString() {
        final List<String> list = new ArrayList<>();
        list().forEach(t -> list.add(String.format("%s = %s", t.getAbbreviation(), t.getDescription())));
        return list;
    }
}
