/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
  * copyright (c)2013-2014 Algorithmi.
  *
  * @author muneebahmad (ahmadgallian@yahoo.com) 
  *
  * The following source - code IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
  * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
  * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
  * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
  * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
  * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
  * THE SOFTWARE.
  * **/
package com.algorithmi.maker.juice.pro.scenes;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Environment;
import android.view.MotionEvent;
import com.algorithmi.maker.juice.pro.data.SharedData;
import com.algorithmi.maker.juice.pro.main.R;
import com.heyzap.sdk.ads.VideoAd;
import com.muneebahmad.microsun.hndl.ActionHandler;
import com.muneebahmad.microsun.interfaces.HudLayerInterface;
import com.muneebahmad.microsun.layers.HudLayer;
import com.wiyun.engine.actions.MoveTo;
import com.wiyun.engine.actions.RepeatForever;
import com.wiyun.engine.actions.ScaleTo;
import com.wiyun.engine.actions.ease.EaseBackIn;
import com.wiyun.engine.actions.ease.EaseBackOut;
import com.wiyun.engine.actions.ease.EaseElasticInOut;
import com.wiyun.engine.actions.grid.Waves3D;
import com.wiyun.engine.nodes.Button;
import com.wiyun.engine.nodes.Director;
import com.wiyun.engine.nodes.Scene;
import com.wiyun.engine.nodes.Scheduler;
import com.wiyun.engine.nodes.Sprite;
import com.wiyun.engine.nodes.Timer;
import com.wiyun.engine.opengl.Texture2D;
import com.wiyun.engine.transitions.LeftPushInTransition;
import com.wiyun.engine.transitions.RightPushInTransition;
import com.wiyun.engine.transitions.TopPushInTransition;
import com.wiyun.engine.transitions.TransitionScene;
import com.wiyun.engine.types.WYColor3B;
import com.wiyun.engine.types.WYGridSize;
import com.wiyun.engine.types.WYPoint;
import com.wiyun.engine.types.WYRect;
import com.wiyun.engine.types.WYSize;
import com.wiyun.engine.utils.TargetSelector;
import com.wiyun.engine.utils.ZwoptexManager;
import java.io.File;


/**
 *
 * @author muneebahmad
 */
public class EndScene extends Scene implements HudLayerInterface {
    
    private WYSize wySize;
    private ActionHandler actionHandler;
    private Sprite bG;
    private Sprite table;
    private Sprite glass;
    private Sprite glassIngredient;
    private Sprite strawSprite;
    private HudLayer hudLayer;
    private Button drinkButt;
    private Button homeButt;
    private Button camButt;
    private final int _MAX_CLIP_DIVISOR = 100;
    private int clipDrinkingOffset = 110;
    private final String _SCREENSHOT_TAKEN_ = "Screenshot taken and saved in your Gellery"
            + " or Album";
    private final String _SCREENSHOT_CANNOT_BE_TAKEN_ = "Screenshot cannot be taken due to an"
            + " exception. The exception details are: ";
    private Timer drinkingTimer = null;
    private TargetSelector drinkingSelector;
    private TargetSelector targetSelector;
    private boolean isDrinking;
    private Sprite message;
    
    public EndScene() {
        this.wySize = Director.getInstance().getWindowSize();
        this.actionHandler = new ActionHandler();
        setTouchEnabled(true);
        loadZwoptex();
        setContents();
    }
    
    private void loadZwoptex() {
        ZwoptexManager.addZwoptex("scenes1", R.raw.scenes_texture1, 
                Texture2D.makePNG(R.drawable.scenes_texture1));
        ZwoptexManager.addZwoptex("scenes2", R.raw.scenes_texture2, 
                Texture2D.makePNG(R.drawable.scenes_texture2));
        ZwoptexManager.addZwoptex("straw", R.raw.straw_texture,
                Texture2D.makePNG(R.drawable.straw_texture));
    }
    
