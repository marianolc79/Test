/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.data;

import java.io.Serializable;
import javax.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

/**
 *
 * @author mariano
 */
public class DaoFactoryBean<R extends JpaRepository<T, I>, T, I extends Serializable>
        extends JpaRepositoryFactoryBean<R, T, I> {

    public DaoFactoryBean() {
        System.out.println("Creando factory bean...");
    }
    protected RepositoryFactorySupport createDaoFactory(EntityManager entityManager) {
    System.out.println("Creando factoria...");
        return new DaoFactory(entityManager);
    }

    private static class DaoFactory<T, I extends Serializable> extends JpaRepositoryFactory {

        private EntityManager entityManager;

        public DaoFactory(EntityManager entityManager) {
            super(entityManager);

            this.entityManager = entityManager;
        }

        protected Object getTargetRepository(RepositoryMetadata metadata) {
//            System.out.println("Creando repositorio "+metadata.getRepositoryInterface().getName());
//            JpaEntityInformation info = JpaEntityInformationSupport.getMetadata((Class<T>) metadata.getDomainType(), entityManager);
//            return new BaseDaoImpl<T, I>(info, entityManager);
            return null;
        }

        protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
            System.out.println("Obteniendo metadatos...");
            // The RepositoryMetadata can be safely ignored, it is used by the JpaRepositoryFactory
            //to check for QueryDslJpaRepository's which is out of scope.
            return BaseDao.class;
        }
    }
}