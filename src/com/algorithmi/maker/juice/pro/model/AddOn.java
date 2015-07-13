/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algorithmi.maker.juice.pro.model;

/**
 *
 * @author muneebahmad
 */
public class AddOn extends MObject {
    private String packageId;
    
    public AddOn(String id, String name, String imageName, boolean isLocked,
			float paramFloat, String packageId) {
        super(id, name, imageName, isLocked);
        this.packageId = packageId;
    }
    
    public String getPackageId() {
        return this.packageId;
    }
    
    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }
}//end class