    /**
     * SET CONTENTS
     */
    private void setContents() {
        init();
        addBg();
        addTable();
        setGlass();
        addGlassIngredient();
        addStraw();
        addButtons();
        addHudLayer();
    }
    
    /**
     *  ADD BG
     */
    private void addBg() {
        if (SharedData.getInstance().player.usedScenes == null) {
            this.bG = ZwoptexManager.makeSprite("scene_12.png");
        } else {
        this.bG = ZwoptexManager.makeSprite(SharedData.getInstance().player.
                usedScenes.getImageResourceName());
        }
        this.bG.setPosition(this.wySize.width / 2.0f, this.wySize.height / 2.0f);
        this.bG.setContentSize(this.wySize.width, this.wySize.height);
        this.bG.setAutoFit(true);
        addChild(this.bG);
        this.bG.autoRelease(true);
    }
    
    /**
     * ADD TABLE
     */
    private void addTable() {
        this.table = Sprite.make(Texture2D.makePNG(R.drawable.table));
        this.table.setPosition(this.wySize.width / 2.0f, 150.0f);
        addChild(this.table);
        this.table.autoRelease();
    }
    
     /**
     * ATTACH GLASS TO THE SCENE NODE
     */
    private void setGlass() {
        this.glass = ZwoptexManager.makeSprite(SharedData.
                getInstance().player.usedGlass.getImageResourceName());
        this.glass.setPosition(this.wySize.width / 2.0f, 310.0f);
        addChild(this.glass);
        this.glass.autoRelease();
        this.glass.setZOrder(10);
    }

    /**
     * Glass Ingredient
     */
    private void addGlassIngredient() {
        this.glassIngredient = Sprite.make(Texture2D.
                makePNG(R.drawable.glass_ingredient));
        this.glassIngredient.setPosition(this.glass.getWidth() / 2.0f,
                this.glass.getHeight() / 1.5f);
        this.glass.addChild(this.glassIngredient);
        this.glassIngredient.autoRelease();
        this.glassIngredient.setZOrder(-1);
        this.glassIngredient.setScale(1.0f, 1.1f);
        this.glassIngredient.setColor(SharedData.getInstance().player.usedFruits.getColor());
    }
    
     /**
     * STRAW
     */
    private void addStraw() {
        if (SharedData.getInstance().player.usedAddOn == null) {
            this.strawSprite = ZwoptexManager.makeSprite("straw_01.png");
        } else {
        this.strawSprite = ZwoptexManager.makeSprite(SharedData.getInstance().
                player.usedAddOn.getImageResourceName());
        }
        this.strawSprite.setPosition(this.glass.getPositionX(),
                this.glass.getPositionY() + 100.0f);
        addChild(this.strawSprite);
        this.strawSprite.autoRelease();
         /** 3D wave action */
        this.strawSprite.runAction(RepeatForever.make(Waves3D.make(5, WYGridSize.make(12, 20), -20, 10)));
        //this.strawSprite.setPosition(this.wySize.width / 2.0f - 25.0f, 400.0f);
        this.strawSprite.setScale(1.3f);
    }
    
    /**
     * HUD LAYER
     */
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
    
