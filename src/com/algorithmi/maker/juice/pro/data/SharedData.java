/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algorithmi.maker.juice.pro.data;

import com.algorithmi.maker.juice.pro.model.AddOn;
import com.algorithmi.maker.juice.pro.model.FallingFruit;
import com.algorithmi.maker.juice.pro.model.FallingPlate;
import com.algorithmi.maker.juice.pro.model.Fruits;
import com.algorithmi.maker.juice.pro.model.Glass;
import com.algorithmi.maker.juice.pro.model.Player;
import com.algorithmi.maker.juice.pro.model.Scenes;
import com.algorithmi.maker.juice.pro.model.ShelfPlate;
import com.muneebahmad.microsun.hndl.SoundsHandler;
import java.util.ArrayList;

/**
 *
 * @author muneebahmad
 */
public class SharedData {

    /**
     *
     */
    public static SharedData instance;
    
    // public EndGameScene coatingBaseSceneReference;
    
    /**
     *
     */
    public ArrayList<Fruits> fruitsList;
    
    /**
     *
     */
    public ArrayList<AddOn> addOnList;
    
    /**
     * 
     */
    public ArrayList<Scenes> scenesList;
    /**
     *
     */
    public boolean isRedrawingRequired;
    //public EndGameScene coatingBaseSceneReference;
    //public ArrayList<Cup> cupList;
    // public ArrayList<PackageDeal> packageDealList;
    
    /**
     *
     */
    public ArrayList<ShelfPlate> shelfPlateList;
    
    /**
     *
     */
    public ArrayList<FallingPlate> fallingPlateList;
    
    /*
     * 
     */
    public ArrayList<FallingFruit> fallingFruitList;
    
    /**
     * 
     */
    public ArrayList<Glass> glassList;
    
    /**
     *
     */
    public Player player = new Player();
    // public ArrayList<SideOrder> sideOrderList;
    
    /**
     *
     */
    public SoundsHandler soundsHandler = new SoundsHandler();

    // public ArrayList<Straw> strawsList;
    
    /**
     * CONSTRUCTOR.
     */
    private SharedData() {
        getData();
    }

    /**
     * 
     */
    private void getData() {
        this.fruitsList = CoreData.getFruitsData();
        this.shelfPlateList = CoreData.getShelfPlateData();
        this.fallingPlateList = CoreData.getFallingPlateData();
        this.fallingFruitList = CoreData.getFallingFruitData();
        this.glassList = CoreData.getGlassData();
        this.addOnList = CoreData.getAddOnData();
        this.scenesList = CoreData.getScenesData();
        // this.sideOrderList = DataProvider.getSideOrderData();
        // this.strawsList = DataProvider.getStrawsData();
        // this.packageDealList = DataProvider.getPackageDealsData();
    }

    /**
     *
     * @return INSTANCE
     */
    public static SharedData getInstance() {
        if (instance == null) {
            instance = new SharedData();
        }
        return instance;
    }
    /**
     *
     * private void loadGameState() { int k = 0; int i1 = 0;
     *
     * if (i1 < this.plateList.size()) { int i2 = 0; ++i2; if (i2 >=
     * this.player.plateIds.size()) { ++i1; if (!(((String)
     * this.player.plateIds.get(i2)) .equalsIgnoreCase(((Plate)
     * this.plateList.get(i1)) .getId()))) ((Plate)
     * this.plateList.get(i1)).setLocked(false); } } }
        * *
     */
}//end class
