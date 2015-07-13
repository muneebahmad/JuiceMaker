/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * copyright (c)2013-2014 Algorithmi.
 *
 * @author muneebahmad (ahmadgallian@yahoo.com)
 *
 * The following source - code IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY
 * KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO
 * EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES
 * OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE. *
 */
package com.algorithmi.maker.juice.pro.scenes;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import com.algorithmi.maker.juice.pro.data.SharedData;
import com.algorithmi.maker.juice.pro.main.R;
import com.algorithmi.maker.juice.pro.model.AddOn;
import com.algorithmi.maker.juice.pro.model.Scenes;
import com.muneebahmad.microsun.hndl.ActionHandler;
import com.muneebahmad.microsun.interfaces.HudLayerInterface;
import com.muneebahmad.microsun.layers.GridInterface;
import com.muneebahmad.microsun.layers.GridLayer;
import com.muneebahmad.microsun.layers.HudLayer;
import com.muneebahmad.microsun.util.Core;
import com.muneebahmad.microsun.util.ParticlePouring;
import com.muneebahmad.microsun.util.ParticleSun;
import com.wiyun.engine.actions.CallFunc;
import com.wiyun.engine.actions.DelayTime;
import com.wiyun.engine.actions.FiniteTimeAction;
import com.wiyun.engine.actions.IntervalAction;
import com.wiyun.engine.actions.MoveTo;
import com.wiyun.engine.actions.RepeatForever;
import com.wiyun.engine.actions.RotateBy;
import com.wiyun.engine.actions.Sequence;
import com.wiyun.engine.actions.ease.EaseElasticInOut;
import com.wiyun.engine.actions.grid.Waves3D;
import com.wiyun.engine.nodes.Button;
import com.wiyun.engine.nodes.Director;
import com.wiyun.engine.nodes.Scene;
import com.wiyun.engine.nodes.Scheduler;
import com.wiyun.engine.nodes.Sprite;
import com.wiyun.engine.nodes.Timer;
import com.wiyun.engine.opengl.Texture2D;
import com.wiyun.engine.particle.ParticleSystem;
import com.wiyun.engine.transitions.LeftPushInTransition;
import com.wiyun.engine.transitions.RightPushInTransition;
import com.wiyun.engine.transitions.TransitionScene;
import com.wiyun.engine.types.WYColor3B;
import com.wiyun.engine.types.WYGridSize;
import com.wiyun.engine.types.WYRect;
import com.wiyun.engine.types.WYSize;
import com.wiyun.engine.utils.TargetSelector;
import com.wiyun.engine.utils.ZwoptexManager;

/**
 *
 * @author muneebahmad
 */
