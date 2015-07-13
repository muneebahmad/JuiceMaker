package com.muneebahmad.microsun.util;

/*
 * @author muneebahmad
 */

import com.algorithmi.maker.juice.pro.main.R;
import com.wiyun.engine.opengl.Texture2D;
import com.wiyun.engine.particle.ParticleSystem;
import com.wiyun.engine.particle.QuadParticleSystem;

public class ParticlePouring extends QuadParticleSystem {

	 protected ParticlePouring()
	  {
	    this(3500);
	  }

	  protected ParticlePouring(int paramInt)
	  {
	    super(paramInt);
	    setDuration(-1.0F);
	    setParticleGravity(0F, -10.0F);
	    setDirectionAngleVariance(-90.0F, 7.0F);
	    setSpeedVariance(130.0F, 30.0F);
	    setRadialAccelerationVariance(0F, 1F);
	    setTangentialAccelerationVariance(0F, 1F);
	    setLifeVariance(0.5F, 0F);
	    setStartSizeVariance(5.0F, 15.0F);
	    setEmissionRate(getMaxParticleCount() / getLife());
	    setTexture(Texture2D.makePNG(R.drawable.snow));
	    setBlendAdditive(false);
	    setAutoRemoveOnFinish(true);
	  }

	  public static ParticleSystem make()
	  {
	    return new ParticlePouring();
	  }
}
