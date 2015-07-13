/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algorithmi.maker.juice.pro.model;

import android.content.res.Resources;
import com.algorithmi.maker.juice.pro.main.R;
import com.wiyun.engine.nodes.Director;
import com.wiyun.engine.types.WYPoint;

/**
 *
 * @author muneebahmad
 */
public class MObject {
    protected String id;
    protected String imageName;
    protected boolean isLocked;
    protected String name;
    protected WYPoint point;
    
    public MObject(String id, String name, String imageName, boolean isLocked) {
		this.id = id;
		this.name = name;
		this.imageName = imageName;
		this.isLocked = isLocked;
	}

	public String getId() {
		return this.id;
	}

	public int getImageResourceId() {
		int i;
		int j;

		try {
			j = Director
					.getInstance()
					.getContext()
					.getResources()
					.getIdentifier(
							"drawable/" +
							this.imageName,
							"drawable",
							Director.getInstance().getContext()
									.getPackageName());
			i = j;
		} catch (Resources.NotFoundException rnfe) {
			i = R.drawable.algo_logo1;
		}
		return i;
	}
	
	public String getImageResourceName() {
		String str1;
		String str2;
		try {
			str2 = this.imageName + ".png";
			str1 = str2;
		}catch (Resources.NotFoundException rnfe) {
			str1 = "ic_launcher.png";
		}
		return str1;
	}
	
	public WYPoint getItemPosition() {
		return this.point;
	}
	
	public String getName() {
		return this.name;
	}
	
	public float getPackagePrice() {
		return 0.0F;
	}
	
	public boolean isLocked() {
		return this.isLocked;
	}
	
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
	public void setItemPosition(float x, float y) {
		this.point = WYPoint.make(x, y);
	}
	
	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}//end class
