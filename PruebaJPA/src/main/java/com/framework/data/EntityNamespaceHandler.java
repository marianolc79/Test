/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.data;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 *
 * @author mariano
 */
public class EntityNamespaceHandler  extends NamespaceHandlerSupport {
    public void init() {
        registerBeanDefinitionParser("entity", new EntityBeanDefinitionParser());        
    }
}
