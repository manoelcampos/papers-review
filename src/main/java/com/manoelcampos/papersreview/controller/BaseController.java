package com.manoelcampos.papersreview.controller;

import br.com.caelum.vraptor.Result;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Manoel Campos da Silva Filho <manoelcampos at gmail.com>
 */
public class BaseController {
    @Inject
    private HttpServletRequest request;
    
    @Inject 
    protected Result result;
    
    public String getURL() {  
        String url = getContextPath() + request.getRequestURI();
                        
        if(request.getQueryString() != null)
            url += '?'+request.getQueryString();
        return url;
    }   

    private String getContextPath() {
        return "http://" + request.getHeader("Host") + 
                request.getContextPath();
    }
    
    public void includeRequestUrlInView(){
        result.include("url", getURL());
    }

    public void includeReturnUrlInView(String returnUrl){
        if(returnUrl != null && !returnUrl.trim().equals(""))
            result.include("returnUrl", returnUrl);
        else includeReturnToHomePage();
    }

    public void redirectTo(String url){
        if(url != null && !url.trim().equals(""))
            result.redirectTo(url);
        else result.redirectTo(getContextPath());
    }

    private void includeReturnToHomePage() {
       result.include("returnUrl", getContextPath());
    }
}
