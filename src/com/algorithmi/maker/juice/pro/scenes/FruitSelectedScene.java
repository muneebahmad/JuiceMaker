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
import android.os.Vibrator;
import android.view.MotionEvent;
import com.algorithmi.maker.juice.pro.data.SharedData;
import com.algorithmi.maker.juice.pro.main.R;
import com.heyzap.sdk.ads.InterstitialAd;
import com.muneebahmad.microsun.hndl.ActionHandler;
import com.muneebahmad.microsun.interfaces.HudLayerInterface;
import com.muneebahmad.microsun.layers.HudLayer;
import com.muneebahmad.microsun.util.ParticleFlower;
import com.wiyun.engine.actions.Animate;
import com.wiyun.engine.actions.FadeIn;
import com.wiyun.engine.actions.MoveTo;
import com.wiyun.engine.actions.RepeatForever;
import com.wiyun.engine.actions.ScaleTo;
import com.wiyun.engine.actions.ease.EaseBackIn;
import com.wiyun.engine.actions.ease.EaseBackOut;
import com.wiyun.engine.actions.ease.EaseBounceInOut;
import com.wiyun.engine.actions.ease.EaseElasticInOut;
import com.wiyun.engine.actions.grid.Waves3D;
import com.wiyun.engine.box2d.Box2D;
import com.wiyun.engine.box2d.PELoader;
import com.wiyun.engine.box2d.collision.EdgeShape;
import com.wiyun.engine.box2d.dynamics.Body;
import com.wiyun.engine.box2d.dynamics.BodyDef;
import com.wiyun.engine.box2d.dynamics.World;
import com.wiyun.engine.nodes.Animation;
import com.wiyun.engine.nodes.Button;
import com.wiyun.engine.nodes.Director;
import com.wiyun.engine.nodes.Scene;
import com.wiyun.engine.nodes.Sprite;
import com.wiyun.engine.opengl.Texture2D;
import com.wiyun.engine.particle.ParticleSystem;
import com.wiyun.engine.transitions.LeftPushInTransition;
import com.wiyun.engine.transitions.RightPushInTransition;
import com.wiyun.engine.transitions.TransitionScene;
import com.wiyun.engine.types.WYColor3B;
import com.wiyun.engine.types.WYGridSize;
import com.wiyun.engine.types.WYPoint;
import com.wiyun.engine.types.WYSize;
import com.wiyun.engine.utils.TargetSelector;
import com.wiyun.engine.utils.Utilities;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author muneebahmad ahmadgallian@yahoo.com To all entities following source
 * code is provided AS IT IS, WITHOUT ANY WARRENTIES OF ANY KIND
 *
 */
