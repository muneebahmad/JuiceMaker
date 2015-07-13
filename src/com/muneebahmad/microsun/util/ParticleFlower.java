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
package com.muneebahmad.microsun.util;

import com.algorithmi.maker.juice.pro.main.R;
import com.wiyun.engine.opengl.Texture2D;
import com.wiyun.engine.particle.ParticleSystem;
import com.wiyun.engine.particle.QuadParticleSystem;

/**
 *
 * @author muneebahmad
 */
public class ParticleFlower extends QuadParticleSystem {
    
    /**
     * 
     * @return 
     */
    public static ParticleSystem make() {
        return new ParticleFlower();
    }
    
    /**
     * CONSTRUCTOR 1.
     */
    protected ParticleFlower() {
        this(250);
    }
    
    /**
     * CONSTRUCTOR 2.
     * @param p 
     */
    protected ParticleFlower(int p) {
        super(p);
        
        //duaration
        setDuration(PARTICLE_DURATION_INFINITY);
        
        //angle
        setDirectionAngleVariance(90, 360);
        
        //speed of particles
        setSpeedVariance(80, 10);
        
        //radial
        setRadialAccelerationVariance(-60, 0);
        
        //tangential
        setTangentialAccelerationVariance(15, 0);
        
        //life of particles
        setLifeVariance(4, 1);
        
        //size, in pixels
        setStartSizeVariance(30.0f, 10.0f);
        
        //emits per second
        setEmissionRate(getMaxParticleCount() / getLife());
        
        //color of particles
        setStartColorVariance(0.5f, 0.5f, 0.5f, 1f, 0.5f, 0.5f, 0.5f, 0.5f);
        setEndColorVariance(0f, 0f, 0f, 1f, 0f, 0f, 0f, 0f);
        
        //texture
        setTexture(Texture2D.makePNG(R.drawable.stars));
        
        //additive
        setBlendAdditive(true);
    }
}/* end class. */
