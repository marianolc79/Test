/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.dao;

import com.framework.data.BaseDao;
import com.framework.entities.Cliente;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mariano
 */
@Repository("clienteDao")
public class ClienteDao extends BaseDao<Cliente, Integer> {
    
}