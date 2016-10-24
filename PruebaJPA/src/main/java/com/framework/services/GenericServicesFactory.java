/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mariano
 */
public class GenericServicesFactory {
   
    private @PersistenceContext EntityManager em;
    
    public GenericService getServiceForEntity(String entity) {
        GenericService serv = new GenericService(entity, em);
        return serv;
    } 
            
    
}
