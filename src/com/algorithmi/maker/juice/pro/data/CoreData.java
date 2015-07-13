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
import com.algorithmi.maker.juice.pro.model.Scenes;
import com.algorithmi.maker.juice.pro.model.ShelfPlate;
import com.wiyun.engine.types.WYColor3B;
import java.util.ArrayList;

/**
 *
 * @author muneebahmad
 */
public class CoreData {
    /**
     *
     * @return
     */
    public static java.util.ArrayList<Fruits> getFruitsData() {
        ArrayList fruitsList = new ArrayList();
        fruitsList.add(new Fruits("pomegrnate", "Fruit", "pomegranate_final", false, 
                1.0f, "fruits_package", WYColor3B.make(255, 0, 0)));
        fruitsList.add(new Fruits("pineapple", "Fruit", "pineapple_final", false, 
                1.0f, "fruits_package", WYColor3B.make(255, 200, 0)));
        fruitsList.add(new Fruits("pear", "Fruit", "pear_final", false, 
                1.0f, "fruits_package", WYColor3B.make(25, 255, 0)));
        fruitsList.add(new Fruits("orange", "Fruit", "orange_final", false, 
                1.0f, "fruits_package", WYColor3B.make(225, 160, 10)));
        fruitsList.add(new Fruits("mango", "Fruit", "mango_final", false, 
                1.0f, "fruits_package", WYColor3B.make(255, 255, 0)));
        fruitsList.add(new Fruits("grapes", "Fruit", "grapes_final", false, 
                1.0f, "fruits_package", WYColor3B.make(25, 0, 255)));
        fruitsList.add(new Fruits("strawberry", "Fruit", "strawberry_final", false, 
                1.0f, "fruits_package", WYColor3B.make(255, 0, 0)));
        fruitsList.add(new Fruits("banana", "Fruit", "banana_final", false, 
                1.0f, "fruits_package", WYColor3B.make(255, 255, 200)));
        fruitsList.add(new Fruits("apple", "Fruit", "apple_final", false, 
                1.0f, "fruits_package", WYColor3B.make(140, 200, 0)));
        return fruitsList;
    }
    
    /**
     * 
     * @return 
     */
    public static java.util.ArrayList<ShelfPlate> getShelfPlateData() {
        ArrayList shelfPlateList = new ArrayList();
        shelfPlateList.add(new ShelfPlate("pome_s_plate", "ShelfPlate", "plate_pomegrante", false, 
                1.0f, "shelf_plate_package"));
        shelfPlateList.add(new ShelfPlate("pine_s_plate", "ShelfPlate", "plate_pineapple", false, 
                1.0f, "shelf_plate_package"));
        shelfPlateList.add(new ShelfPlate("pear_s_plate", "ShelfPlate", "plate_pear", false, 
                1.0f, "shelf_plate_package"));
        shelfPlateList.add(new ShelfPlate("orange_s_plate", "ShelfPlate", "plate_orrange", false, 
                1.0f, "shelf_plate_package"));
        shelfPlateList.add(new ShelfPlate("mango_s_plate", "ShelfPlate", "plate_mango", false, 
                1.0f, "shelf_plate_package"));
        shelfPlateList.add(new ShelfPlate("grapes_s_plate", "ShelfPlate", "plate_grapes", false, 
                1.0f, "shelf_plate_package"));
        shelfPlateList.add(new ShelfPlate("strawberry_s_plate", "ShelfPlate", "plate_strabery", false, 
                1.0f, "shelf_plate_package"));
        shelfPlateList.add(new ShelfPlate("banana_s_plate", "ShelfPlate", "plate_banana", false, 
                1.0f, "shelf_plate_package"));
        shelfPlateList.add(new ShelfPlate("apple_s_plate", "ShelfPlate", "plate_apple", false, 
                1.0f, "shelf_plate_package"));
        return shelfPlateList;
    }
    
    /**
     * 
     * @return 
     */
    public static java.util.ArrayList<FallingPlate> getFallingPlateData() {
        ArrayList fallingPlateList = new ArrayList();
        fallingPlateList.add(new FallingPlate("pomigranatefallingplate", "FallingPlate", "plate_pomegranate_01", 
                false, 1.0f, "falling_plate"));
        fallingPlateList.add(new FallingPlate("pineapplefallingplate", "FallingPlate", "plate_pineapple_01", 
                false, 1.0f, "falling_plate"));
        fallingPlateList.add(new FallingPlate("pearfallingplate", "FallingPlate", "plate_pear_01", 
                false, 1.0f, "falling_plate"));
        fallingPlateList.add(new FallingPlate("orangefallingplate", "FallingPlate", "plate_orrange_01", 
                false, 1.0f, "falling_plate"));
        fallingPlateList.add(new FallingPlate("mangofallingplate", "FallingPlate", "plate_mango_01", 
                false, 1.0f, "falling_plate"));
        fallingPlateList.add(new FallingPlate("grapesfallingplate", "FallingPlate", "plate_grapes_01", 
                false, 1.0f, "falling_plate"));
        fallingPlateList.add(new FallingPlate("strawberryfallingplate", "FallingPlate", "plate_strabery_01", 
                false, 1.0f, "falling_plate"));
        fallingPlateList.add(new FallingPlate("bananafallingplate", "FallingPlate", "plate_banana_01", 
                false, 1.0f, "falling_plate"));
        fallingPlateList.add(new FallingPlate("applefallingplate", "FallingPlate", "plate_apple_01", 
                false, 1.0f, "falling_plate"));
        return fallingPlateList;
    }
    
