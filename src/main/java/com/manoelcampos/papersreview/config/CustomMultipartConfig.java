package com.manoelcampos.papersreview.config;

import br.com.caelum.vraptor.observer.upload.DefaultMultipartConfig;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;

/**
 *
 * @author Manoel Campos da Silva Filho <manoelcampos at gmail.com>
 */
@Specializes
@ApplicationScoped
public class CustomMultipartConfig extends DefaultMultipartConfig {
    // alteramos o tamanho total do upload para 10MB
    @Override
    public long getSizeLimit() {
        return 5 * 1024 * 1024;
    }

    // alteramos o tamanho do upload de cada arquivo para 5MB
    @Override
    public long getFileSizeLimit() {
        return 20 * 1024 * 1024;
    }
}
