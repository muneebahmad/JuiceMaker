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
import android.view.MotionEvent;
import android.widget.Toast;
import com.algorithmi.maker.juice.pro.data.SharedData;
import com.algorithmi.maker.juice.pro.main.R;
import com.algorithmi.maker.juice.pro.model.Glass;
import com.muneebahmad.microsun.hndl.ActionHandler;
import com.muneebahmad.microsun.interfaces.AlmirahListener;
import com.muneebahmad.microsun.interfaces.HudLayerInterface;
import com.muneebahmad.microsun.layers.GlassSupport;
import com.muneebahmad.microsun.layers.HudLayer;
import com.muneebahmad.microsun.util.ParticleFlower;
import com.vungle.sdk.VunglePub;
import com.wiyun.engine.actions.MoveTo;
import com.wiyun.engine.nodes.Director;
import com.wiyun.engine.nodes.Scene;
import com.wiyun.engine.nodes.Sprite;
import com.wiyun.engine.opengl.Texture2D;
import com.wiyun.engine.particle.ParticleSystem;
import com.wiyun.engine.transitions.LeftPushInTransition;
import com.wiyun.engine.transitions.RightPushInTransition;
import com.wiyun.engine.transitions.TransitionScene;
import com.wiyun.engine.types.WYColor3B;
import com.wiyun.engine.types.WYPoint;
import com.wiyun.engine.types.WYSize;
import com.wiyun.engine.utils.ZwoptexManager;

/**
 *
 * @author muneebahmad
 */
