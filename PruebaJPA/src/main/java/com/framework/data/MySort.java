/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.data;

/**
 *
 * @author mariano
 */
public class MySort {
    private String id = null;
    private Integer direction = DIRECTION_ASC;
    
    public static final int DIRECTION_ASC = 1;
    public static final int DIRECTION_DESC = 2;
    
    public MySort(String id, int direction) {
        this.id = id;
        this.direction = direction;
    }
    
    public MySort(String id) {
        this.id = id;
    }
    
    public String getId() {
        return id;
    }

    public Integer getDirection() {
        return direction;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }
    
    
}
