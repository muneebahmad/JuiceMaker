/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muneebahmad.microsun.util;

import com.algorithmi.maker.juice.pro.main.R;
import com.wiyun.engine.opengl.Texture2D;
import com.wiyun.engine.particle.ParticleSystem;
import com.wiyun.engine.particle.QuadParticleSystem;

/**
 *
 * @author muneebahmad
 */
public class ParticleSun extends QuadParticleSystem {
    
    public static ParticleSystem make() {
        return new ParticleSun();
    }
    
    protected ParticleSun() {
        this(1000);
    }
    
    protected ParticleSun(int p) {
        super(p);
        setBlendAdditive(true);
        setDuration(PARTICLE_DURATION_INFINITY);
        setDirectionAngleVariance(90, 360.0f);
        setLifeVariance(1.0f, 0.5f);
        setSpeedVariance(20, 5);
        setStartSizeVariance(30.0f, 10.0f);
        setEmissionRate(getMaxParticleCount() / getLife());
        /**
        setStartColorVariance(10.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        setEndColorVariance(0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        * **/
        setTexture(Texture2D.makePNG(R.drawable.sun));
    }

}//end class