public class GlassSelection extends Scene implements
        HudLayerInterface, AlmirahListener {

    private WYSize wySize;
    private ActionHandler actionHandler;
    private Sprite bg;
    private Sprite almirah;
    private Sprite almirahGate;
    private final int MODE_TOUCH_GATE = 5;
    private final int MODE_TOUCH_GLASS = 5;
    private final int MODE_GATE_CLOSED = 1;
    private final int MODE_GATE_OPEN = 2;
    private final int GLASS_MODE_TOUCH_ENABLED_FALSE = 0;
    private final int GLASS_MODE_TOUCH_ENABLED_TRUE = 1;
    private int glassMode = 0;
    private int gateState = 0;
    private int mode = 0;
    private int gridMode = 0;
    private HudLayer hudLayer;
    private ParticleSystem particle;
    private GlassSupport glassSupport;
    private Sprite glass01;
    private Sprite glass02;
    private Sprite glass03;
    private Sprite glass04;
    private Sprite glass05;
    private Sprite glass06;
    private Sprite glass07;
    private Sprite glass08;
    private Sprite glass09;
    private Sprite glass10;
    private Sprite lock;

    /**
     * Constructor.
     */
    public GlassSelection() {
        this.wySize = Director.getInstance().getWindowSize();
        this.actionHandler = new ActionHandler();
        this.glassSupport = new GlassSupport(this);
        setTouchEnabled(true);
        this.gateState = MODE_GATE_CLOSED;
        this.mode = MODE_TOUCH_GATE;
        setContents();
    }

    private void setContents() {
        addChild(setBg());
        addAlmirah();
        populateGlasses();
        addGate();
        addFlowerParticle();
        addHudLayer();
    }

    private Sprite setBg() {
        this.bg = Sprite.make(Texture2D.makePNG(R.drawable.bg_glasselec));
        this.bg.setPosition(this.wySize.width / 2.0f, this.wySize.height / 2.0f);
        this.bg.setContentSize(this.wySize.width, this.wySize.height);
        this.bg.setAutoFit(true);
        this.bg.autoRelease();
        return this.bg;
    }

    /**
     *
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
     * LOAD THE ZWOPTEX TEXTURE
     */
    private void loadZwoptex() {
        ZwoptexManager.addZwoptex("glasses", R.raw.glasses_texture,
                Texture2D.makePNG(R.drawable.glasses_texture));
    }

    private void populateGlasses() {
        loadZwoptex();
        /**
         * glass01
         */
        this.glass01 = ZwoptexManager.makeSprite("glass01.png");
        this.glass01.setPosition(WYPoint.make(150.0f, 655.0f));
        addChild(this.glass01);
        this.glass01.setScale(0.3f);
        this.glass01.autoRelease();
        if (SharedData.getInstance().glassList.get(0).isLocked()) {
            Sprite sprite = Sprite.make(Texture2D.makePNG(R.drawable.lock_1));
            sprite.setScale(2.0f);
            sprite.setPosition(glass01.getWidth() - 25.0f,
                    40.0f);
            glass01.addChild(sprite);
            sprite.autoRelease();
        }

        /**
         * glass02
         */
        this.glass02 = ZwoptexManager.makeSprite("glass02.png");
        this.glass02.setPosition(WYPoint.make(this.wySize.width - 150.0f, 655.0f));
        addChild(this.glass02);
        this.glass02.setScale(0.3f);
        this.glass02.autoRelease();
        if (SharedData.getInstance().glassList.get(1).isLocked()) {
            Sprite sprite = Sprite.make(Texture2D.makePNG(R.drawable.lock_1));
            sprite.setScale(2.0f);
            sprite.setPosition(glass02.getWidth() - 25.0f,
                    40.0f);
            glass02.addChild(sprite);
            sprite.autoRelease();
        }
        /**
         * glass03
         */
        this.glass03 = ZwoptexManager.makeSprite("glass03.png");
        this.glass03.setPosition(WYPoint.make(150.0f, 530));
        addChild(this.glass03);
        this.glass03.setScale(0.3f);
        this.glass03.autoRelease();
        if (SharedData.getInstance().glassList.get(2).isLocked()) {
            Sprite sprite = Sprite.make(Texture2D.makePNG(R.drawable.lock_1));
            sprite.setScale(2.0f);
            sprite.setPosition(glass03.getWidth() - 25.0f,
                    40.0f);
            glass03.addChild(sprite);
            sprite.autoRelease();
        }

        /**
         * glass04
         */
        this.glass04 = ZwoptexManager.makeSprite("glass04.png");
        this.glass04.setPosition(WYPoint.make(this.wySize.width - 150.0f, 530.0f));
        addChild(this.glass04);
        this.glass04.setScale(0.3f);
        this.glass04.autoRelease();
        if (SharedData.getInstance().glassList.get(3).isLocked()) {
            Sprite sprite = Sprite.make(Texture2D.makePNG(R.drawable.lock_1));
            sprite.setScale(2.0f);
            sprite.setPosition(glass04.getWidth() - 25.0f,
                    40.0f);
            glass04.addChild(sprite);
            sprite.autoRelease();
        }

        /**
         * glass05
         */
        this.glass05 = ZwoptexManager.makeSprite("glass05.png");
        this.glass05.setPosition(WYPoint.make(150.0f, 400));
        addChild(this.glass05);
        this.glass05.setScale(0.3f);
        this.glass05.autoRelease();
        if (SharedData.getInstance().glassList.get(4).isLocked()) {
            Sprite sprite = Sprite.make(Texture2D.makePNG(R.drawable.lock_1));
            sprite.setScale(2.0f);
            sprite.setPosition(glass05.getWidth() - 25.0f,
                    40.0f);
            glass05.addChild(sprite);
            sprite.autoRelease();
        }

        /**
         * glass06
         */
        this.glass06 = ZwoptexManager.makeSprite("glass06.png");
        this.glass06.setPosition(WYPoint.make(this.wySize.width - 150.0f, 400.0f));
        addChild(this.glass06);
        this.glass06.setScale(0.3f);
        this.glass06.autoRelease();
        if (SharedData.getInstance().glassList.get(5).isLocked()) {
            Sprite sprite = Sprite.make(Texture2D.makePNG(R.drawable.lock_1));
            sprite.setScale(2.0f);
            sprite.setPosition(glass06.getWidth() - 25.0f,
                    40.0f);
            glass06.addChild(sprite);
            sprite.autoRelease();
        }

        /**
         * glass07
         */
        this.glass07 = ZwoptexManager.makeSprite("glass07.png");
        this.glass07.setPosition(WYPoint.make(150.0f, 275));
        addChild(this.glass07);
        this.glass07.setScale(0.3f);
        this.glass07.autoRelease();
        if (SharedData.getInstance().glassList.get(6).isLocked()) {
            Sprite sprite = Sprite.make(Texture2D.makePNG(R.drawable.lock_1));
            sprite.setScale(2.0f);
            sprite.setPosition(glass07.getWidth() - 25.0f,
                    40.0f);
            glass07.addChild(sprite);
            sprite.autoRelease();
        }

        /**
         * glass08
         */
        this.glass08 = ZwoptexManager.makeSprite("glass08.png");
        this.glass08.setPosition(WYPoint.make(this.wySize.width - 150.0f, 275.0f));
        addChild(this.glass08);
        this.glass08.setScale(0.3f);
        this.glass08.autoRelease();
        if (SharedData.getInstance().glassList.get(7).isLocked()) {
            Sprite sprite = Sprite.make(Texture2D.makePNG(R.drawable.lock_1));
            sprite.setScale(2.0f);
            sprite.setPosition(glass08.getWidth() - 25.0f,
                    40.0f);
            glass08.addChild(sprite);
            sprite.autoRelease();
        }

        /**
         * glass09
         */
        this.glass09 = ZwoptexManager.makeSprite("glass09.png");
        this.glass09.setPosition(WYPoint.make(150.0f, 133.0f));
        addChild(this.glass09);
        this.glass09.setScale(0.3f);
        this.glass09.autoRelease();
        if (SharedData.getInstance().glassList.get(8).isLocked()) {
            Sprite sprite = Sprite.make(Texture2D.makePNG(R.drawable.lock_1));
            sprite.setScale(2.0f);
            sprite.setPosition(glass09.getWidth() - 25.0f,
                    40.0f);
            glass09.addChild(sprite);
            sprite.autoRelease();
        }

        /**
         * glass10
         */
        this.glass10 = ZwoptexManager.makeSprite("glass10.png");
        this.glass10.setPosition(WYPoint.make(this.wySize.width - 150.0f, 133.0f));
        addChild(this.glass10);
        this.glass10.setScale(0.3f);
        this.glass10.autoRelease();
        if (SharedData.getInstance().glassList.get(9).isLocked()) {
            Sprite sprite = Sprite.make(Texture2D.makePNG(R.drawable.lock_1));
            sprite.setScale(2.0f);
            sprite.setPosition(glass10.getWidth() - 25.0f,
                    40.0f);
            glass10.addChild(sprite);
            sprite.autoRelease();
        }
    }

    private void addAlmirah() {
        this.almirah = Sprite.make(Texture2D.makePNG(R.drawable.almirah_indside));
        this.almirah.setPosition(this.wySize.width / 2.0f, this.wySize.height / 2.0f);
        addChild(this.almirah);
        this.almirah.autoRelease(true);
    }

    private void addGate() {
        this.almirahGate = Sprite.make(Texture2D.makePNG(R.drawable.almirah_door));
        this.almirahGate.setPosition(this.wySize.width / 2.0f + 5.0f, this.wySize.height / 2.0f);
        addChild(this.almirahGate);
        this.almirahGate.autoRelease();
    }

    /**
     * FlowerParticle.
     */
    private void addFlowerParticle() {
        this.particle = ParticleFlower.make();
        this.particle.setPosition(this.wySize.width + 300.0f, this.wySize.height / 2.0f);
        addChild(this.particle);
    }

    /**
     * onGlassButtonClicked().
     */
    public void onGlassClicked(String paramString) {
        switch (this.gridMode) {
            case 1:
                try {
                    for (int i = 0; i <= SharedData.getInstance().glassList.size(); i++) {
                        if ((((Glass) SharedData.getInstance().glassList.get(i)).
                                getId().equalsIgnoreCase(paramString))) {
                            SharedData.getInstance().player.usedGlass = ((Glass) SharedData.
                                    getInstance().glassList.get(i));
                            ((Activity) Director.getInstance().getContext()).runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(Director.getInstance().getContext(), "Button Clicked",
                                            Toast.LENGTH_LONG).show();
                                }
                            });
                        } else if ((((Glass) SharedData.getInstance().glassList.
                                get(i)).isLocked())) {
                        }
                    }
                } catch (final java.lang.IndexOutOfBoundsException e) {
                    Log.d("ALGORITHMI", "Index Out of bound exception: ", e);
                    ((Activity) Director.getInstance().getContext()).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            AlertDialog.Builder builder = new AlertDialog.Builder(Director.getInstance().getContext());
                            builder.setIcon(R.drawable.algo_logo2).setMessage(" ERROR: " + e).
                                    setPositiveButton("KILL PROESS", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {
                                    arg0.cancel();
                                }
                            });
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                    });
                }
        }
    }

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

    private void setPouringScene() {
        Director.getInstance().replaceScene((TransitionScene) RightPushInTransition.make(0.5f,
                new PouringScene()));
        VunglePub.displayAdvert();
    }

    @Override
    public boolean onBackButton() {
        SharedData.getInstance().soundsHandler.playButtonClickSound();
        Director.getInstance().replaceScene((TransitionScene) LeftPushInTransition.
                make(0.5f, new FruitSelectedScene()));
        return true;
    }

    @Override
    public void onSoftBackButtonClicked() {
        onBackButton();
    }

    @Override
    public void onSoftNextButtonClicked() {
    }

    /**
     *
     * @param event android.view.MotionEvent
     * @return boolean true
     */
    @Override
    public boolean wyTouchesBegan(MotionEvent event) {
        WYPoint point = Director.getInstance().convertToGL(event.getX(), event.getY());
        switch (this.mode) {
            case MODE_TOUCH_GATE:
                if ((this.almirahGate.getBoundingBoxRelativeToWorld().containsPoint(point))
                        && (this.gateState == MODE_GATE_CLOSED)) {
                    this.almirahGate.runAction(MoveTo.make(1.0f,
                            this.almirahGate.getPositionX(), this.almirahGate.getPositionY(),
                            this.wySize.width / 2.0f - (this.almirahGate.getWidth() - 50.0f),
                            this.almirahGate.getPositionY()));

                    this.particle.runAction(MoveTo.make(1.0f, this.particle.getPositionX(),
                            point.y, -300.0f, point.y));

                    this.gateState = MODE_GATE_OPEN;
                    this.glassMode = GLASS_MODE_TOUCH_ENABLED_TRUE;
                } else if (this.almirahGate.getBoundingBoxRelativeToWorld().containsPoint(point)
                        && (this.gateState == MODE_GATE_OPEN)) {
                    this.almirahGate.runAction(MoveTo.make(1.0f, this.almirahGate.getPositionX(),
                            this.almirahGate.getPositionY(), this.wySize.width / 2.0f,
                            this.almirahGate.getPositionY()));

                    this.particle.runAction(MoveTo.make(1.0f, this.particle.getPositionX(),
                            point.y, this.wySize.width + 300.0f, point.y));
                    this.gateState = MODE_GATE_CLOSED;
                    this.glassMode = GLASS_MODE_TOUCH_ENABLED_FALSE;
                }



                //glass01
                if ((this.glass01.getBoundingBoxRelativeToWorld().containsPoint(point))
                        && (this.almirahGate.getPositionX() == this.wySize.width / 2.0f
                        - (this.almirahGate.getWidth() - 50.0f))) {
                    if (SharedData.getInstance().glassList.get(0).isLocked()) {
                        makeItemLockedDialog();
                    } else {
                        SharedData.getInstance().player.usedGlass = SharedData.getInstance().glassList.get(0);
                        setPouringScene();
                    }
                } else //glass02
                if ((this.glass02.getBoundingBoxRelativeToWorld().containsPoint(point))
                        && (this.almirahGate.getPositionX() == this.wySize.width / 2.0f
                        - (this.almirahGate.getWidth() - 50.0f))) {
                    if (SharedData.getInstance().glassList.get(1).isLocked()) {
                        makeItemLockedDialog();
                    } else {
                        SharedData.getInstance().player.usedGlass = SharedData.getInstance().glassList.get(1);
                        setPouringScene();
                    }
                } else //glass03
                if ((this.glass03.getBoundingBoxRelativeToWorld().containsPoint(point))
                        && (this.almirahGate.getPositionX() == this.wySize.width / 2.0f
                        - (this.almirahGate.getWidth() - 50.0f))) {
                    if (SharedData.getInstance().glassList.get(2).isLocked()) {
                        makeItemLockedDialog();
                    } else {
                        SharedData.getInstance().player.usedGlass = SharedData.getInstance().glassList.get(2);
                        setPouringScene();
                    }
                } else //glass04
                if ((this.glass04.getBoundingBoxRelativeToWorld().containsPoint(point))
                        && (this.almirahGate.getPositionX() == this.wySize.width / 2.0f
                        - (this.almirahGate.getWidth() - 50.0f))) {
                    if (SharedData.getInstance().glassList.get(3).isLocked()) {
                        makeItemLockedDialog();
                    } else {
                        SharedData.getInstance().player.usedGlass = SharedData.getInstance().glassList.get(3);
                        setPouringScene();
                    }
                } else //glass05
                if ((this.glass05.getBoundingBoxRelativeToWorld().containsPoint(point))
                        && (this.almirahGate.getPositionX() == this.wySize.width / 2.0f
                        - (this.almirahGate.getWidth() - 50.0f))) {
                    if (SharedData.getInstance().glassList.get(4).isLocked()) {
                        makeItemLockedDialog();
                    } else {
                        SharedData.getInstance().player.usedGlass = SharedData.getInstance().glassList.get(4);
                        setPouringScene();
                    }
                } else //glass06
                if ((this.glass06.getBoundingBoxRelativeToWorld().containsPoint(point))
                        && (this.almirahGate.getPositionX() == this.wySize.width / 2.0f
                        - (this.almirahGate.getWidth() - 50.0f))) {
                    if (SharedData.getInstance().glassList.get(5).isLocked()) {
                        makeItemLockedDialog();
                    } else {
                        SharedData.getInstance().player.usedGlass = SharedData.getInstance().glassList.get(5);
                        setPouringScene();
                    }
                } else //glass07
                if ((this.glass07.getBoundingBoxRelativeToWorld().containsPoint(point))
                        && (this.almirahGate.getPositionX() == this.wySize.width / 2.0f
                        - (this.almirahGate.getWidth() - 50.0f))) {
                    if (SharedData.getInstance().glassList.get(6).isLocked()) {
                        makeItemLockedDialog();
                    } else {
                        SharedData.getInstance().player.usedGlass = SharedData.getInstance().glassList.get(6);
                        setPouringScene();
                    }
                } else //glass08
                if ((this.glass08.getBoundingBoxRelativeToWorld().containsPoint(point))
                        && (this.almirahGate.getPositionX() == this.wySize.width / 2.0f
                        - (this.almirahGate.getWidth() - 50.0f))) {
                    if (SharedData.getInstance().glassList.get(7).isLocked()) {
                        makeItemLockedDialog();
                    } else {
                        SharedData.getInstance().player.usedGlass = SharedData.getInstance().glassList.get(7);
                        setPouringScene();
                    }
                } else //glass09
                if ((this.glass09.getBoundingBoxRelativeToWorld().containsPoint(point))
                        && (this.almirahGate.getPositionX() == this.wySize.width / 2.0f
                        - (this.almirahGate.getWidth() - 50.0f))) {
                    if (SharedData.getInstance().glassList.get(8).isLocked()) {
                        makeItemLockedDialog();
                    } else {
                        SharedData.getInstance().player.usedGlass = SharedData.getInstance().glassList.get(8);
                        setPouringScene();
                    }
                } else //glass10
                if ((this.glass10.getBoundingBoxRelativeToWorld().containsPoint(point))
                        && (this.almirahGate.getPositionX() == this.wySize.width / 2.0f
                        - (this.almirahGate.getWidth() - 50.0f))) {
                    if (SharedData.getInstance().glassList.get(9).isLocked()) {
                        makeItemLockedDialog();
                    } else {
                        SharedData.getInstance().player.usedGlass = SharedData.getInstance().glassList.get(9);
                        setPouringScene();
                    }
                }

                break;
            default:
                break;
        }
        return true;
    }

    /**
     *
     * @param paramString
     */
    public void onGlassButtonClicked(String paramString) {
    }
}/* end class */
