/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muneebahmad.microsun.layers;

import com.algorithmi.maker.juice.pro.main.R;
import com.wiyun.engine.actions.CallFunc;
import com.wiyun.engine.actions.FiniteTimeAction;
import com.wiyun.engine.actions.MoveTo;
import com.wiyun.engine.actions.Sequence;
import com.wiyun.engine.actions.ease.EaseElasticIn;
import com.wiyun.engine.nodes.Button;
import com.wiyun.engine.nodes.Director;
import com.wiyun.engine.nodes.Layer;
import com.wiyun.engine.nodes.NinePatchSprite;
import com.wiyun.engine.nodes.Sprite;
import com.wiyun.engine.opengl.Texture2D;
import com.wiyun.engine.types.WYPoint;
import com.wiyun.engine.types.WYRect;
import com.wiyun.engine.types.WYSize;
import com.wiyun.engine.utils.ResolutionIndependent;

/**
 *
 * @author muneebahmad
 */
public class PrivacyLayer extends Layer {

    private Sprite bg;
    private Button close;
    private PrivacyInterface privacyInterface;
    private PrivacyView privacyView;
    private WYSize wySize;
    
    /**
     * 
     * @param privacyInterface 
     */
    public PrivacyLayer(PrivacyInterface privacyInterface) {
        this.wySize = Director.getInstance().getWindowSize();
        this.privacyInterface = privacyInterface;
        this.privacyView = new PrivacyView(privacyInterface);
        
        NinePatchSprite vThumb = NinePatchSprite.make(Texture2D.makePNG
                (R.drawable.vertical_thumb), WYRect.make(DP(5), DP(7), DP(1), DP(1)));
        privacyView.setVerticalThumb(vThumb);
        addChild(this.privacyView, 1);
        setContents();
    }

    /**
     * 
     */
    private void setContents() {
        addBackground();
        addCloseButton();
    }

    /**
     * 
     */
    private void addBackground() {
        this.bg = Sprite.make(R.drawable.privacy_window);
        this.bg.setPosition(this.wySize.width / 2.0f, this.wySize.height / 2.0f);
        addChild(this.bg);
        this.bg.autoRelease(true);
    }

    /**
     * 
     */
    private void addCloseButton() {
        this.close = Button.make(R.drawable.close_button1, R.drawable.close_button2,
                this, "onCloseButtonClicked");
        this.close.setPosition(this.bg.getWidth() - 63.0f, this.bg.getHeight() - 85.0f);
        addChild(this.close);
        this.close.autoRelease(true);
    }
    
    /**
     * 
     */
    public void onCloseButtonClicked() {
        this.privacyInterface.onCloseButtonClicked();
    }
    
    /**
     * 
     */
    public void populatePrivacy() {
        Sprite privacy = this.privacyView.getScrollItem(WYPoint.make(this.privacyView.getWidth() / 2.0f, 
                this.privacyView.getHeight() / 2.0f)
                , "",
                R.drawable.privacy_policy);
        this.privacyView.addScrollableChild(privacy);
    }
    
    /**
     * 
     */
    public void removePrivacyView() {
        removeChild(this.privacyView, true);
        removeChild(this.bg, true);
    }

    /**
     * 
     */
    public void removePrivacyViewWithAction() {
        MoveTo localMoveTo = MoveTo.make(5.5f, 0.0f, 0.0f, 0.0f, 2000.0f);
        CallFunc callFunc = CallFunc.make(this, "removePrivacyView");
        FiniteTimeAction[] arrayOfFiniteTimeAction = new FiniteTimeAction[1];
        arrayOfFiniteTimeAction[0] = callFunc;
        runAction(Sequence.make(EaseElasticIn.make(localMoveTo), arrayOfFiniteTimeAction));
    }
    
    /**
     * 
     * @param v
     * @return float
     */
    private float DP(float v) {
        return ResolutionIndependent.resolveDp(v);
    }
    
}/* end class */
