/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algorithmi.maker.juice.pro.model;

import com.wiyun.engine.types.WYColor3B;

/**
 *
 * @author muneebahmad
 */
public class Fruits extends MObject {
    private WYColor3B color;
    private String packageId;
    
    public Fruits(String id, String name, String imageName, boolean isLocked,
			float paramFloat, String packageId, WYColor3B color) {
        super(id, name, imageName, isLocked);
        this.packageId = packageId;
        this.color = color;
    }
    
    public WYColor3B getColor() {
        return this.color;
    }
    
    public String getPackageId() {
        return this.packageId;
    }
    
    public void setColor(WYColor3B color) {
        this.color = color;
    }
    
    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }
    
}//end class
