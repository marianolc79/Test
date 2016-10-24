/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.services;

import com.framework.business.BaseService;
import com.framework.dao.ClienteDao;
import com.framework.entities.Cliente;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

/**
 *
 * @author mariano
 */
@Service("servicioClientes")
public class ServicioClientesImpl extends BaseService implements ServicioClientes {

    private static final Logger logger = Logger.getLogger(ServicioClientesImpl.class);
    @Autowired(required = false)
    private ClienteDao clienteDao;

    public List<Cliente> findAllClientes() {
        return clienteDao.findAll();
    }

    public long clientesCount() {
        return clienteDao.count();
    }

    public Page<Cliente> findClientesPage(final int pageNumber, final int pageSize, final String sort, final String sortd) {
        Pageable pageable = new Pageable() {
            
        	public int getPageNumber() {
                return pageNumber;
            }

            public int getPageSize() {
                return pageSize;
            }

            public int getOffset() {
                int offset = 0;
                if(pageNumber > 1) {
                    offset = ((pageNumber - 1)*pageSize);
                }
                return offset;
            }

            public Sort getSort() {
                return new Sort(("desc".equals(sortd)?Direction.DESC:Direction.ASC), sort);
            }
        };
        return clienteDao.findAll(pageable);
    }
}
