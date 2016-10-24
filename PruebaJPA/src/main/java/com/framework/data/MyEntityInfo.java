/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.data;

import java.util.HashMap;

/**
 *
 * @author mariano
 */
public class MyEntityInfo {

   
    private String type = null;

    private String id = null;
    private HashMap<String, MyQuery> mapping = new HashMap<String, MyQuery>();

  
    public MyEntityInfo(String id) {
        this.id = id;
    }
    public MyEntityInfo() {
    }
    public HashMap<String, MyQuery> getMapping() {
        return mapping;
    }
   
    
    public void setMapping(HashMap<String, MyQuery> mapping) {
        this.mapping = mapping;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
  
   
}
