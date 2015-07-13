/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algorithmi.maker.juice.pro.scenes;

import android.view.MotionEvent;
import com.algorithmi.maker.juice.pro.data.SharedData;
import com.algorithmi.maker.juice.pro.main.R;
import com.chartboost.sdk.Chartboost;
import com.muneebahmad.microsun.hndl.ActionHandler;
import com.muneebahmad.microsun.hndl.SoundsHandler;
import com.muneebahmad.microsun.interfaces.HudLayerInterface;
import com.muneebahmad.microsun.layers.HudLayer;
import com.muneebahmad.microsun.util.Core;
import com.wiyun.engine.actions.MoveTo;
import com.wiyun.engine.nodes.Button;
import com.wiyun.engine.nodes.Director;
import com.wiyun.engine.nodes.Label;
import com.wiyun.engine.nodes.Scene;
import com.wiyun.engine.nodes.Sprite;
import com.wiyun.engine.opengl.Texture2D;
import com.wiyun.engine.transitions.LeftPushInTransition;
import com.wiyun.engine.transitions.RightPushInTransition;
import com.wiyun.engine.transitions.TransitionScene;
import com.wiyun.engine.types.WYColor3B;
import com.wiyun.engine.types.WYPoint;
import com.wiyun.engine.types.WYSize;

/**
 *
 * @author muneebahmad
 */
public class FruitSelectionScene extends Scene implements HudLayerInterface {
    
    private WYSize wySize;
    private Label label;
    private Sprite bg;
    private Sprite fridge;
    private Sprite fridgeGate;
    private int mode;
    private final int _MODE_TOUCH_FRUIT_GATE = 1;
    private int gateState = 0;
    private ActionHandler actionHandler;
    private HudLayer hudLayer;
    private Button pomeGrnate;
    private Button peer;
    private Button apple;
    private Button Orange;
    private Button grapes;
    private Button pineapple;
    private Button banana;
    private Button strawberry;
    private Button mango;
            
    /**
     *
     */
    public FruitSelectionScene() {
        this.wySize = Director.getInstance().getWindowSize();
        this.actionHandler = new ActionHandler();
        setTouchEnabled(true);
        this.mode = _MODE_TOUCH_FRUIT_GATE;
        setContents();
    }
    
    private void setContents() {
        addBg(this.wySize.width / 2.0f, this.wySize.height / 2.0f);
        addFridge(this.wySize.width / 2.0f, this.wySize.height / 2.0f);
        addLabel();
        addFridgeItems();
        addFrigeGate();
        addHudLayer();
      
    }
    
    private void addHudLayer() {
        this.hudLayer = new HudLayer(this);
        this.hudLayer.setContents(R.drawable.arrow_button1_left,
                R.drawable.arrow_button2_left, R.drawable.arrow_button1_right,
                R.drawable.arrow_button2_right, "cheri.ttf",
                WYColor3B.make(255, 255, 255), 36.0f, 0.0f);
        this.hudLayer.updateObjectsVisibility(true, false, false,
                this.wySize.width / 2.0f, this.wySize.height - 100.0f, "bck");
        this.hudLayer.setBackButtonPosition(60.0f, this.wySize.height - 50.0f);
        
        addChild(this.hudLayer);
        this.hudLayer.autoRelease(true);
        this.hudLayer.setZOrder(15);
        bringToFront(this.hudLayer);
    }
    
    private void addBg(float x, float y) {
        this.bg = Sprite.make(Texture2D.makePNG(R.drawable.fruit_bg));
        this.bg.setPosition(x, y);
        this.bg.setContentSize(this.wySize.width, this.wySize.height);
        this.bg.setAutoFit(true);
        addChild(this.bg);
        this.bg.autoRelease(true);
    }
    
    private void addFridge(float x, float y) {
        this.fridge = Sprite.make(R.drawable.fridge);
        this.fridge.setPosition(x, y);
        addChild(this.fridge);
        this.fridge.autoRelease(true);
    }
    
    private void addFrigeGate() {
        this.fridgeGate = Sprite.make(R.drawable.fridge_gate);
        this.fridgeGate.setPosition(this.wySize.width / 2.0f, this.wySize.height / 2.0f);
        addChild(this.fridgeGate);
        this.fridgeGate.setZOrder(5);
        this.fridgeGate.autoRelease(true);
    }
    
    private void addLabel() {
        this.label = Label.make("Open Fridge to select fruits", 32, "cheri.ttf", false);
        this.label.setPosition(this.wySize.width / 2.0f, 40.0f);
        addChild(this.label);
        this.label.autoRelease(true);
    }
    
