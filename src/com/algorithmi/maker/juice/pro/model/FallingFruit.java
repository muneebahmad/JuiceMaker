/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algorithmi.maker.juice.pro.model;

/**
 *
 * @author muneebahmad
 */
public class FallingFruit extends MObject {
    private String packageId;
    private int index;
    /**
     * 
     * @param id
     * @param name
     * @param imageName
     * @param isLocked
     * @param paramFloat
     * @param packageId
     * @param index int array index 
     */
    public FallingFruit(String id, String name, String imageName, boolean isLocked,
			float paramFloat, String packageId, int index) {
        super(id, name, imageName, isLocked);
        this.packageId = packageId;
        this.index = index;
        //this.color = color;
    }
    
    /**
     * 
     * @return String
     */
    public String getPackageId() {
        return this.packageId;
    }
    
    /**
     * 
     * @param packageId String 
     */
    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }
    
    /**
     * 
     * @param index int
     */
    public void setIndex(int index) {
        this.index = index;
    }
    
    /**
     * 
     * @return index int 
     */
    public int getIndex() {
        return this.index;
    }
}/* end class */