    /**
     * 
     * @return 
     */
    public static java.util.ArrayList<FallingFruit> getFallingFruitData() {
        ArrayList fallingFruitList = new ArrayList();
        fallingFruitList.add(new FallingFruit("pomigranatefallingplate", "FallingFlate", "falling_pomegranate01", 
                false, 1.0f, "falling_plate", 0));
        fallingFruitList.add(new FallingFruit("pineapplefallingplate", "FallingPlate", "falling_pineapple01", 
                false, 1.0f, "falling_plate", 1));
        fallingFruitList.add(new FallingFruit("pearfallingplate", "FallingPlate", "falling_pear01", 
                false, 1.0f, "falling_plate", 2));
        fallingFruitList.add(new FallingFruit("orangefallingplate", "FallingPlate", "falling_orrange01", 
                false, 1.0f, "falling_plate", 3));
        fallingFruitList.add(new FallingFruit("mangofallingplate", "FallingPlate", "falling_mango01", 
                false, 1.0f, "falling_plate", 4));
        fallingFruitList.add(new FallingFruit("grapesfallingplate", "FallingPlate", "falling_grapes01", 
                false, 1.0f, "falling_plate", 5));
        fallingFruitList.add(new FallingFruit("strawberryfallingplate", "FallingPlate", "falling_strabery01", 
                false, 1.0f, "falling_plate", 6));
        fallingFruitList.add(new FallingFruit("bananafallingplate", "FallingPlate", "falling_banana01", 
                false, 1.0f, "falling_plate", 7));
        fallingFruitList.add(new FallingFruit("applefallingplate", "FallingPlate", "falling_apple01", 
                false, 1.0f, "falling_plate", 8));
        return fallingFruitList;
    }
    
    /**
     * 
     * @return java.util.ArrayList glassList
     */
    public static java.util.ArrayList<Glass> getGlassData() {
        ArrayList glassList = new ArrayList();
        glassList.add(new Glass("glass01", "glass01", "glass01", false, 1.0f, "glass"));
        glassList.add(new Glass("glass02", "glass02", "glass02", false, 1.0f, "glass"));
        glassList.add(new Glass("glass03", "glass03", "glass03", false, 1.0f, "glass"));
        glassList.add(new Glass("glass04", "glass04", "glass04", true, 1.0f, "glass"));
        glassList.add(new Glass("glass05", "glass05", "glass05", true, 1.0f, "glass"));
        glassList.add(new Glass("glass06", "glass06", "glass06", true, 1.0f, "glass"));
        glassList.add(new Glass("glass07", "glass07", "glass07", true, 1.0f, "glass"));
        glassList.add(new Glass("glass08", "glass08", "glass08", true, 1.0f, "glass"));
        glassList.add(new Glass("glass09", "glass09", "glass09", true, 1.0f, "glass"));
        glassList.add(new Glass("glass10", "glass10", "glass10", true, 1.0f, "glass"));
        return glassList;
    }
    
    /**
     * 
     * @return 
     */
    public static java.util.ArrayList<AddOn> getAddOnData() {
       ArrayList addOnList = new ArrayList();
       addOnList.add(new AddOn("straw_01", "straw_01", "straw_01", false, 1.0f, "straw"));
       addOnList.add(new AddOn("straw_02", "straw_02", "straw_02", false, 1.0f, "straw"));
       addOnList.add(new AddOn("straw_03", "straw_03", "straw_03", false, 1.0f, "straw"));
       addOnList.add(new AddOn("straw_04", "straw_04", "straw_04", true, 1.0f, "straw"));
       addOnList.add(new AddOn("straw_05", "straw_05", "straw_05", true, 1.0f, "straw"));
       addOnList.add(new AddOn("straw_06", "straw_06", "straw_06", true, 1.0f, "straw"));
       addOnList.add(new AddOn("straw_07", "straw_07", "straw_07", true, 1.0f, "straw"));
       addOnList.add(new AddOn("straw_08", "straw_08", "straw_08", true, 1.0f, "straw"));
       addOnList.add(new AddOn("straw_09", "straw_09", "straw_09", true, 1.0f, "straw"));
       addOnList.add(new AddOn("straw_10", "straw_10", "straw_10", true, 1.0f, "straw"));
       return addOnList;
    }
    
    public static java.util.ArrayList<Scenes> getScenesData() {
        ArrayList scenesList = new ArrayList();
        scenesList.add(new Scenes("scene_01", "scene_01", "scene_01", false, 1.0f, "scenes"));
        scenesList.add(new Scenes("scene_02", "scene_02", "scene_02", false, 1.0f, "scenes"));
        scenesList.add(new Scenes("scene_03", "scene_03", "scene_03", false, 1.0f, "scenes"));
        scenesList.add(new Scenes("scene_04", "scene_04", "scene_04", true, 1.0f, "scenes"));
        scenesList.add(new Scenes("scene_05", "scene_05", "scene_05", true, 1.0f, "scenes"));
        scenesList.add(new Scenes("scene_06", "scene_06", "scene_06", true, 1.0f, "scenes"));
        scenesList.add(new Scenes("scene_07", "scene_07", "scene_07", true, 1.0f, "scenes"));
        scenesList.add(new Scenes("scene_08", "scene_08", "scene_08", true, 1.0f, "scenes"));
        scenesList.add(new Scenes("scene_09", "scene_09", "scene_09", true, 1.0f, "scenes"));
        scenesList.add(new Scenes("scene_10", "scene_10", "scene_10", true, 1.0f, "scenes"));
        scenesList.add(new Scenes("scene_11", "scene_11", "scene_11", true, 1.0f, "scenes"));
        scenesList.add(new Scenes("scene_12", "scene_12", "scene_12", false, 1.0f, "scenes"));
        return scenesList;
    }
}//end class