    private void addFridgeItems() {
        /* pomegranate */
        this.pomeGrnate = Button.make(R.drawable.pomegranate_final, 
                R.drawable.pomegranate_final, this, "onPomegranateButtonClicked");
        this.pomeGrnate.setPosition(this.fridge.getWidth() / 3.0f, this.fridge.getHeight() / 1.3f + 27.0f);
        this.fridge.addChild(this.pomeGrnate);
        this.pomeGrnate.setScale(1.5f);
        this.pomeGrnate.autoRelease(true);
        this.pomeGrnate.runAction(this.actionHandler.getRepeatingJumpAction(0.5f, 
                this.pomeGrnate.getPositionX(), this.pomeGrnate.getPositionY(), 5));
        this.pomeGrnate.runAction(this.actionHandler.getRepeatingRotateAction(0.5f, 25));
        /* pear */
        this.peer = Button.make(R.drawable.pear_final, R.drawable.pear_final, this, "onPearButtonClicked");
        this.peer.setPosition(this.fridge.getWidth() / 1.5f, this.pomeGrnate.getPositionY() + 10.0f);
        this.fridge.addChild(this.peer);
        this.peer.setScale(0.8f);
        this.peer.autoRelease(true);
        this.peer.runAction(this.actionHandler.getRepeatingJumpAction(0.5f, this.peer.getPositionX(), 
                this.peer.getPositionY() + 5.0f, -5));
        this.peer.runAction(this.actionHandler.getRepeatingRotateAction(0.5f, -5));
        /* apple */
        this.apple = Button.make(R.drawable.apple_final, R.drawable.apple_final, this, "onAppleButtonClicked");
        this.apple.setPosition(this.pomeGrnate.getPositionX(), this.pomeGrnate.getPositionY() - 115.0f);
        this.fridge.addChild(this.apple);
        this.apple.autoRelease(true);
        this.apple.runAction(this.actionHandler.getRepeatingRotateAction(0.5f, -15));
        /* orange */
        this.Orange =  Button.make(R.drawable.orrange_final, R.drawable.orrange_final, this, "onOrangeButtonClicked");
        this.Orange.setPosition(this.peer.getPositionX(), this.apple.getPositionY());
        this.fridge.addChild(this.Orange);
        this.Orange.autoRelease(true);
        this.Orange.runAction(this.actionHandler.getRepeatingRotateAction(0.5f, 15));
        /* grapes */
        this.grapes = Button.make(R.drawable.grapes_final, R.drawable.grapes_final, this, "onGrapesButtonClicked");
        this.grapes.setPosition(this.apple.getPositionX(), this.apple.getPositionY() - 115.0f);
        this.fridge.addChild(this.grapes);
        this.grapes.autoRelease(true);
        this.grapes.setScale(1.1f);
        this.grapes.runAction(this.actionHandler.getRepeatingJumpAction(0.5f, this.grapes.getPositionX(), 
                this.grapes.getPositionY() + 5.0f, -5));
        this.grapes.runAction(this.actionHandler.getRepeatingRotateAction(0.5f, -15));
        /* pineapple */
        this.pineapple = Button.make(R.drawable.pineapple_final, R.drawable.pineapple_final, this, 
                "onPineappleButtonClicked");
        this.pineapple.setPosition(this.Orange.getPositionX(), this.grapes.getPositionY());
        this.fridge.addChild(this.pineapple);
        this.pineapple.autoRelease(true);
        this.pineapple.setScale(1.1f);
        this.pineapple.runAction(this.actionHandler.getRepeatingJumpAction(0.5f, 
                this.pineapple.getPositionX(), this.pineapple.getPositionY(), 5));
        this.pineapple.runAction(this.actionHandler.getRepeatingRotateAction(0.5f, 25));
        /* banana */
        this.banana = Button.make(R.drawable.bnana_final, R.drawable.bnana_final, this, "onBananaButtonClicked");
        this.banana.setPosition(this.grapes.getPositionX(), this.grapes.getPositionY() - 115.0f);
        this.fridge.addChild(this.banana);
        this.banana.autoRelease(true);
        this.banana.runAction(this.actionHandler.getRepeatingRotateAction(0.5f, 15));
        /* strawberry */
        this.strawberry = Button.make(R.drawable.strawberry_final, R.drawable.strawberry_final, this,
                "onStrawberryButtonClicked");
        this.strawberry.setPosition(this.pineapple.getPositionX(), this.banana.getPositionY());
        this.fridge.addChild(this.strawberry);
        this.strawberry.autoRelease(true);
        this.strawberry.runAction(this.actionHandler.getRepeatingRotateAction(0.5f, -15));
        /* mango */
        this.mango = Button.make(R.drawable.mango_final, R.drawable.mango_final, this, "onMangoButtonClicked");
        this.mango.setPosition(this.fridge.getWidth() / 2.0f, this.banana.getPositionY() - 115.0f);
        this.fridge.addChild(this.mango);
        this.mango.autoRelease(true);
        this.mango.runAction(this.actionHandler.getRepeatingJumpAction(0.5f, this.mango.getPositionX(), 
                this.mango.getPositionY() + 5.0f, -5));
        this.mango.runAction(this.actionHandler.getRepeatingRotateAction(0.5f, -15));
    }
    
