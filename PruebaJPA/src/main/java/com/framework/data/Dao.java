/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.data;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 *
 * @author mariano
 */
@NoRepositoryBean
public interface Dao<T, ID extends Serializable>  extends JpaRepository<T, ID> {
    //public void mydelete(ID p);
}
