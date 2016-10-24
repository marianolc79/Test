/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.services;

import com.framework.entities.Cliente;
import java.util.List;
import org.springframework.data.domain.Page;

/**
 *
 * @author mariano
 */
public interface ServicioClientes {
    public List<Cliente> findAllClientes();
    public Page<Cliente> findClientesPage(final int pageNumber, final int pageSize, final String sort, final String sortd);
}
