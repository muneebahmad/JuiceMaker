package com.muneebahmad.microsun.util;

/**
 * @author muneebahmad * 
 * {@value} Core -> Util<br />< br />
 * */

import java.util.Random;

import com.wiyun.engine.actions.Action;
import com.wiyun.engine.actions.FiniteTimeAction;
import com.wiyun.engine.actions.MoveByAngle;
import com.wiyun.engine.actions.RepeatForever;
import com.wiyun.engine.actions.Sequence;


public class Core {

	public static final int FRUIT_POMEGRANATE = 0;
	public static final int FRUIT_PINEAPPLE = 1;
        public static final int FRUIT_PEAR = 2;
        public static final int FRUIT_ORANGE = 3;
        public static final int FRUIT_MANGO = 4;
        public static final int FRUIT_GRAPES = 5;
        public static final int FRUIT_STRAWBERRY = 6;
        public static final int FRUIT_BANANA = 7;
        public static final int FRUIT_APPLE = 8;
	public static final int BEAT_NONE = 2;
	public static final int GRID_MODE_STRAW = 2;
	public static final int GRID_MODE_ADDON = 9;
	public static final int GRID_MODE_SCENES = 8;
	public static final int GRID_MODE_NONE = 0;
	public static final int GRID_MODE_BG = 1;
	public static int fruitType;
	public static int gridModeCurrent = -1;
        public static int privacyModeCurrent = -1;

	static {
		fruitType = -1;
	}

	public static int getRendom(int paramInt1, int paramInt2) {
		Random mRandom = new Random();
		mRandom.setSeed(System.currentTimeMillis());
		return (paramInt1 + mRandom.nextInt(1 + paramInt2 - paramInt1));
	}

	public static float getRandomFloatWithRange(float paramFloat1,
			float paramFloat2) {
		Random mRandom = new Random();
		mRandom.setSeed(System.currentTimeMillis());
		return (paramFloat1 + mRandom.nextFloat() * (paramFloat2 - paramFloat1));
	}

	public static Action getRepeatingAngularAction(float duration, int degree,
			int velocity, float x, float y, float delta) {
		MoveByAngle moveByAngle = (MoveByAngle) MoveByAngle.make(duration,
				degree, velocity).autoRelease();
		moveByAngle.setPinPoint(x, y);
		moveByAngle.setPinAngleDelta(delta);
		MoveByAngle moveByAngle2 = (MoveByAngle) MoveByAngle.make(duration,
				degree, -velocity).autoRelease();
		moveByAngle2.setPinPoint(x, y);
		moveByAngle2.setPinAngleDelta(delta);
		MoveByAngle moveByAngle3 = (MoveByAngle) moveByAngle2.reverse()
				.autoRelease();
		MoveByAngle moveByAnlge4 = (MoveByAngle) moveByAngle.reverse()
				.autoRelease();
		FiniteTimeAction[] finiteTimeAction = new FiniteTimeAction[3];
		finiteTimeAction[0] = moveByAnlge4;
		finiteTimeAction[1] = moveByAngle2;
		finiteTimeAction[2] = moveByAngle3;
		return ((Action) RepeatForever.make(
				(Sequence) Sequence.make(moveByAngle, finiteTimeAction)
						.autoRelease()).autoRelease());
	}
}// end class