public class PouringScene extends Scene implements HudLayerInterface,
        GridInterface {

    private ActionHandler actionHandler;
    private WYSize wySize;
    private Sprite bg;
    private ParticleSystem particleSystem;
    private HudLayer hudLayer;
    private Sprite cloudsTwo;
    private Sprite table;
    private Sprite glass;
    private Timer pouringTimer;
    private TargetSelector pouringSelector;
    private ParticleSystem pouringParticles;
    private Sprite jug;
    private Sprite jugIngredient;
    private Sprite glassIngredient;
    private final int MAX_CLIP_DIVISOR = 110;
    private int clipHeightOffset;
    private int clipHeightOffsetJug = 160;
    private Button sceneButt;
    private Button strawButt;
    private GridLayer gridLayer;
    private Sprite strawSprite;

    /**
     * CONSTRUCTOR.
     */
    public PouringScene() {
        this.actionHandler = new ActionHandler();
        this.wySize = Director.getInstance().getWindowSize();
        loadSceneZwoptex();
        setContents();
        replaceDrinks();
    }

    /**
     *SET CONTENTS
     */
    private void setContents() {
        initTimer();
        addBg();
        addTable();
        //addSun();
        addClouds();
        setJug();
        setGlass();
        addGlassIngredient();
        addJugIngredient();
        addStraw();
        addHudLayer();
    }

    /**
     * LOAD ZWOPTEX.
     */
    private void loadSceneZwoptex() {
        ZwoptexManager.addZwoptex("scenes1", R.raw.scenes_texture1,
                Texture2D.makePNG(R.drawable.scenes_texture1));
        ZwoptexManager.addZwoptex("scenes2", R.raw.scenes_texture2,
                Texture2D.makePNG(R.drawable.scenes_texture2));
        ZwoptexManager.addZwoptex("straws", R.raw.straw_texture,
                Texture2D.makePNG(R.drawable.straw_texture));
    }

    /**
     * ADD BG.
     */
    private void addBg() {

        if (SharedData.getInstance().player.usedScenes == null) {
            this.bg = ZwoptexManager.makeSprite("scene_12.png");
        } else {
            this.bg = ZwoptexManager.makeSprite(SharedData.getInstance().player.usedScenes.getImageResourceName());
        }
        this.bg.setPosition(this.wySize.width / 2.0f, this.wySize.height / 2.0f);
        this.bg.setContentSize(this.wySize.width, this.wySize.height);
        this.bg.setAutoFit(true);
        addChild(this.bg);
        this.bg.autoRelease();
    }

    /**
     * ADD TABLE.
     */
    private void addTable() {
        this.table = Sprite.make(Texture2D.makePNG(R.drawable.table));
        this.table.setPosition(this.wySize.width / 2.0f, 150.0f);
        addChild(this.table);
        this.table.autoRelease();
    }

    /**
     * ADD SUN.
     */
    private void addSun() {
        this.particleSystem = ParticleSun.make();
        addChild(this.particleSystem);
        sunPosition();
        this.particleSystem.autoRelease(true);
    }

    /**
     * ADD CLOUDS.
     */
    private void addClouds() {
        this.cloudsTwo = Sprite.make(Texture2D.makePNG(R.drawable.cloud7));
        this.cloudsTwo.setPosition(-400.0f, this.wySize.height - 130.0f);
        addChild(this.cloudsTwo);
        this.cloudsTwo.autoRelease(true);
        this.cloudsTwo.runAction(RepeatForever.
                make(MoveTo.make(50.0f, this.cloudsTwo.getPositionX(), this.cloudsTwo.getPositionY(),
                this.wySize.width + 400.0f, this.cloudsTwo.getPositionY())));
    }

    /**
     * SUN POSITION.
     */
    private void sunPosition() {
        this.particleSystem.setPosition(this.wySize.width - 100.0f, this.wySize.height - 120.0f);
        //this.particleSystem.setScale(2.0f);
        //this.particleSystem.setStartColorVariance(255.0f, 0.25f, 0.12f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        //this.particleSystem.setEndColorVariance(0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f);
    }

    /**
     * ADD HUDLAYER.
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
     * LOAD THE ZWOPTEX TEXTURE.
     */
    private void loadZwoptex() {
        ZwoptexManager.addZwoptex("glasses", R.raw.glasses_texture,
                Texture2D.makePNG(R.drawable.glasses_texture));
    }

    /**
     * ATTACH GLASS TO THE SCENE NODE.
     */
    private void setGlass() {
        loadZwoptex();
        this.glass = ZwoptexManager.makeSprite(SharedData.
                getInstance().player.usedGlass.getImageResourceName());
        this.glass.setPosition(this.wySize.width / 2.0f, 310.0f);
        addChild(this.glass);
        this.glass.autoRelease();
        this.glass.setZOrder(10);
    }

    /**
     * Glass Ingredient.
     */
    private void addGlassIngredient() {
        this.glassIngredient = Sprite.make(Texture2D.
                makePNG(R.drawable.glass_ingredient));
        this.glassIngredient.setPosition(this.glass.getWidth() / 2.0f,
                this.glass.getHeight() / 1.5f);
        this.glass.addChild(this.glassIngredient);
        this.glassIngredient.autoRelease();
        this.glassIngredient.setZOrder(-5);
        this.glassIngredient.setScale(1.0f, 1.1f);
        this.glassIngredient.setColor(SharedData.getInstance().player.usedFruits.getColor());
        this.glassIngredient.setVisible(false);
    }

    /**
     * STRAW.
     */
    private void addStraw() {
        this.strawSprite = ZwoptexManager.makeSprite("straw_01.png");
        this.strawSprite.setPosition(this.glass.getPositionX(),
                this.glass.getPositionY() + 100.0f);
        addChild(this.strawSprite);
        this.strawSprite.autoRelease();
        this.strawSprite.setVisible(false);
    }

    /**
     * Jug.
     */
    private void setJug() {
        this.jug = Sprite.make(Texture2D.makePNG(R.drawable.juicer));
        this.jug.setPosition(-300.0f, this.wySize.height / 1.3f);
        addChild(this.jug);
        this.jug.autoRelease(true);
        this.jug.setZOrder(9);
        //this.jug.setVisible(false);
    }

    /**
     * Jug Ingredient.
     */
    private void addJugIngredient() {
        this.jugIngredient = Sprite.make(Texture2D.
                makePNG(R.drawable.juicer_ingredient_pouring));
        this.jugIngredient.setPosition(this.jug.getWidth() / 2.0f + 19.0f,
                this.jug.getHeight() / 2.0f - 5.0f);
        this.jug.addChild(this.jugIngredient);
        this.jugIngredient.autoRelease();
        this.jugIngredient.setZOrder(-5);
        this.jugIngredient.setColor(SharedData.getInstance().player.usedFruits.getColor());
    }

    /**
     * Initialize Timer.
     */
    private void initTimer() {
        Object[] obj = new Object[1];
        obj[0] = Float.valueOf(0);
        this.pouringSelector = new TargetSelector(this, "onPouringTick(float)", obj);
        if (this.pouringTimer == null) {
            this.pouringTimer = new Timer(this.pouringSelector, 0.10f);
        } else {
            Scheduler.getInstance().unschedule(this.pouringTimer);
        }
    }

    /**
     *
     * @param r
     * @param g
     * @param b
     */
    private void setPourColor(float r, float g, float b) {
        this.pouringParticles.setStartColorVariance(r / 255.0f,
                g / 255.0f, b / 255.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f);
    }

    /**
     * Execute Drink Pouring.
     */
    public void executeDrinkPouring() {
        this.pouringParticles = ParticlePouring.make();
        this.pouringParticles.setScale(2.5f);
        this.pouringParticles.setPosition(this.glass.getPositionX()
                - 5.0f, this.jug.getPositionY() - this.jug.getHeight() / 4.0f - 5.0f);
        WYColor3B color = SharedData.getInstance().player.usedFruits.getColor();
        setPourColor(color.r, color.g, color.b);
        addChild(this.pouringParticles);
        this.pouringParticles.setZOrder(1);
        this.pouringParticles.autoRelease(true);
    }

    /**
     * ON POURING TICK.
     *
     * @param paramFloat
     */
    public void onPouringTick(float paramFloat) {
        this.clipHeightOffset = (1 + this.clipHeightOffset);
        this.clipHeightOffsetJug = (-1 + this.clipHeightOffsetJug);
        this.glassIngredient.setClipRect(WYRect.make(0.0f, 0.0f,
                this.glassIngredient.getWidth(), this.glassIngredient.
                getHeight() / 100.0f * this.clipHeightOffset), true);
        this.jugIngredient.setClipRect(WYRect.make(0.0f, 0.0f,
                this.jugIngredient.getWidth() / 100.0f * this.clipHeightOffsetJug,
                this.jugIngredient.getHeight()), true);
        SharedData.getInstance().soundsHandler.playPouringSound();
        if (this.clipHeightOffset >= this.MAX_CLIP_DIVISOR) {
            Scheduler.getInstance().unschedule(pouringTimer);
            this.pouringParticles.stopSystem();
            /**
             * update objects visibility
             */
            addButtons();
            IntervalAction rotate = (IntervalAction) RotateBy.make(0.69f, -90.0f).autoRelease();
            IntervalAction move = (IntervalAction) MoveTo.make(0.5f, this.jug.getPositionX(),
                    this.jug.getPositionY(), -300.0f, this.jug.getPositionY());
            this.jug.runAction(Sequence.make(rotate, move));
        }
    }

    /**
     * replace Drinks.
     */
    private void replaceDrinks() {
        DelayTime delay = DelayTime.make(1.0f);
        FiniteTimeAction[] finiteTimeAct = new FiniteTimeAction[2];
        finiteTimeAct[0] = CallFunc.make(this, "startPouringDrink");
        finiteTimeAct[1] = CallFunc.make(this, "displayDrinks");
        this.glassIngredient.runAction(Sequence.make(delay, finiteTimeAct));
    }

    /**
     * Display Drinks Deprecated in this game.
     */
    public void displayDrinks() {
    }

    /**
     * START POURING DRINK.
     */
    public void startPouringDrink() {
        this.jug.setVisible(true);
        this.jug.runAction(this.actionHandler.getMoveAction(0.209f, this.jug.getPositionX(),
                this.jug.getPositionY(), this.wySize.width / 2.0f - 120.0f,
                this.jug.getPositionY() - 20.0f));
        IntervalAction act1 = (IntervalAction) RotateBy.make(1.29f, 90.0f).autoRelease();
        CallFunc func = (CallFunc) CallFunc.make(this, "rotateJugActionEnded").
                autoRelease();
        FiniteTimeAction[] arrFiniteTimeAct = new FiniteTimeAction[1];
        arrFiniteTimeAct[0] = func;
        IntervalAction act2 = (IntervalAction) Sequence.make(act1, arrFiniteTimeAct);
        this.jug.runAction(act2);
    }

    /**
     * ROTATE JUG ACTION ENDED.
     */
    public void rotateJugActionEnded() {
        Scheduler.getInstance().schedule(pouringTimer);
        this.glassIngredient.setVisible(true);
        executeDrinkPouring();
    }

    /**
     * ADD BUTTONs.
     */
    private void addButtons() {
        //SCENE BUTTON
        this.sceneButt = Button.make(R.drawable.button_scene,
                R.drawable.button_scene2, this, "onSceneButtClicked");
        this.sceneButt.setPosition(170.0f, this.wySize.height + 100.0f);
        addChild(this.sceneButt);
        this.sceneButt.autoRelease();
        this.sceneButt.runAction(EaseElasticInOut.make(MoveTo.make(3.0f,
                this.sceneButt.getPositionX(), this.wySize.height + 100.0f,
                this.sceneButt.getPositionX(), this.wySize.height - 50.0f)));

        //STRAW BUTTON
        this.strawButt = Button.make(R.drawable.button_straw, R.drawable.button_straw2,
                this, "onStrawButtClicked");
        this.strawButt.setPosition(this.sceneButt.getPositionX() + 135.0f,
                this.sceneButt.getPositionY());
        addChild(this.strawButt);
        this.strawButt.autoRelease(true);
        this.strawButt.runAction(EaseElasticInOut.make(MoveTo.make(4.0f,
                this.strawButt.getPositionX(), this.wySize.height + 100.0f,
                this.strawButt.getPositionX(), this.wySize.height - 50.0f)));
    }
    
    /**
     *
     * @param gridModeCurrent
     */
    private void makeGridView(int gridModeCurrent) {
        this.hudLayer.updateHudObjectVisibility(false, false, false);
        if (this.gridLayer != null) {
            this.gridLayer.removeGridViewWithAction();
        }
        this.gridLayer = new GridLayer(this);
        this.gridLayer.setPosition(0.0f, this.wySize.height - 120.0f);
        addChild(this.gridLayer);
        this.gridLayer.autoRelease(true);
        this.gridLayer.setZOrder(50);
        bringToFront(this.gridLayer);
        this.gridLayer.populateGrid();
        this.gridLayer.runAction(MoveTo.make(1.0f, 0.0f,
                this.wySize.height + 800.0f, 0.0f, 0.0f));
    }

    /**
     * On SCENE BUTT CLICKED.
     */
    public void onSceneButtClicked() {
        SharedData.getInstance().soundsHandler.playButtonClickSound();
        Core.gridModeCurrent = Core.GRID_MODE_SCENES;
        makeGridView(Core.gridModeCurrent);
    }

    /**
     * On Straw Butt Clicked.
     */
    public void onStrawButtClicked() {
        SharedData.getInstance().soundsHandler.playButtonClickSound();
        Core.gridModeCurrent = Core.GRID_MODE_ADDON;
        makeGridView(Core.gridModeCurrent);
    }

    /**
     * MAKE ITEM LOCKED DIALOG.
     */
    private void makeItemLockedDialog() {
        ((Activity) Director.getInstance().getContext()).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final AlertDialog.Builder builder = new AlertDialog.Builder(Director.getInstance().getContext());
                builder.setIcon(R.drawable.algo_logo2).
                        setMessage("The Item requested is not available "
                        + "in Juice Maker Free version. Please upgrade to "
                        + "Juice Maker Pro version from"
                        + " Google Play Store. To Download now press 'Download' "
                        + "button, To Download later press"
                        + " 'Later' "
                        + "to exit").setTitle(" Alert")
                        .setPositiveButton("Download", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                         try {
                            ((Activity) Director.getInstance().getContext())
                                    .startActivity(new Intent("android.intent.action.VIEW", Uri
                                    .parse("market://details?id="
                                    + "com.algorithmi.maker.juice.main")));
                        } catch (ActivityNotFoundException ae) {
                            
                        }
                    }
                }).setNegativeButton("Later", new DialogInterface.OnClickListener() {
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

    /**
     * 
     * BACK BUTTON
     *
     * @return
     */
    @Override
    public boolean onBackButton() {
        SharedData.getInstance().soundsHandler.playButtonClickSound();
        Director.getInstance().replaceScene((TransitionScene) LeftPushInTransition.
                make(0.5f, new GlassSelection()));
        return true;
    }

    /**
     * ON SOFT BACK BUTTON CLICKED.
     */
    @Override
    public void onSoftBackButtonClicked() {
        onBackButton();
    }

    /**
     * ON SOFT BACK BUTTON CLICKED.
     */
    @Override
    public void onSoftNextButtonClicked() {
        SharedData.getInstance().soundsHandler.playButtonClickSound();
        Director.getInstance().replaceScene((TransitionScene) 
                RightPushInTransition.make(0.5f, new EndScene()));
    }

    /**
     * ON CROSS BUTTON CLICKED.
     */
    @Override
    public void onCrossButtonClicked() {
        this.hudLayer.updateHudObjectVisibility(true, false, false);
        this.gridLayer.removeGridViewWithAction();
        Core.gridModeCurrent = 0;
    }

    /**
     * ON GRID ITEM CLICKED.
     * 
     * @param paramString 
     */
    @Override
    public void onGridItemClicked(String paramString) {
        this.gridLayer.removeGridViewWithAction();
        switch (Core.gridModeCurrent) {
            case Core.GRID_MODE_SCENES:
                try {
                    for (int i = 0; i <= SharedData.getInstance().scenesList.size(); i++) {
                        if ((((Scenes) SharedData.getInstance().scenesList.get(i)).
                                getId().equalsIgnoreCase(paramString))) {
                            replaceBg(i);
                        } else if ((((Scenes) SharedData.getInstance().scenesList.get(i)).
                                isLocked())) {
                        }
                    }
                } catch (java.lang.IndexOutOfBoundsException e) {
                    Log.d("Muneeb: ", "Exception: ", e);
                }
                break;
            case Core.GRID_MODE_ADDON:
                try {
                    for (int i = 0; i <= SharedData.getInstance().addOnList.size(); i++) {
                        if (((AddOn) SharedData.getInstance().addOnList.get(i)).getId()
                                .equalsIgnoreCase(paramString)) {
                            this.strawSprite.setVisible(true);
                            replaceStraw(i);
                        } else if (((AddOn) SharedData.getInstance().addOnList.get(i)).
                                isLocked()) {
                        }
                    }
                } catch (java.lang.IndexOutOfBoundsException e) {
                    Log.d("Muneeb: ", "Exception: ", e);
                }
                break;
        }
    }

    /**
     * REPLACE BG.
     * 
     * @param index int  
     */
    private void replaceBg(int index) {
        if (((Scenes) SharedData.getInstance().scenesList.get(index)).isLocked()) {
            makeItemLockedDialog();
        } else {
            SharedData.getInstance().soundsHandler.playSelectedMusic();
            SharedData.getInstance().player.usedScenes = ((Scenes) SharedData.
                    getInstance().scenesList.get(index));
            this.bg.setDisplayFrame(ZwoptexManager.
                    makeSprite(SharedData.getInstance().player.usedScenes.
                    getImageResourceName()).makeFrame());
        }
        /* HudLayer update */
        this.hudLayer.updateHudObjectVisibility(true, true, false);
        this.hudLayer.setNextButtonPosition(this.wySize.width - 60.0f,
                this.wySize.height - 50.0f);
        HudLayer localHudLayer = this.hudLayer;
        this.hudLayer.getClass();
        localHudLayer.startNextButtonAction(4);
    }

    /**
     * REPLACE STRAW.
     * 
     * @param index 
     */
    private void replaceStraw(int index) {
        if (((AddOn) SharedData.getInstance().addOnList.get(index)).
                isLocked()) {
            makeItemLockedDialog();
        } else {
            SharedData.getInstance().soundsHandler.playSelectedMusic();
            SharedData.getInstance().player.usedAddOn = ((AddOn) SharedData.
                    getInstance().addOnList.get(index));
            this.strawSprite.setDisplayFrame(ZwoptexManager.makeSprite(SharedData.
                    getInstance().player.usedAddOn.getImageResourceName()).makeFrame());
        }
        /* HudLayer update */
        this.hudLayer.updateHudObjectVisibility(true, true, false);
        this.hudLayer.setNextButtonPosition(this.wySize.width - 60.0f,
                this.wySize.height - 50.0f);
        HudLayer localHudLayer = this.hudLayer;
        this.hudLayer.getClass();
        localHudLayer.startNextButtonAction(4);
        /**
         * 3D wave action
         */
        this.strawSprite.runAction(RepeatForever.make(Waves3D.make(5, 
                WYGridSize.make(12, 20), -20, 10)));
        //this.strawSprite.setPosition(this.wySize.width / 2.0f - 25.0f, 400.0f);
        this.strawSprite.setScale(1.3f);
    }
}/* end class. */
