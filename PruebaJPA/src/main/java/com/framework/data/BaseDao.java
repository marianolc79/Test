/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.data;

import com.framework.ApplicationContextProvider;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 *
 * @author mariano
 */
@NoRepositoryBean
public class BaseDao<T extends Object, ID extends Serializable> {
    //implements JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

    public BaseDao() {
    }
    private @PersistenceContext
    EntityManager em;
    private SimpleJpaRepository<T, ID> target;

    protected EntityManager getEntityManager() {
        return em;
    }

    public long count() {
        return target.count();
    }

    public long count(Specification<T> s) {
        return target.count(s);
    }

    public void delete(T t) {
        target.delete(t);
    }

    public void delete(Iterable<? extends T> itrbl) {
        target.delete(itrbl);
    }

    public void deleteAll() {
        target.deleteAll();
    }

    public void deleteInBatch(Iterable<T> itrbl) {
        target.deleteInBatch(itrbl);
    }

    public boolean exists(ID id) {
        return target.exists(id);
    }

    public List<T> findAll() {
        return target.findAll();
    }

    public List<T> findAll(Sort sort) {
        return target.findAll(sort);
    }

    public List<T> findAll(Iterable<ID> ids) {
        return target.findAll(ids);
    }

    public List<T> findAll(Specification<T> s) {
        return target.findAll(s);
    }

    public Page<T> findAll(Specification<T> spec, org.springframework.data.domain.Pageable pageable) {
        return target.findAll(spec, pageable);
    }

    public T findOne(ID id) {
        return target.findOne(id);
    }

    public T findOne(Specification<T> s) {
        return target.findOne(s);
    }
//    public List<T> findQueryParameters(String name, Sort sort, Pageable pageable, Object ... params) {
//        
//    }
    private Class<T> type = null;

    private String getNamedQueryString(String queryName) {
        return getEntityInfo().getMapping().get(queryName).getQueryText();
    }

    private String getQueryOrderText(String queryName, String id) {
        return getEntityInfo().getMapping().get(queryName).getOrderColumnText(id);
    }

    public List<T> findNamedQuery(String queryName, Object... params) {
        return findNamedQuery(queryName, null, params);
    }
    
    public List<T> findNamedQuery(String queryName, MySort[] sort, Object... params) {
        String queryText = getNamedQueryString(queryName);

        if (sort != null && sort.length != 0) {
            queryText = queryText + " order by ";
            StringBuilder orderSb = new StringBuilder();
            for (MySort s: sort) {
                String id = s.getId();
                String colText = getQueryOrderText(queryName, id);
                if(colText != null) {
                    if(orderSb.length() > 0) {
                        orderSb.append(", ");
                    }
                    orderSb.append(colText+(s.getDirection()==1?" asc":" desc"));
                } else {
                    throw new RuntimeException("No se ha definido ordenación para la consulta "+queryName);
                }
            }
            queryText += orderSb.toString();
        }
        Query query = em.createQuery(queryText);
        int pos = 1;
        for (Object obj : params) {
            query.setParameter(pos++, obj);
        }
        return query.getResultList();
    }

    public void flush() {
        target.flush();
    }

    @PostConstruct
    void init() {
        // this is needed to retrieve the Class instance associated with the generic definition T
        ParameterizedType superclass = (ParameterizedType) getClass().getGenericSuperclass();
        Class<T> type = (Class<T>) superclass.getActualTypeArguments()[0];
        target = new SimpleJpaRepository<T, ID>(type, em);
        System.out.println("Creando dao de tipo " + type.getName());
    }

    private void initQueries() {
        ApplicationContext ctx = ApplicationContextProvider.getApplicationContext();
        if (ctx.getBean(type.getCanonicalName(), MyEntityInfo.class) != null) {
            info = (MyEntityInfo) ctx.getBean(type.getCanonicalName(), MyEntityInfo.class);
            System.out.println("Cargado fichero de mapeo " + type.getName());
        }
    }
    private MyEntityInfo info = null;
    
    private MyEntityInfo getEntityInfo() {
        if(info == null) initQueries();
        return info;
    }
    
    public BaseDao(String type, EntityManager em) {
        initEntity(type, em);
    }

    public BaseDao(Class type, EntityManager em) {
        initEntity(type, em);
    }

    public void initEntity(String type, EntityManager em) {
        try {
            Class c = Class.forName(type);
            this.initEntity(c, em);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void initEntity(Class type, EntityManager em) {
        this.type = type;
        target = new SimpleJpaRepository<T, ID>(type, em);
        this.em = em;
        System.out.println("Creando dao de tipo " + type.getName());
    }

    public <S extends T> S save(S entity) {
        return target.save(entity);
    }

    public <S extends T> List<S> save(Iterable<S> entities) {
        return target.save(entities);
    }

    public T saveAndFlush(T t) {
        return target.saveAndFlush(t);
    }

    public void deleteAllInBatch() {
        target.deleteAllInBatch();
    }

    public Page<T> findAll(Pageable pageable) {
        return target.findAll(pageable);
    }

    public void delete(ID id) {
        target.delete(id);
    }

    public List<T> findAll(Specification<T> spec, Sort sort) {
        return target.findAll(spec, sort);
    }
}