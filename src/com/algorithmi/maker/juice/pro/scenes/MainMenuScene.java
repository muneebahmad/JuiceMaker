/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algorithmi.maker.juice.pro.scenes;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import com.algorithmi.maker.juice.pro.data.SharedData;
import com.algorithmi.maker.juice.pro.main.R;
import com.muneebahmad.microsun.hndl.ActionHandler;
import com.muneebahmad.microsun.hndl.SoundsHandler;
import com.muneebahmad.microsun.layers.PrivacyInterface;
import com.muneebahmad.microsun.layers.PrivacyLayer;
import com.muneebahmad.microsun.util.Core;
import com.muneebahmad.microsun.util.ParticleSun;
import com.wiyun.engine.actions.Action;
import com.wiyun.engine.actions.Animate;
import com.wiyun.engine.actions.MoveTo;
import com.wiyun.engine.actions.RepeatForever;
import com.wiyun.engine.actions.RotateTo;
import com.wiyun.engine.actions.ease.EaseElasticInOut;
import com.wiyun.engine.actions.ease.EaseElasticOut;
import com.wiyun.engine.dialog.Dialog;
import com.wiyun.engine.dialog.DialogPopupTransition;
import com.wiyun.engine.nodes.Animation;
import com.wiyun.engine.nodes.Animation.IAnimationCallback;
import com.wiyun.engine.nodes.Button;
import com.wiyun.engine.nodes.Director;
import com.wiyun.engine.nodes.Label;
import com.wiyun.engine.nodes.Scene;
import com.wiyun.engine.nodes.Sprite;
import com.wiyun.engine.opengl.Texture2D;
import com.wiyun.engine.particle.ParticleSystem;
import com.wiyun.engine.transitions.RightPushInTransition;
import com.wiyun.engine.transitions.TransitionScene;
import com.wiyun.engine.types.WYColor3B;
import com.wiyun.engine.types.WYSize;
import com.wiyun.engine.utils.TargetSelector;

/**
 *
 * @author muneebahmad
 */
