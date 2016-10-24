/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.data;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.PersistenceExceptionTranslator;

/**
 * Realiza la traducción de la excepción del proveedor de datos subyacente en una excepción
 * definida DataException
 * @author mariano
 */
public class DataExceptionTranslator implements PersistenceExceptionTranslator {

    public DataAccessException translateExceptionIfPossible(RuntimeException ex) {
        throw new DataException(ex);
    }
    
}
