package com.manoelcampos.papersreview.model;

import java.io.Serializable;

/**
 *
 * @author Manoel Campos da Silva Filho <manoelcampos at gmail.com>
 */
public interface EntityInterface extends Serializable {
     Long getId();
     void setId(Long id);
     static boolean isNull(EntityInterface obj){
         return obj == null || obj.getId() <= 0;
     }
}