    /**
     * BUTTONS
     */
    private void addButtons() {
         //DRINK BUTTON
        this.drinkButt = Button.make(R.drawable.button_drink01,
                R.drawable.button_drink02, this, "onDrinkButtClicked");
        this.drinkButt.setPosition(170.0f, this.wySize.height + 100.0f);
        addChild(this.drinkButt);
        this.drinkButt.autoRelease();
        this.drinkButt.runAction(EaseElasticInOut.make(MoveTo.make(3.0f,
                this.drinkButt.getPositionX(), this.wySize.height + 100.0f,
                this.drinkButt.getPositionX(), this.wySize.height - 50.0f)));

        //CAM?CAPTURE BUTTON
        this.camButt = Button.make(R.drawable.button_cam01, R.drawable.button_cam02,
                this, "onCamButtClicked");
        this.camButt.setPosition(this.drinkButt.getPositionX() + 135.0f,
                this.drinkButt.getPositionY());
        addChild(this.camButt);
        this.camButt.autoRelease(true);
        this.camButt.runAction(EaseElasticInOut.make(MoveTo.make(4.0f,
                this.camButt.getPositionX(), this.wySize.height + 100.0f,
                this.camButt.getPositionX(), this.wySize.height - 50.0f)));
        
        //HOME BUTT
        this.homeButt = Button.make(R.drawable.button_home01, R.drawable.button_home02, this, "onHomeButtClicked");
        this.homeButt.setPosition(this.wySize.width / 2.0f, -150.0f);
        addChild(this.homeButt);
        this.homeButt.autoRelease();
        this.homeButt.runAction(EaseElasticInOut.make(MoveTo.make(3.0f, this.homeButt.getPositionX(), 
                this.homeButt.getPositionY(), this.homeButt.getPositionX(), 50.0f)));
    }
    
    /**
     * INIT
     */
    private void init() {
        Object[] obj = new Object[1];
        obj[0] = Float.valueOf(0.0f);
        this.drinkingSelector = new TargetSelector(this, "onDrinkingTick(float)", obj);
        if (this.drinkingTimer == null) {
            this.drinkingTimer = new Timer(this.drinkingSelector, 0.10f);
        }
    }
    
    /**
     * DRINKING TICK
     * 
     * @param paramFloat 
     */
    public void onDrinkingTick(float paramFloat) {
        SharedData.getInstance().soundsHandler.playDrinkSound();
        this.clipDrinkingOffset = (-1 + this.clipDrinkingOffset);
        this.glassIngredient.setClipRect(WYRect.make(0.0f, 0.0f, this.glassIngredient.
                getWidth(), this.glassIngredient.getHeight() / 100.0f * 
                this.clipDrinkingOffset), true);
        if (this.clipDrinkingOffset < 0) {
            Scheduler.getInstance().unschedule(this.drinkingTimer);
        }
    }
    
    /**
     * DRINK BUTT CLICKED
     */
    public void onDrinkButtClicked() {
        SharedData.getInstance().soundsHandler.playButtonClickSound();
        this.isDrinking = true;
        this.drinkButt.runAction(EaseElasticInOut.make(MoveTo.make(3.0f, this.drinkButt.getPositionX(), 
                this.drinkButt.getPositionY(), this.drinkButt.getPositionX(), 
                this.wySize.height + 150.0f)));
        
        addMessageSprite();
        
         /* HudLayer update */
        this.hudLayer.updateHudObjectVisibility(true, true, false);
        this.hudLayer.setNextButtonPosition(this.wySize.width - 60.0f,
                this.wySize.height - 50.0f);
        HudLayer localHudLayer = this.hudLayer;
        this.hudLayer.getClass();
        localHudLayer.startNextButtonAction(4);
    }
    
    /**
     * CAM BUTT CLICKED
     */
    public void onCamButtClicked() {
        SharedData.getInstance().soundsHandler.playButtonClickSound();
        makeScreenshot();
        this.camButt.runAction(EaseElasticInOut.make(MoveTo.make(3.0f, this.camButt.getPositionX(), 
                this.camButt.getPositionY(), this.camButt.getPositionX(), 
                this.wySize.height + 150.0f)));
        
         /* HudLayer update */
        this.hudLayer.updateHudObjectVisibility(true, true, false);
        this.hudLayer.setNextButtonPosition(this.wySize.width - 60.0f,
                this.wySize.height - 50.0f);
        HudLayer localHudLayer = this.hudLayer;
        this.hudLayer.getClass();
        localHudLayer.startNextButtonAction(4);
    }
    
    /**
     * HOME BUTT CLICKED
     */
    public void onHomeButtClicked() {
        SharedData.getInstance().soundsHandler.playButtonClickSound();
        Director.getInstance().popSceneWithTransition((TransitionScene) TopPushInTransition.
                make(0.5f, new MainMenuScene()));
    }
    