public class MainMenuScene extends Scene
        implements IAnimationCallback, PrivacyInterface {

    private Sprite bg;
    private WYSize wySize;
    private Button playButton;
    private Button funButton;
    private Button rateButton;
    private Button endButton;
    private Button privacyButton;
    private Sprite clouds;
    private Sprite cloudsTwo;
    private ParticleSystem particleSystem;
    private ActionHandler actionHandler;
    private Sprite flyingBird;
    private Sprite logo;
    private Sprite sun;
    private Button breakfastLogo;
    private PrivacyLayer privacyLayer;

    /**
     *
     */
    public MainMenuScene() {
        this.wySize = Director.getInstance().getWindowSize();
        SoundsHandler.getInstance().playBackgroundFinalSound();
        this.actionHandler = new ActionHandler();
        setContents();
    }

    private void setContents() {
        addBgs();
        setLogo();
        addButtons();
        addSun();
        addClouds();
        addBirdOne();
        addBirdTwo();
    }

    private void addBgs() {
        this.bg = Sprite.make(R.drawable.bg_main);
        this.bg.setPosition(this.wySize.width / 2.0f, this.wySize.height / 2.0f);
        this.bg.setContentSize(this.wySize.width, this.wySize.height);
        this.bg.setAutoFit(true);
        addChild(this.bg);
        this.bg.autoRelease(true);
    }

    private void addButtons() {
        this.playButton = Button.make(R.drawable.play_btn1, R.drawable.explosion,
                this, "onPlayButtonClicked");
        //this.playButton.setPosition(this.wySize.width / 2.0f, this.wySize.height / 2.0f + 70.0f);
        this.playButton.setPosition(this.wySize.width / 2.0f, -250.0f);
        addChild(this.playButton);
        this.playButton.autoRelease(true);
        this.playButton.runAction(EaseElasticInOut.make(MoveTo.make(3.0f, this.playButton.getPositionX(),
                this.playButton.getPositionY(), this.playButton.getPositionX(), this.wySize.height / 2.0f + 80.0f)));
        /**
         * this.playButton.runAction(this.actionHandler.getRepeatingRotateAction(0.5f,
         * -25));
         * this.playButton.runAction(this.actionHandler.getRepeatingPointingAction(0.5f,
         * this.playButton.getPositionX(), this.playButton.getPositionY(),
         * this.playButton.getPositionX() + 20.0f,
         * this.playButton.getPositionY() + 10.0f)) ;
         *
         */
        this.funButton = Button.make(R.drawable.fun_btn1, R.drawable.explosion, this, "onFunButtonClicked");
        //this.funButton.setPosition(120, this.wySize.height / 2.0f);
        this.funButton.setPosition(this.wySize.width / 2.0f, -250.0f);
        addChild(this.funButton);
        this.funButton.autoRelease(true);
        this.funButton.runAction(EaseElasticInOut.make(MoveTo.make(3.0f, this.funButton.getPositionX(),
                this.funButton.getPositionY(), 120.0f, this.wySize.height / 2.0f + 80.0f)));
        /**
         * this.funButton.runAction(this.actionHandler.getRepeatingRotateAction(0.5f,
         * -25));
         * this.funButton.runAction(this.actionHandler.getRepeatingPointingAction(0.3f,
         * this.funButton.getPositionX(), this.funButton.getPositionY(),
         * this.funButton.getPositionX() + 20.0f, this.funButton.getPositionY()
         * + 10.0f)) ;
         *
         */
        this.rateButton = Button.make(R.drawable.rate1, R.drawable.explosion, this, "onRateButtonClicked");
        //this.rateButton.setPosition(this.wySize.width - 120.0f, this.wySize.height / 2.0f);
        this.rateButton.setPosition(this.wySize.width / 2.0f, -250.0f);
        addChild(this.rateButton);
        this.rateButton.autoRelease(true);
        this.rateButton.runAction(EaseElasticInOut.make(MoveTo.make(3.0f, this.rateButton.getPositionX(),
                this.rateButton.getPositionY(), this.wySize.width - 120.0f, this.wySize.height / 2.0f + 80.0f)));


        this.endButton = Button.make(R.drawable.button_exit, R.drawable.button_exit2, this, "onEndButtonClicked");
        //this.endButton.setPosition(this.wySize.width / 2.0f, this.wySize.height / 2.0f - 100.0f);
        this.endButton.setPosition(this.wySize.width + 200.0f, 50.0f);
        addChild(this.endButton);
        this.endButton.autoRelease(true);
        this.endButton.runAction(EaseElasticInOut.make(MoveTo.make(3.0f, this.endButton.getPositionX(),
                this.endButton.getPositionY(), this.wySize.width - 70.0f, this.endButton.getPositionY())));


        this.privacyButton = Button.make(R.drawable.button_privacy2, R.drawable.button_privacy3,
                this, "onPrivacyButtonClicked");
        this.privacyButton.setPosition(-200.0f, 50.0f);
        addChild(this.privacyButton);
        this.privacyButton.autoRelease(true);
        this.privacyButton.runAction(EaseElasticInOut.make(MoveTo.make(3.0f, this.privacyButton.getPositionX(),
                this.privacyButton.getPositionY(), 70.0f, this.privacyButton.getPositionY())));

        this.breakfastLogo = Button.make(R.drawable.breakfast_logo_icon, R.drawable.breakfast_logo_icon2,
                this, "onBreakfastLogoClicked");
        this.breakfastLogo.setPosition(this.wySize.width / 2.0f, -200.0f);
        addChild(this.breakfastLogo);
        this.breakfastLogo.autoRelease(true);
        this.breakfastLogo.runAction(EaseElasticInOut.make(MoveTo.make(2.0f, this.breakfastLogo.getPositionX(),
                this.breakfastLogo.getPositionY(), this.breakfastLogo.getPositionX(), 50.0f)));
    }

    private void addSun() {
        this.particleSystem = ParticleSun.make();
        addChild(this.particleSystem);
        sunPosition();
        this.particleSystem.autoRelease(true);

        this.particleSystem.runAction(RepeatForever.make(MoveTo.
                make(100.0f, this.particleSystem.getPositionX(),
                this.particleSystem.getPositionY(), this.wySize.width + 100.0f,
                this.wySize.height - 60.0f)));

        this.sun = Sprite.make(R.drawable.sun2);
        this.sun.setPosition(this.particleSystem.getWidth() / 2.0f,
                this.particleSystem.getHeight() / 2.0f);
        this.particleSystem.addChild(this.sun, 5);
        this.sun.autoRelease(true);
        this.sun.runAction(RepeatForever.make(RotateTo.make(10.0f, 0.0f, 359.0f)));
    }

    private void sunPosition() {
        this.particleSystem.setPosition(-100.0f, this.wySize.height - 320.0f);
        this.particleSystem.setScale(2.0f);
        this.particleSystem.setStartColorVariance(255.0f, 0.25f, 0.12f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.particleSystem.setEndColorVariance(0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f);
    }

    private void addClouds() {
        this.cloudsTwo = Sprite.make(Texture2D.makePNG(R.drawable.cloud7));
        this.cloudsTwo.setPosition(this.wySize.width + 400.0f, this.wySize.height - 80.0f);
        addChild(this.cloudsTwo);
        this.cloudsTwo.autoRelease(true);
        this.cloudsTwo.runAction(RepeatForever.
                make(MoveTo.make(50.0f, this.cloudsTwo.getPositionX(), this.cloudsTwo.getPositionY(),
                -400.0f, this.cloudsTwo.getPositionY())));
        this.clouds = Sprite.make(Texture2D.makePNG(R.drawable.cloud3));
        this.clouds.setPosition(this.wySize.width + 120.0f, this.wySize.height - 100.0f);
        addChild(this.clouds);
        this.clouds.autoRelease(true);
        this.clouds.setScale(0.9f);
        this.clouds.runAction(RepeatForever.make(MoveTo.
                make(12.0f, this.clouds.getPositionX(), this.clouds.getPositionY(),
                -120.0f, this.clouds.getPositionY())));
    }

    private void addBirdOne() {
        this.flyingBird = Sprite.make(R.drawable.flybird1);
        this.flyingBird.setPosition(-80.0f, this.wySize.height - 200.0f);
        addChild(this.flyingBird);
        this.flyingBird.autoRelease(true);

        Animation anim = (Animation) new Animation(0, 0.3f, R.drawable.flybird2,
                R.drawable.flybird1).autoRelease();
        anim.setCallback(this);
        Animate a = (Animate) Animate.make(anim).autoRelease();
        this.flyingBird.runAction((Action) RepeatForever.make(a).autoRelease());
        this.flyingBird.runAction(RepeatForever.make(MoveTo.make(25.0f, this.flyingBird.getPositionX(),
                this.flyingBird.getPositionY(), this.wySize.width + 80.0f, this.flyingBird.getPositionY())));
    }

    private void addBirdTwo() {
        this.flyingBird = Sprite.make(R.drawable.flybird2);
        this.flyingBird.setPosition(-90.0f, this.wySize.height - 220.0f);
        addChild(this.flyingBird);
        this.flyingBird.autoRelease(true);

        Animation anim = (Animation) new Animation(0, 0.25f, R.drawable.flybird1,
                R.drawable.flybird2).autoRelease();
        anim.setCallback(this);
        Animate a = (Animate) Animate.make(anim).autoRelease();
        this.flyingBird.runAction((Action) RepeatForever.make(a).autoRelease());
        this.flyingBird.runAction(RepeatForever.make(MoveTo.make(23.0f, this.flyingBird.getPositionX(),
                this.flyingBird.getPositionY(), this.wySize.width + 80.0f, this.flyingBird.getPositionY())));
    }

    private void setLogo() {
        this.logo = Sprite.make(Texture2D.makePNG(R.drawable.logo1));
        this.logo.setPosition(this.wySize.width / 2.0f, -500.0f);
        addChild(this.logo);
        this.logo.setZOrder(0);
        this.logo.autoRelease(true);
        this.logo.runAction(EaseElasticOut.make(MoveTo.make(1.0f, this.logo.getPositionX(),
                this.logo.getPositionY(), this.logo.getPositionX(), this.wySize.height / 2.0f - 100.0f)));
    }

    /**
     * PRIVACY LAYER
     *
     * @param privacyMode
     */
    private void makePrivacyView(int privacyMode) {
        if (this.privacyLayer != null) {
            this.privacyLayer.removePrivacyViewWithAction();
        }
        this.privacyLayer = new PrivacyLayer(this);
        this.privacyLayer.setPosition(0.0f, this.wySize.height - 50.0f);
        addChild(this.privacyLayer, 30);
        this.privacyLayer.autoRelease(true);
        this.privacyLayer.setZOrder(50);
        bringToFront(this.privacyLayer);
        this.privacyLayer.populatePrivacy();
        MoveTo localMoveTo = MoveTo.make(5.5f, 0.0f, 2000.0f, 0.0f, 0.0f);
        this.privacyLayer.runAction(EaseElasticOut.make(localMoveTo));
    }

    /**
     *
     */
    public void onBreakfastLogoClicked() {
        SharedData.getInstance().soundsHandler.playButtonClickSound();
        try {
            ((Activity) Director.getInstance().getContext())
                    .startActivity(new Intent("android.intent.action.VIEW", Uri
                    .parse("market://details?id="
                    + "com.algorithmi.maker.breakfast.free.main")));
        } catch (ActivityNotFoundException ae) {
            Log.e("Breakfast Click", "ERROR", ae);
        }
    }

    /**
     *
     */
    public void onPrivacyButtonClicked() {
        SharedData.getInstance().soundsHandler.playButtonClickSound();
        Core.privacyModeCurrent = 2;
        makePrivacyView(Core.privacyModeCurrent);
    }

    /**
     *
     */
    public void onPlayButtonClicked() {
        SoundsHandler.getInstance().playButtonClickSound();
        Director.getInstance().pushScene((TransitionScene) RightPushInTransition.
                make(0.5f, new FruitSelectionScene()));
    }

    /**
     *
     */
    public void onFunButtonClicked() {
        SoundsHandler.getInstance().playButtonClickSound();
        try {
            ((Activity) Director.getInstance().getContext())
                    .startActivity(new Intent("android.intent.action.VIEW", Uri
                    .parse("market://search?q=pub:Algorithmi")));
        } catch (ActivityNotFoundException ae) {
            Log.d("Tag MuneebAhmad", "ActivityNotFoundException", ae);
        }
    }

    /**
     *
     */
    public void onRateButtonClicked() {
        SoundsHandler.getInstance().playButtonClickSound();
        try {
            ((Activity) Director.getInstance().getContext())
                    .startActivity(new Intent("android.intent.action.VIEW", Uri
                    .parse("market://details?id="
                    + "com.algorithmi.maker.juice.pro.main")));
        } catch (ActivityNotFoundException ae) {
            
        }
    }

    /**
     *
     */
    public void onEndButtonClicked() {
        SoundsHandler.getInstance().playButtonClickSound();
        onBackButton();
    }

    /**
     *
     *
     * @return end buttons clicked callback
     */
    @Override
    public boolean onBackButton() {
        SoundsHandler.getInstance().playButtonClickSound();
        showExitDialog("cheri.ttf", R.drawable.yes_button_up, R.drawable.no_button_up,
                R.drawable.yes_button_down, R.drawable.no_button_down);
        return true;
    }

    /**
     *
     * @param i
     * @param i1
     */
    @Override
    public void onAnimationFrameChanged(int i, int i1) {
    }

    /**
     *
     * @param i
     */
    @Override
    public void onAnimationEnded(int i) {
    }

    /**
     *
     * @param paramFloat
     * @param paramObject
     */
    public void onNOButton(float paramFloat, Object paramObject) {
        ((Dialog) paramObject).dismiss(true);
        setEnabled(true);
    }

    /**
     *
     * @param paramFloat
     * @param paramObject
     *
     */
    public void onYESButton(float paramFloat, Object paramObject) {
        ((Dialog) paramObject).dismiss(true);
        setEnabled(true);
        Director.getInstance().popScene();
    }

    /**
     *
     * @param paramString
     * @param paramInt
     * @param paramInt2
     */
    protected void showExitDialog(String paramString, int yesUp,
            int noUp, int yesDn, int noDn) {
        setEnabled(false);
        Label localLabel1 = Label.make("\n" + "  Are you sure! you want" + "\n to exit? ", 32.0F,
                paramString, false, this.wySize.width - 50.0F);
        localLabel1.setColor(new WYColor3B(255, 255, 255));
        localLabel1.setAlignment(1);
        localLabel1.autoRelease(true);
        Sprite localSprite1 = Sprite.make(yesUp);
        localSprite1.setScaleY(1.20000004768371582031F);
        localSprite1.autoRelease(true);
        Sprite yesDown = Sprite.make(yesDn);
        yesDown.setScaleY(1.20000004768371582031F);
        yesDown.autoRelease(true);
        Label localLabel2 = Label.make("", 30.0F, paramString, false, 0F);
        localLabel2.setColor(new WYColor3B(0, 0, 0));
        localLabel2.setAlignment(1);
        localLabel2.setPosition(this.wySize.width / 2F - this.wySize.width
                / 8.0F, this.wySize.height / 2F - 40.0F);
        localLabel2.autoRelease(true);
        Sprite localSprite2 = Sprite.make(noUp);
        localSprite2.setScaleY(1.20000004768371582031F);
        localSprite2.autoRelease(true);
        Sprite noDown = Sprite.make(noDn);
        noDown.setScaleY(1.20000004768371582031F);
        noDown.autoRelease(true);
        Button yes = Button.make(yesUp, yesDn, null, null);
        Button no = Button.make(noUp, noDn, null, null);
        Sprite dBg = Sprite.make(R.drawable.dialog_bg);
        dBg.autoRelease(true);
        Label localLabel3 = Label.make("", 30.0F, paramString, false, 0F);
        localLabel3.setColor(new WYColor3B(0, 0, 0));
        localLabel3.setAlignment(1);
        localLabel3.setPosition(this.wySize.width / 2F + this.wySize.width
                / 8.0F, this.wySize.height / 2F - 40.0F);
        localLabel3.autoRelease(true);
        Dialog localDialog = Dialog.make();
        localDialog.setColor(new WYColor3B(0, 0, 0));
        localDialog.setBackground(dBg);
        localDialog.setTitle(localLabel1);
        localDialog.setBackgroundPadding(110.0F, 40.0F, 0F, 40.0F);
        Object[] arrayOfObject1 = new Object[2];
        arrayOfObject1[0] = Float.valueOf(0F);
        arrayOfObject1[1] = localDialog;
        TargetSelector localTargetSelector = new TargetSelector(this,
                "onYESButton(float,Object)", arrayOfObject1);
        Object[] arrayOfObject2 = new Object[2];
        arrayOfObject2[0] = Float.valueOf(0F);
        arrayOfObject2[1] = localDialog;
        localDialog.addTwoColumnsButton(yes, localLabel2,
                localTargetSelector, no, localLabel3,
                new TargetSelector(this, "onNOButton(float,Object)",
                arrayOfObject2));
        localDialog.setTransition(DialogPopupTransition.make());
        localDialog.setBackKeyEquivalentButtonIndex(1);
        localDialog.show(true);
        localDialog.autoRelease(true);
    }/* exit dialog */


    @Override
    public void onCloseButtonClicked() {
        this.privacyLayer.removePrivacyViewWithAction();
        Core.privacyModeCurrent = 0;
    }
}//end class