public class FruitSelectedScene extends Scene
        implements HudLayerInterface, Animation.IAnimationCallback {

    private Sprite bg;
    private ActionHandler actionHandler;
    private HudLayer hudLayer;
    private WYSize wySize;
    private Sprite juicer;
    private Sprite juicerCap;
    private Sprite plateSprite;
    private Sprite fruitSprite;
    private Body fruitBody;
    private ArrayList<Body> mBodyList;
    Body juicerBody;
    int x = 0;
    int y = 0;
    int z = 0;
    private final int _MODE_FRUIT_ = 1;
    private final int _MODE_ICE_ = 2;
    private int mode;
    private Button capButton;
    private Button juicerButton;
    private ParticleSystem particle;
    private Sprite tap;
    public Sprite juicerIngr;
    private Sprite switchSprite;
    Sprite sprite;
    private Vibrator vibrator;
    /**
     * fruits resIds[]
     */
    private int resIds[] = {
        R.drawable.falling_pomegranate01,
        R.drawable.falling_pineapple01,
        R.drawable.falling_pear01,
        R.drawable.falling_orrange01,
        R.drawable.falling_mango01,
        R.drawable.falling_grapes01,
        R.drawable.falling_strabery01,
        R.drawable.falling_banana01,
        R.drawable.falling_apple01
    };
    /**
     * fruitNames[]
     */
    private String fruitNames[] = {
        "falling_pomegranate01",
        "falling_pineapple01",
        "falling_pear01",
        "falling_orrange01",
        "falling_mango01",
        "falling_grapes01",
        "falling_strabery01",
        "falling_banana01",
        "falling_apple01"
    };
    /**
     * ice resIds int
     */
    private int iceResIds = R.drawable.falling_ice01;
    /**
     * iceName String
     */
    private String iceName = "falling_ice01";
    
    /**
     * Juicer resId
     */
    private final int _JUICER_RESID_ = R.drawable.juicer2;
    /**
     * Juicer name
     */
    private final String _JUICER_NAME_ = "juicer2";
    /**
     * BOX2D
     */
    private Box2D mBox2D;
    private Box2D box2d;
    /**
     * WORLD
     */
    private World mWorld;
    /**
     * PhysicsEditorLoader
     */
    private PELoader mBodyLoader;
    private Random rand;

    /**
     * Default Constructor.
     */
    public FruitSelectedScene() {
        this.wySize = Director.getInstance().getWindowSize();
        this.actionHandler = new ActionHandler();
        this.mode = _MODE_FRUIT_;
        setBg();

        setTouchEnabled(true);
        addFruits();
        setContents();
    }

    /**
     * setContents()
     */
    private void setContents() {
        setJuicer();
        addHudLayer();
        addTapHere();
    }

    /**
     *
     */
    private void setBg() {
        this.bg = Sprite.make(Texture2D.makePNG(R.drawable.bg_fruitselected));
        this.bg.setPosition(this.wySize.width / 2.0f, this.wySize.height / 2.0f);
        this.bg.setContentSize(this.wySize.width, this.wySize.height);
        this.bg.setAutoFit(true);
        addChild(this.bg);
        this.bg.autoRelease(true);
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

    private void addTapHere() {
        this.tap = Sprite.make(Texture2D.makePNG(R.drawable.tap_logo));
        this.tap.setPosition(this.wySize.width / 2.0f, this.wySize.height - 200.0f);
        addChild(this.tap);
        this.tap.autoRelease();

        this.tap.runAction(EaseBackOut.make(ScaleTo.make(0.5f, 0.0f, 1.0f)));
    }

    /**
     * JUICER
     */
    private void setJuicer() {
        PELoader pLoader;
        mBox2D = Box2D.make();
        //mBox2D.setDebugDraw(true);
        mBox2D.setPosition(120.0f, 150.0f);
        addChild(mBox2D);

        //mWorld = mBox2D.getWorld();
        //mWorld.setGravity(0, -10);

        pLoader = PELoader.make(R.raw.juicer2);
        mBox2D.setMeterPixels(pLoader.getMeterPixels());

        juicerBody = pLoader.createBodyByName(mBox2D, _JUICER_NAME_);
        juicerBody.setType(Body.TYPE_STATIC);

        this.juicer = Sprite.make(Texture2D.makePNG(_JUICER_RESID_));
        this.juicer.setPosition(0.0f, 0.0f);
        this.juicer.autoRelease();

        WYPoint anchor = pLoader.getAnchorPercent(_JUICER_NAME_);
        this.juicer.setAnchor(anchor.x, anchor.y);
        //juicerBody.setUserData(this.juicer);
        mBox2D.addChild(this.juicer);

        /**
         * JUICER CAP
         */
        this.juicerCap = Sprite.make(Texture2D.makePNG(R.drawable.juicer2_cap));
        //this.juicerCap.setPosition(this.wySize.width / 2.0f, this.wySize.height / 2.0f + 10.0f);
        this.juicerCap.setPosition(this.wySize.width / 2.0f, this.wySize.height + 400.0f);
        addChild(this.juicerCap);
        this.juicerCap.autoRelease(true);

    }

    /**
     * This adds the box2d items of fruit and ice and adds the ground. returns
     * nothing.
     */
    private void addFruits() {
        rand = new Random(10);
        mBodyList = new ArrayList<Body>();
        setTouchEnabled(true);

        box2d = Box2D.make();
        //box2d.setDebugDraw(true);
        box2d.setPosition(120.0f, 150.0f);
        addChild(box2d);

        mWorld = box2d.getWorld();
        mWorld.setGravity(0, -10.0f);

        mBodyLoader = PELoader.make(R.raw.fruits);

        box2d.setMeterPixels(mBodyLoader.getMeterPixels());

        float meterWidth = box2d.pixel2Meter(this.wySize.width);

        {
            Body ground = null;
            BodyDef bd = BodyDef.make();
            ground = mWorld.createBody(bd);
            bd.destroy();
            //
            EdgeShape shape = EdgeShape.make();
            //Floor
            shape.setEndpoints(1.3f, 5.5f, 6.4f, 5.5f);
            ground.createFixture(shape, 0.0f);

            //left wall
            shape.setEndpoints(1.3f, 5.5f, 1.3f, 10.6f);
            ground.createFixture(shape, 0.0f);

            //right wall
            shape.setEndpoints(6.4f, 5.5f, 6.4f, 10.6f);
            ground.createFixture(shape, 0.0f);

            //center floor
            shape.setEndpoints(0.7f, 4.8f, 6.9f, 4.8f);
            ground.createFixture(shape, 0.0f);

            //table floor
            shape.setEndpoints(-2.8f, 1.0f, 10.5f, 1.0f);
            ground.createFixture(shape, 0.0f);

            //ground floor
            shape.setEndpoints(-3.7f, -3.8f, meterWidth, -3.8f);
            ground.createFixture(shape, 1.0f);

            //left juicer bottom wall
            shape.setEndpoints(1.0f, 1.0f, 0.7f, 4.8f);
            ground.createFixture(shape, 0.0f);

            //right juicer bottom wall
            shape.setEndpoints(6.6f, 1.0f, 6.9f, 4.8f);
            ground.createFixture(shape, 0.0f);
        }

        schedule(new TargetSelector(this, "update(float)", new Object[]{0.0f}));
    }

    /**
     *
     * @param delta float
     */
    public void update(float delta) {
        mWorld.step(delta, 10, 10);
        mWorld.clearForces();

        for (Body body : mBodyList) {
            //update sprties
            WYPoint pos = body.getPosition();
            float angle = body.getAngle();
            Sprite sprite = (Sprite) body.getUserData();
            sprite.setPosition(box2d.meter2Pixel(pos.x), box2d.meter2Pixel(pos.y));
            sprite.setRotation(-Utilities.r2d(angle));

        }
    }

    /**
     *
     * @param x float
     * @param y float
     */
    private void addNewBody(float x, float y) {
        int i = SharedData.getInstance().player.usedFallingFruit.getIndex();
        float angle = ((float) (rand.nextLong()) % 360.0f);
        float radian = Utilities.d2r(angle);
        Body fruitsBody = null;
        WYPoint anchor = null;
        sprite = null;

        if (this.mode == _MODE_FRUIT_) {
            fruitsBody = mBodyLoader.createBodyByName(box2d, fruitNames[i]);
        } else if (this.mode == _MODE_ICE_) {
            fruitsBody = mBodyLoader.createBodyByName(box2d, iceName);
        }

        mBodyList.add(fruitsBody);

        fruitsBody.setTransform(box2d.pixel2Meter(x), box2d.pixel2Meter(y), radian);

        if (this.mode == _MODE_FRUIT_) {
            sprite = Sprite.make(resIds[i]);
        } else if (this.mode == _MODE_ICE_) {
            sprite = Sprite.make(iceResIds);
        }
        sprite.autoRelease();

        if (this.mode == _MODE_FRUIT_) {
            anchor = mBodyLoader.getAnchorPercent(fruitNames[i]);
        } else if (this.mode == _MODE_ICE_) {
            anchor = mBodyLoader.getAnchorPercent(iceName);
        }
        sprite.setAnchor(anchor.x, anchor.y);
        sprite.setPosition(x, y);
        sprite.setRotation(-angle);
        fruitsBody.setUserData(sprite);
        box2d.addChild(sprite);
    }
    
    /**
     * addJuicerIngredient()
     */
    private void addJuicerIngredient() {
        this.juicerIngr = Sprite.make(R.drawable.juicer_ingredient04);
        this.juicerIngr.setPosition(this.juicer.getWidth() / 2.0f, 
                this.juicer.getHeight() / 2.0f + 70.0f);
        this.juicer.addChild(this.juicerIngr, -1);
        this.juicerIngr.autoRelease();
        this.juicerIngr.setVisible(false);
    }

    /**
     *
     * @param event MotionEvent
     * @return boolean
     */
    @Override
    public boolean wyTouchesBegan(MotionEvent event) {
        if (z == 0) {
            //this.tap.stopAllActions();
            this.tap.runAction(EaseBackIn.make(ScaleTo.make(0.5f, 1.0f, 0.0f)));
            z = 1;
        }
        x++;
        WYPoint loc = Director.getInstance().convertToGL(event.getX(), event.getY());
        if ((x <= 13) && (this.mode == _MODE_FRUIT_)) {
            addNewBody(loc.x - 120.0f, loc.y - 150.0f);
            if (x == 13) {
                this.mode = _MODE_ICE_;
            }
        } else if ((this.mode == _MODE_ICE_) && (x <= 26)) {
            addNewBody(loc.x - 120.0f, loc.y - 150.0f);
            if ((x == 26) && (y == 0)) {
                y++;
                addCapButton();
            }
        }
        return true;
    }

    /**
     * 
     */
    private void addCapButton() {
        addFlowerParticle();
        this.capButton = Button.make(R.drawable.button_cap01, R.drawable.button_cap02,
                this, "onCapButtonClicked");
        this.capButton.setPosition(this.wySize.width / 2.0f, this.wySize.height + 250.0f);
        addChild(this.capButton);
        this.capButton.autoRelease(true);
        
        this.capButton.runAction(EaseElasticInOut.make(MoveTo.make(3.0f, 
                this.capButton.getPositionX(), this.capButton.getPositionY(), 
                this.wySize.width / 2.0f, this.wySize.height - 50.0f)));
        
        InterstitialAd.display((Activity) Director.getInstance().getContext());
        
    }
    
    /**
     * Ingredient Animation.
     */
    private void runAnimation() {
        Animation anim = (Animation) new Animation(0, 0.2f, R.drawable.juicer_ingredient02,
                R.drawable.juicer_ingredient03,
                R.drawable.juicer_ingredient01,
                R.drawable.juicer_ingredient02,
                R.drawable.juicer_ingredient03,
                R.drawable.juicer_ingredient01,
                R.drawable.juicer_ingredient02,
                R.drawable.juicer_ingredient03,
                R.drawable.juicer_ingredient01,
                R.drawable.juicer_ingredient02,
                R.drawable.juicer_ingredient03,
                R.drawable.juicer_ingredient01,
                R.drawable.juicer_ingredient02,
                R.drawable.juicer_ingredient03,
                R.drawable.juicer_ingredient01,
                R.drawable.juicer_ingredient02,
                R.drawable.juicer_ingredient03,
                R.drawable.juicer_ingredient01,
                R.drawable.juicer_ingredient02,
                R.drawable.juicer_ingredient03,
                R.drawable.juicer_ingredient01,
                R.drawable.juicer_ingredient02,
                R.drawable.juicer_ingredient03,
                R.drawable.juicer_ingredient01,
                R.drawable.juicer_ingredient02,
                R.drawable.juicer_ingredient03,
                R.drawable.juicer_ingredient01,
                R.drawable.juicer_ingredient02,
                R.drawable.juicer_ingredient03,
                R.drawable.juicer_ingredient01,
                R.drawable.juicer_ingredient02,
                R.drawable.juicer_ingredient03,
                R.drawable.juicer_ingredient01,
                R.drawable.juicer_ingredient02,
                R.drawable.juicer_ingredient03,
                R.drawable.juicer_ingredient01,
                R.drawable.juicer_ingredient02,
                R.drawable.juicer_ingredient03,
                R.drawable.juicer_ingredient01,
                R.drawable.juicer_ingredient02,
                R.drawable.juicer_ingredient03,
                R.drawable.juicer_ingredient01,
                R.drawable.juicer_ingredient02,
                R.drawable.juicer_ingredient03,
                R.drawable.juicer_ingredient01,
                R.drawable.juicer_ingredient02,
                R.drawable.juicer_ingredient03,
                R.drawable.juicer_ingredient01,
                R.drawable.juicer_ingredient02,
                R.drawable.juicer_ingredient03,
                R.drawable.juicer_ingredient02,
                R.drawable.juicer_ingredient04
                );
        anim.setCallback(this);
        Animate a = (Animate) Animate.make(anim).autoRelease();
        this.juicerIngr.runAction(a);
    }

    /**
     *
     */
    private void runJuicerCapAction() {
        this.juicerCap.runAction(EaseBounceInOut.make(MoveTo.make(1.0f, this.juicerCap.getPositionX(),
                this.juicerCap.getPositionY(), this.juicerCap.getPositionX(), this.wySize.height / 2.0f - 10.0f
                + 10.0f)));
    }

    private void addJuicerButton() {
        this.particle.runAction(EaseElasticInOut.make(MoveTo.make(3.0f, 
                this.particle.getPositionX(), this.particle.getPositionY(), this.wySize.width / 2.0f, 
                this.wySize.height / 2.0f - 190.0f)));
        this.switchSprite = Sprite.make(R.drawable.button_juicer1);
        this.juicerButton = Button.make(switchSprite, switchSprite, null, null,
                this, "onJuicerButtonClicked");
        this.juicerButton.setPosition(this.wySize.width / 2.0f + 5.0f,
                this.wySize.height / 2.0f - 200.0f);
        addChild(this.juicerButton);
        this.juicerButton.autoRelease();
    }

    /**
     * juicerButton callback
     */
    public void onJuicerButtonClicked() {
        addJuicerIngredient();
        //this.sprite.runAction(FadeOut.make(1.0f, true));
        //this.box2d.removeChild(sprite, true);
        this.juicerIngr.setVisible(true);
        this.juicerIngr.runAction(FadeIn.make(2.0f, true));
        this.juicerIngr.setColor(SharedData.getInstance().player.usedFruits.getColor());
        this.particle.runAction(EaseElasticInOut.make(MoveTo.make(3.0f, 
                this.particle.getPositionX(), this.particle.getPositionY(), this.particle.getPositionX(), 
                this.wySize.height + 300.0f)));
        this.switchSprite.setDisplayFrame(Sprite.make(R.drawable.button_juicer2).makeFrame());
        runAnimation();
        this.juicerButton.setEnabled(false);
        
        this.vibrator = ((Vibrator) Director.getInstance().getContext().
                getSystemService("vibrator"));
        
        /**
         * VIBRATOR activate.
         */
        this.vibrator.vibrate(10000l);
        SharedData.getInstance().soundsHandler.playMixingSound();
    }

    /**
     * Cap button callback
     */
    public void onCapButtonClicked() {
        runJuicerCapAction();
        SharedData.getInstance().soundsHandler.playButtonClickSound();
        this.capButton.runAction(EaseElasticInOut.make(MoveTo.make(3.0f,
                this.capButton.getPositionX(), this.capButton.getPositionY(),
                this.wySize.width / 2.0f, this.wySize.height + 250.0f)));
        this.capButton.setEnabled(false);
        this.particle.runAction(EaseElasticInOut.make(MoveTo.make(3.0f,
                this.capButton.getPositionX(), this.capButton.getPositionY(),
                this.wySize.width + 100.0f, this.capButton.getPositionY())));
        if (this.particle.getPositionX() == this.wySize.width + 100.0f) {
            removeChild(this.capButton, true);
        }
        addJuicerButton();
    }

    /**
     * FlowerParticle.
     */
    private void addFlowerParticle() {
        this.particle = ParticleFlower.make();
        this.particle.setPosition(-300.0f, this.wySize.height - 50.0f);
        addChild(this.particle);
        
        this.particle.runAction(EaseElasticInOut.make(MoveTo.make(3.0f, 
                this.particle.getPositionX(), this.particle.getPositionY(), 
                this.wySize.width / 2.0f, this.particle.getPositionY())));
    }

    /**
     * SOFT-BACK CLICKED
     */
    @Override
    public void onSoftBackButtonClicked() {
        onBackButton();
    }

    /**
     * SOFT-NEXT CLICKED
     */
    @Override
    public void onSoftNextButtonClicked() {
        SharedData.getInstance().soundsHandler.playButtonClickSound();
        Director.getInstance().replaceScene((TransitionScene) 
                RightPushInTransition.make(0.5f, new GlassSelection()));
    }

    /**
     * onBackButton()
     *
     * @return boolean true
     */
    @Override
    public boolean onBackButton() {
        SharedData.getInstance().soundsHandler.playButtonClickSound();
        Director.getInstance().replaceScene((TransitionScene) LeftPushInTransition.
                make(0.5f, new FruitSelectionScene()));
        return true;
    }

    /**
     * 
     * @param i int
     * @param i1 int
     */
    @Override
    public void onAnimationFrameChanged(int i, int i1) {
        
    }

    /**
     * 
     * @param i int
     */
    @Override
    public void onAnimationEnded(int i) {
        this.switchSprite.setDisplayFrame(Sprite.make(R.drawable.button_juicer1).makeFrame());
        this.juicerIngr.runAction(RepeatForever.make(Waves3D.make(5, WYGridSize.make(12, 20), -20, 10)));
        this.juicerIngr.setPosition(this.juicerIngr.getPositionX() - 25.0f, this.juicerIngr.getPositionY() - 15.0f);
        this.juicerIngr.setScale(1.3f);
        
        /* HudLayer update */
                    this.hudLayer.updateHudObjectVisibility(true, true, false);
                    this.hudLayer.setNextButtonPosition(this.wySize.width - 60.0f,
				this.wySize.height - 50.0f);
                    HudLayer localHudLayer = this.hudLayer;
                    this.hudLayer.getClass();
                    localHudLayer.startNextButtonAction(4);
                    
        this.particle.runAction(EaseElasticInOut.make(MoveTo.make(3.0f,
                this.particle.getPositionX(), this.particle.getPositionY(), 
                this.wySize.width - 60.0f, this.wySize.height - 50.0f)));
    }
}/* end class */
