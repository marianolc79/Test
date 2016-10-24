/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.data;

import org.springframework.dao.DataAccessException;

/**
 *
 * @author mariano
 */
public class DataException extends DataAccessException {
    public DataException(String str) {
        super(str);
    }
    public DataException(Throwable ex) {
        super(null, ex);
    }
    public DataException(String str, Throwable ex) {
        super(str,ex);
    }
}
