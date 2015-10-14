package com.manoelcampos.papersreview.dao;

import java.lang.reflect.ParameterizedType;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * Define como objetos DAO devem ser instanciados,
 * passando todos os parâmetros necessários para instanciar
 * um quando for solicitada a injeção de tais objetos 
 * em alguma parte do código do projeto.
 * @author manoelcampos
 */
public class DaoProducer {
    @Inject
    /**
     * EntityManager a ser utilizado pelo DAO para persistir o objeto
     * de negócio.
     */
    private EntityManager em;

    /**
     * Instancia um objeto DAO por meio de injeção de dependência CDI.
     * @param <T> Classe do objeto de negócio a ser manipulado pelo DAO a ser 
     * instanciado por injeção
     * @param injectionPoint Parâmetros passados (no ponto de injeção) 
     * ao objeto sendo injetado, como tipos genéricos a serem associados 
     * à instância criada.
     * @return Uma instância do DAO solicitado no ponto de injeção.
     */
    @Produces
    public <T extends Object> DAO<T> create(InjectionPoint injectionPoint) {
        ParameterizedType type = (ParameterizedType) injectionPoint.getType();
        Class classe = (Class) type.getActualTypeArguments()[0];

        return new JpaDAO<>(classe, em);
    }
    
    @Produces
    public FieldOptionDAO createFieldOptionDAO() {
        return new FieldOptionJpaDAO(em);
    }
    
    @Produces
    public SearchSectionDAO createSearchSectionDAO() {
        return new SearchSectionJpaDAO(em);
    }
    
    @Produces
    public PaperDAO createPaperDao() {
        return new PaperJpaDAO(em);
    }
    
}