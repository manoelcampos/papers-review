package com.manoelcampos.papersreview.config;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

/**
 * Define como objetos EntityManager devem ser instanciados,
 * passando todos os parâmetros necessários para instanciar
 * um quando for solicitada a injeção de tais objetos
 * em alguma parte do projeto.
 * 
 * Referências: 
 * http://blog.caelum.com.br/customizando-a-producao-de-dependencias-no-cdi/
 * http://www.byteslounge.com/subcategory/cdi
 * @author manoelcampos
 */
@ApplicationScoped
public class EntityManagerProducer {
    //Desabilitado pois agora está usando o plugin vraptor-jpa que já faz isso tudo
    /*
    @Produces
    @PersistenceUnit(unitName = "default")
    private EntityManagerFactory entityManagerFactory;

   
    @Produces
    @PersistenceContext()
    private EntityManager em;  */      
}
