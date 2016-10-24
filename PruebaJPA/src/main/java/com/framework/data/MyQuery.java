/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.data;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mariano
 */
public class MyQuery {
    enum Type {
        NATIVE,
        JPQL
    }
    
    public class MyColumnOrder {
        private String id = null;
        private String columnText = null;

        public MyColumnOrder(String id, String columnText) {
            this.id = id;
            this.columnText = columnText;
        }

        public String getId() {
            return id;
        }

        public String getColumnText() {
            return columnText;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setColumnText(String columnText) {
            this.columnText = columnText;
        }
        
        public boolean equals(Object obj) {
            if(! (obj instanceof MyColumnOrder)) {
                return false;
            }
            return getId().equals(((MyColumnOrder)obj).getId());
        }
        
    }
     
    private List<MyColumnOrder> queryOrderMapping = new ArrayList<MyColumnOrder>();
    
    private Type queryType = Type.JPQL;
    private String queryText = null;
    private String id = null;
    
    public MyQuery(String id) {
        this.id = id;
    }
    
    public Type getQueryType() {
        return queryType;
    }

    public String getQueryText() {
        return queryText;
    }

    public void setQueryType(Type queryType) {
        this.queryType = queryType;
    }

    public void setQueryText(String queryText) {
        this.queryText = queryText;
    }

   
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<MyColumnOrder> getQueryOrderMapping() {
        return queryOrderMapping;
    }

    public String getOrderColumnText(String id) {
        String text = null;
        for(MyColumnOrder col: queryOrderMapping) {
            if(col.getId().equals(id)) {
                text = col.getColumnText();
                break;
            }
        }
        return text;
    }
    
    public void addQueryOrderMapping(String id, String columnText) {
        if(!queryOrderMapping.contains(new MyColumnOrder(id, null))) {
            queryOrderMapping.add(new MyColumnOrder(id, columnText));
        }
    }
    
    public String toString() {
       return "";
    }
    
}