    /**
     * buttons clicked implementation
     */
    
    /* pomegranate */
    public void onPomegranateButtonClicked() {
        if (this.gateState == 1) {
        Core.fruitType = Core.FRUIT_POMEGRANATE;
        SharedData.getInstance().player.usedFruits = SharedData.getInstance().fruitsList.get(0);
        SharedData.getInstance().player.usedShelfPlate = SharedData.getInstance().shelfPlateList.get(0);
        SharedData.getInstance().player.usedFallingPlate = SharedData.getInstance().fallingPlateList.get(0);
        SharedData.getInstance().player.usedFallingFruit = SharedData.getInstance().fallingFruitList.get(0);
        /* HudLayer update */
                    this.hudLayer.updateHudObjectVisibility(true, true, false);
                    this.hudLayer.setNextButtonPosition(this.wySize.width - 60.0f,
				this.wySize.height - 50.0f);
                    HudLayer localHudLayer = this.hudLayer;
                    this.hudLayer.getClass();
                    localHudLayer.startNextButtonAction(4);
        /* Scene Transition */ 
                    Director.getInstance().replaceScene(
                            (TransitionScene) RightPushInTransition.make(0.5f, new FruitSelectedScene()).autoRelease());
        }
    }
    
    /* pear */
    /**
     *
     */
    public void onPearButtonClicked() {
        if (this.gateState == 1) {
        Core.fruitType = Core.FRUIT_PEAR;
        SharedData.getInstance().player.usedFruits = SharedData.getInstance().fruitsList.get(2);
        SharedData.getInstance().player.usedShelfPlate = SharedData.getInstance().shelfPlateList.get(2);
        SharedData.getInstance().player.usedFallingPlate = SharedData.getInstance().fallingPlateList.get(2);
        SharedData.getInstance().player.usedFallingFruit = SharedData.getInstance().fallingFruitList.get(2);
        /* HudLayer update */
                    this.hudLayer.updateHudObjectVisibility(true, true, false);
                    this.hudLayer.setNextButtonPosition(this.wySize.width - 60.0f,
				this.wySize.height - 50.0f);
                    HudLayer localHudLayer = this.hudLayer;
                    this.hudLayer.getClass();
                    localHudLayer.startNextButtonAction(4);
         /* Scene Transition */ 
                    Director.getInstance().replaceScene(
                            (TransitionScene) RightPushInTransition.make(0.5f, new FruitSelectedScene()).autoRelease());
        }
    }
    
    /* apple */
    /**
     *
     */
    public void onAppleButtonClicked() {
        if (this.gateState == 1) {
        Core.fruitType = Core.FRUIT_APPLE;
        SharedData.getInstance().player.usedFruits = SharedData.getInstance().fruitsList.get(8);
        SharedData.getInstance().player.usedShelfPlate = SharedData.getInstance().shelfPlateList.get(8);
        SharedData.getInstance().player.usedFallingPlate = SharedData.getInstance().fallingPlateList.get(8);
        SharedData.getInstance().player.usedFallingFruit = SharedData.getInstance().fallingFruitList.get(8);
        /* HudLayer update */
                    this.hudLayer.updateHudObjectVisibility(true, true, false);
                    this.hudLayer.setNextButtonPosition(this.wySize.width - 60.0f,
				this.wySize.height - 50.0f);
                    HudLayer localHudLayer = this.hudLayer;
                    this.hudLayer.getClass();
                    localHudLayer.startNextButtonAction(4);
         /* Scene Transition */ 
                    Director.getInstance().replaceScene(
                            (TransitionScene) RightPushInTransition.make(0.5f, new FruitSelectedScene()).autoRelease()); 
        }
    }
    
