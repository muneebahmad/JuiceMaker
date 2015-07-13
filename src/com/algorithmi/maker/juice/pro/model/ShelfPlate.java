/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algorithmi.maker.juice.pro.model;

/**
 *
 * @author muneebahmad
 */
public class ShelfPlate extends MObject {
   // private WYColor3B color;
    private String packageId;
    
     public ShelfPlate(String id, String name, String imageName, boolean isLocked,
			float paramFloat, String packageId) {
        super(id, name, imageName, isLocked);
        this.packageId = packageId;
        //this.color = color;
    }
     
     public String getPackageId() {
         return this.packageId;
     }
     
     public void setPackageId(String packageId) {
         this.packageId = packageId;
     }
    
}//end class
