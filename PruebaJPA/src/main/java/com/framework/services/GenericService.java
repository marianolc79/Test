/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.services;

import com.framework.data.BaseDao;
import com.framework.data.MySort;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 *
 * @author mariano
 */
public class GenericService<T extends Object, ID extends Serializable> {

    privateeee BaseDao<T, ID> dao = null;

    public GenericService(String entName, EntityManager em) {
        initDao(entName, em);
    }
        
    private void initDao(String entityName, EntityManager em) {
        dao = new BaseDao<T, ID>(entityName, em);
    }

    public List<T> findNamedQuery(String queryName, MySort[] sort, Object... params) {
        return dao.findNamedQuery(queryName, sort, params);
    }
    
    public Page<T> findAllPage(final int pageNumber, final int pageSize, final String sort, final String sortd) {
        Pageable pageable = new Pageable() {
            public int getPageNumber() {
                return pageNumber;
            }

            public int getPageSize() {
                return pageSize;
            }

            public int getOffset() {
                int offset = 0;
                if (pageNumber > 1) {
                    offset = ((pageNumber - 1) * pageSize);
                }
                return offset;
            }

            public Sort getSort() {
                return new Sort(("desc".equals(sortd) ? Sort.Direction.DESC : Sort.Direction.ASC), sort);
            }
        };
        return dao.findAll(pageable);
    }
}