    /* orange */
    /**
     *
     */
    public void onOrangeButtonClicked() {
        if (this.gateState == 1) {
        Core.fruitType = Core.FRUIT_ORANGE;
        SharedData.getInstance().player.usedFruits = SharedData.getInstance().fruitsList.get(3);
        SharedData.getInstance().player.usedShelfPlate = SharedData.getInstance().shelfPlateList.get(3);
        SharedData.getInstance().player.usedFallingPlate = SharedData.getInstance().fallingPlateList.get(3);
        SharedData.getInstance().player.usedFallingFruit = SharedData.getInstance().fallingFruitList.get(3);
        /* HudLayer update */
                    this.hudLayer.updateHudObjectVisibility(true, true, false);
                    this.hudLayer.setNextButtonPosition(this.wySize.width - 60.0f,
				this.wySize.height - 50.0f);
                    HudLayer localHudLayer = this.hudLayer;
                    this.hudLayer.getClass();
                    localHudLayer.startNextButtonAction(4);
         /* Scene Transition */ 
                    Director.getInstance().replaceScene(
                            (TransitionScene) RightPushInTransition.make(0.5f, new FruitSelectedScene()).autoRelease()); 
        }
    }
    
    /* grapes */
    /**
     *
     */
    public void onGrapesButtonClicked() {
        if (this.gateState == 1) {
        Core.fruitType = Core.FRUIT_GRAPES;
        SharedData.getInstance().player.usedFruits = SharedData.getInstance().fruitsList.get(5);
        SharedData.getInstance().player.usedShelfPlate = SharedData.getInstance().shelfPlateList.get(5);
        SharedData.getInstance().player.usedFallingPlate = SharedData.getInstance().fallingPlateList.get(5);
        SharedData.getInstance().player.usedFallingFruit = SharedData.getInstance().fallingFruitList.get(5);
        /* HudLayer update */
                    this.hudLayer.updateHudObjectVisibility(true, true, false);
                   this.hudLayer.setNextButtonPosition(this.wySize.width - 60.0f,
				this.wySize.height - 50.0f);
                    HudLayer localHudLayer = this.hudLayer;
                    this.hudLayer.getClass();
                    localHudLayer.startNextButtonAction(4);
        /* Scene Transition */ 
                    Director.getInstance().replaceScene(
                            (TransitionScene) RightPushInTransition.make(0.5f, new FruitSelectedScene()).autoRelease()); 
        }
    }
    
    /* pineapple */
    /**
     *
     */
    public void onPineappleButtonClicked() {
        if (this.gateState == 1) {
        Core.fruitType = Core.FRUIT_PINEAPPLE;
        SharedData.getInstance().player.usedFruits = SharedData.getInstance().fruitsList.get(1);
        SharedData.getInstance().player.usedShelfPlate = SharedData.getInstance().shelfPlateList.get(1);
        SharedData.getInstance().player.usedFallingPlate = SharedData.getInstance().fallingPlateList.get(1);
        SharedData.getInstance().player.usedFallingFruit = SharedData.getInstance().fallingFruitList.get(1);
        /* HudLayer update */
                    this.hudLayer.updateHudObjectVisibility(true, true, false);
                    this.hudLayer.setNextButtonPosition(this.wySize.width - 60.0f,
				this.wySize.height - 50.0f);
                    HudLayer localHudLayer = this.hudLayer;
                    this.hudLayer.getClass();
                    localHudLayer.startNextButtonAction(4);
        /* Scene Transition */ 
                    Director.getInstance().replaceScene(
                            (TransitionScene) RightPushInTransition.make(0.5f, new FruitSelectedScene()).autoRelease());
        }
    }
    
    /* banana */
    /**
     *
     */
    public void onBananaButtonClicked() {
        if (this.gateState == 1) {
        Core.fruitType = Core.FRUIT_BANANA;
        SharedData.getInstance().player.usedFruits = SharedData.getInstance().fruitsList.get(7);
        SharedData.getInstance().player.usedShelfPlate = SharedData.getInstance().shelfPlateList.get(7);
        SharedData.getInstance().player.usedFallingPlate = SharedData.getInstance().fallingPlateList.get(7);
        SharedData.getInstance().player.usedFallingFruit = SharedData.getInstance().fallingFruitList.get(7);
        /* HudLayer update */
                    this.hudLayer.updateHudObjectVisibility(true, true, false);
                    this.hudLayer.setNextButtonPosition(this.wySize.width - 60.0f,
				this.wySize.height - 50.0f);
                    HudLayer localHudLayer = this.hudLayer;
                    this.hudLayer.getClass();
                    localHudLayer.startNextButtonAction(4);
         /* Scene Transition */ 
                    Director.getInstance().replaceScene(
                            (TransitionScene) RightPushInTransition.make(0.5f, new FruitSelectedScene()).autoRelease());
        }
    }
    