    private boolean makeScreenshot() {
        int i = 0;
        if (Environment.MEDIA_MOUNTED.
                equals(Environment.getExternalStorageState())) {
            if (new File(Environment.getExternalStorageDirectory().
                    getPath() + "/AlgorithmiApps/juice_free.png").exists()) {
                i = 1;
            }
            
            if (i == 1) {
                new File(Environment.getExternalStorageDirectory().
                        getPath() + "/AlgorithmiApps/juice_free.png").delete();
                Director.getInstance().makeScreenshot(Environment.
                        getExternalStorageDirectory().getPath() + "/AlgorithmiApps/"
                        + "juice_free.png");
                makeScreenshotDialog(_SCREENSHOT_TAKEN_);
            } else {
                Director.getInstance().makeScreenshot(Environment.
                        getExternalStorageDirectory().getPath() + "/AlgorithmiApps/"
                        + "juice_free.png");
                makeScreenshotDialog(_SCREENSHOT_TAKEN_);
            }
        } else {
                makeScreenshotDialog(_SCREENSHOT_CANNOT_BE_TAKEN_);
        }
        return true;
    }
    
    /**
     * Drink message Sprite.
     */
    private void addMessageSprite() {
        this.message = Sprite.make(Texture2D.makePNG(R.drawable.message));
        this.message.setPosition(this.wySize.width / 2.0f, this.wySize.height / 1.3f);
        addChild(this.message);
        this.message.autoRelease();
        this.message.setScale(0.0f);
        SharedData.getInstance().soundsHandler.playSelectedMusic();
        this.message.runAction(EaseBackOut.make(ScaleTo.make(0.5f, 0.0f, 1.0f)));
    }
    
    /**
     * MAKE SCREENSHOT.
     * @param msg String
     */
    private void makeScreenshotDialog(final String msg) {
         ((Activity) Director.getInstance().getContext()).
                 runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final AlertDialog.Builder builder = new AlertDialog.Builder(Director.getInstance().getContext());
                builder.setIcon(R.drawable.algo_logo2).
                        setMessage(msg + ".\nFollowing is the complete path of saved image.\n\n"
                        + Environment.getExternalStorageDirectory().getPath() + "/AlgorithmiApps/juice_"
                        + "free.png").setTitle(" Alert")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                            arg0.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
    
    @Override
    public boolean wyTouchesBegan(MotionEvent event) {
       WYPoint point = Director.getInstance().
               convertToGL(event.getX(), event.getY());
       if ((this.isDrinking) && (this.glass.
               getBoundingBoxRelativeToWorld().containsPoint(point)) &&
               (this.clipDrinkingOffset > 0)) {
           this.message.runAction(EaseBackIn.make(ScaleTo.make(0.5f, 1.0f, 0.0f)));
           Scheduler.getInstance().schedule(this.drinkingTimer);
       }
       
        return true;
    }
    
     
    /**
     *
     * @param event
     * @return
     */
    @Override
    public boolean wyTouchesEnded(MotionEvent event) {
        Scheduler.getInstance().unschedule(drinkingTimer);
        return true;
    }

    
    /**
     * onBACKBUTTOn
     * @return 
     */
    @Override
    public boolean onBackButton() {
        SharedData.getInstance().soundsHandler.playButtonClickSound();
        Director.getInstance().replaceScene((TransitionScene) LeftPushInTransition.
                make(0.5f, new PouringScene()));
        return true;
    }

    /**
     * ON SOFT BACK BUTTON
     */
    @Override
    public void onSoftBackButtonClicked() {
        onBackButton();
    }

    /**
     * ON SOFT NEXT BUTTON
     */
    @Override
    public void onSoftNextButtonClicked() {
        Director.getInstance().replaceScene((TransitionScene) 
                RightPushInTransition.make(0.5f, new FruitSelectionScene()));
        VideoAd.display((Activity) Director.getInstance().getContext());
    }

}/** end class. */
