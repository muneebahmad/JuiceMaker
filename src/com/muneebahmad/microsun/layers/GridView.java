package com.muneebahmad.microsun.layers;

import com.algorithmi.maker.juice.pro.main.R;
import com.wiyun.engine.nodes.Button;
import com.wiyun.engine.nodes.ScrollableLayer;
import com.wiyun.engine.nodes.Sprite;
import com.wiyun.engine.opengl.Texture2D;
import com.wiyun.engine.types.WYPoint;
import com.wiyun.engine.utils.TargetSelector;
import com.wiyun.engine.utils.ZwoptexManager;

public class GridView extends ScrollableLayer {

	private GridInterface gridInterface;

	public GridView(GridInterface gridInterface) {
		this.gridInterface = gridInterface;
		setContentSize(480.0f, 390.0f);
		setPosition(15.0f, 195.0f);
		setVertical(true);
		setLeftMargin(50.0f);
		setRightMargin(50.0f);
		setTopMargin(10.0f);
		setBottomMargin(10.0f);
	} // end constructor

        
        private void loadZwoptex() {
            ZwoptexManager.addZwoptex("grid_button", R.raw.grid_button_texture, 
                    Texture2D.makePNG(R.drawable.grid_button_texture));
        }
        /**
         * 
         * @param point
         * @param sprite
         * @param paramBool
         * @param paramString
         * @return 
         */
	public Button getScrollItem(WYPoint point, Sprite sprite,
			boolean paramBool, String paramString) {
            loadZwoptex();
		Sprite buttUp = ZwoptexManager.makeSprite("button_grid01.png");
                Sprite buttDn = ZwoptexManager.makeSprite("button_grid02.png");
		Object[] arrayOfObject = new Object[1];
		arrayOfObject[0] = paramString;
		Button localButton = Button.make(buttUp, buttDn, null, null,
				new TargetSelector(this, "onButtonClicked(String)",
						arrayOfObject));
		localButton.setPosition(point);
		sprite.setPosition(localButton.getWidth() / 2.0f,
				localButton.getHeight() / 2.0f);
		localButton.addChild(sprite);
		if (paramBool) {
			Sprite localSprite2 = Sprite.make(R.drawable.lock_1);
			localSprite2.setPosition(
					localButton.getWidth() - localSprite2.getWidth() / 2.0f,
					localSprite2.getHeight() - localSprite2.getHeight() / 2.0f);
			localButton.addChild(localSprite2);
		}
		return localButton;
	}
	
	public void onButtonClicked(String paramString) {
		this.gridInterface.onGridItemClicked(paramString);
	}

}// end class
