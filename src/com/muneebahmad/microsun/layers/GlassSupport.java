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
package com.muneebahmad.microsun.layers;

import com.algorithmi.maker.juice.pro.main.R;
import com.algorithmi.maker.juice.pro.scenes.GlassSelection;
import com.muneebahmad.microsun.interfaces.AlmirahListener;
import com.wiyun.engine.nodes.Button;
import com.wiyun.engine.nodes.Director;
import com.wiyun.engine.nodes.Sprite;
import com.wiyun.engine.opengl.Texture2D;
import com.wiyun.engine.types.WYPoint;
import com.wiyun.engine.types.WYSize;
import com.wiyun.engine.utils.TargetSelector;


/**
 *
 * @author muneebahmad
 */
public class GlassSupport {
    private WYSize wySize;
    private AlmirahListener almirahInterface;

    /**
     * 
     * @param almirahInterface 
     */
    public GlassSupport(AlmirahListener almirahInterface) {
        this.wySize = Director.getInstance().getWindowSize();
        this.almirahInterface = almirahInterface;
    }
    
    /**
     * 
     * @param point
     * @param sprite
     * @param isLocked
     * @param paramString
     * @return 
     */
    public Button getGlassButtons(WYPoint point, Sprite sprite, 
            boolean isLocked, String paramString) {
        Sprite buttBg = Sprite.make(Texture2D.makePNG(R.drawable.button_cam01));
        Object[] obj = new Object[1];
        obj[0] = paramString;
        
        Button button = Button.make(buttBg, buttBg, null, null, 
                new TargetSelector(GlassSelection.class, "onButtonClicked(String)", obj));
        button.setPosition(point);
        sprite.setPosition(button.getWidth() / 2.0f, button.getHeight() / 2.0f);
        button.addChild(sprite);
        if (isLocked) {
            Sprite lock = Sprite.make(Texture2D.makePNG(R.drawable.lock_1));
            lock.setPosition(button.getWidth() - lock.getWidth() / 2.0f,
					lock.getHeight() - lock.getHeight() / 2.0f);
            button.addChild(lock);
        }
        return button;
    }
    
    /**
     * 
     * @param paramString 
     */
    public void onButtonClicked(String paramString) {
        this.almirahInterface.onGlassButtonClicked(paramString);
    }
    
} /** end class. */
