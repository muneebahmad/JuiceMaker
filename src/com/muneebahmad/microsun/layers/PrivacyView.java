/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muneebahmad.microsun.layers;

import com.wiyun.engine.nodes.Director;
import com.wiyun.engine.nodes.ScrollableLayer;
import com.wiyun.engine.nodes.Sprite;
import com.wiyun.engine.types.WYPoint;
import com.wiyun.engine.types.WYSize;
import com.wiyun.engine.utils.ResolutionIndependent;

/**
 *
 * @author muneebahmad
 */
public class PrivacyView extends ScrollableLayer {

    private PrivacyInterface privacyInterface;
    private WYSize wySize;

    public PrivacyView(PrivacyInterface privacyInterface) {
        this.privacyInterface = privacyInterface;
        this.wySize = Director.getInstance().getWindowSize();
        setContentSize(380.0f, 460.0f);
        setRelativeAnchorPoint(true);
        setPosition(this.wySize.width / 2.0f, this.wySize.height / 2.0f - 50.0f);
        setVertical(true);
        setLeftMargin(50.0f);
        setRightMargin(50.0f);
        setTopMargin(10.0f);
        setBottomMargin(10.0f);
    }
    
    public Sprite getScrollItem(WYPoint point, String content, int resId) {
        Sprite privacy = Sprite.make(resId);
        privacy.setPosition(point);
        privacy.autoRelease(true);
        return privacy;
    }
    
    private float DP(float v) {
        return ResolutionIndependent.resolveDp(v);
    }
}/* end class */