    /* strawberry */
    /**
     *
     */
    public void onStrawberryButtonClicked() {
        if (this.gateState == 1) {
        Core.fruitType = Core.FRUIT_STRAWBERRY;
        SharedData.getInstance().player.usedFruits = SharedData.getInstance().fruitsList.get(6);
        SharedData.getInstance().player.usedShelfPlate = SharedData.getInstance().shelfPlateList.get(6);
        SharedData.getInstance().player.usedFallingPlate = SharedData.getInstance().fallingPlateList.get(6);
        SharedData.getInstance().player.usedFallingFruit = SharedData.getInstance().fallingFruitList.get(6);
        /* HudLayer update */
                    this.hudLayer.updateHudObjectVisibility(true, true, false);
                    this.hudLayer.setNextButtonPosition(this.wySize.width - 60.0f,
				this.wySize.height - 50.0f);
                    HudLayer localHudLayer = this.hudLayer;
                    this.hudLayer.getClass();
                    localHudLayer.startNextButtonAction(4);
         /* Scene Transition */ 
                    Director.getInstance().replaceScene(
                            (TransitionScene) RightPushInTransition.make(0.5f, new FruitSelectedScene()).autoRelease()); 
        }
    }
    
    /* mango */
    /**
     *
     */
    public void onMangoButtonClicked() {
        if (this.gateState == 1) {
        Core.fruitType = Core.FRUIT_MANGO;
        SharedData.getInstance().player.usedFruits = SharedData.getInstance().fruitsList.get(4);
        SharedData.getInstance().player.usedShelfPlate = SharedData.getInstance().shelfPlateList.get(4);
        SharedData.getInstance().player.usedFallingPlate = SharedData.getInstance().fallingPlateList.get(4);
        SharedData.getInstance().player.usedFallingFruit = SharedData.getInstance().fallingFruitList.get(4);
        /* HudLayer update */
                    this.hudLayer.updateHudObjectVisibility(true, true, false);
                    this.hudLayer.setNextButtonPosition(this.wySize.width - 60.0f,
				this.wySize.height - 50.0f);
                    HudLayer localHudLayer = this.hudLayer;
                    this.hudLayer.getClass();
                    localHudLayer.startNextButtonAction(4);
         /* Scene Transition */ 
                    Director.getInstance().replaceScene(
                            (TransitionScene) RightPushInTransition.make(0.5f, new FruitSelectedScene()).autoRelease()); 
        }
    }
    
    /**
     * end buttons clicked implementation
     * @return 
     */
    
    @Override
    public boolean onBackButton() {
        SoundsHandler.getInstance().playButtonClickSound();
        Director.getInstance().popSceneWithTransition(
				(TransitionScene) LeftPushInTransition.make(0.5f,
						new MainMenuScene()).autoRelease());
        return true;
    }
    
    /**
     *
     * @param event
     * @return
     */
    @Override
    public boolean wyTouchesBegan(MotionEvent event) {
        WYPoint point = Director.getInstance().convertToGL(event.getX(), event.getY());
        switch (this.mode) {
            case _MODE_TOUCH_FRUIT_GATE:
                if ((this.fridgeGate.getBoundingBoxRelativeToWorld().containsPoint(point)) && 
                        (this.gateState == 0)) {
                    this.fridgeGate.runAction(MoveTo.make(1.0f, 
                            this.fridgeGate.getPositionX(), this.fridgeGate.getPositionY(), 
                            this.wySize.width / 2.0f - this.fridgeGate.getWidth(), 
                            this.fridgeGate.getPositionY()));
                    this.gateState = 1;
                    
                } else if ((this.fridgeGate.getBoundingBoxRelativeToWorld().containsPoint(point)) && 
                        (this.gateState == 1)) {
                    this.fridgeGate.runAction(MoveTo.make(1.0f, this.fridgeGate.getPositionX(), 
                            this.fridgeGate.getPositionY(), this.wySize.width / 2.0f, 
                            this.fridgeGate.getPositionY()));
                    this.gateState = 0;
                }
                
            break;
        }
    return true;
    }

    /**
     *
     */
    @Override
    public void onSoftBackButtonClicked() {
        onBackButton();
    }

    /**
     *
     */
    @Override
    public void onSoftNextButtonClicked() {
        
    }
    
}/* end class */
