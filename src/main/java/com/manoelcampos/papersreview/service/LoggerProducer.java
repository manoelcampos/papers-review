package com.manoelcampos.papersreview.service;

import java.util.logging.Logger;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 *
 * @author Manoel Campos da Silva Filho <manoelcampos at gmail.com>
 */
public class LoggerProducer {
   /**
    * @param injectionPoint
    * @return logger
    */
    @Produces
    public Logger produceLogger(InjectionPoint injectionPoint) {
	return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }
}
