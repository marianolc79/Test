/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.business;

import com.framework.services.ServicioClientesImpl;
import org.apache.log4j.Logger;

/**
 * Clase base de soporte de servicios
 * @author mariano
 */
public class BaseService {
     private static final Logger logger = Logger.getLogger(ServicioClientesImpl.class);

     public BaseService() {
         logger.debug("Creando "+this.getClass().getName());
     }
}
